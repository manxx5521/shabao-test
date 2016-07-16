package com.xiaoshabao.wechat.component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.wechat.api.wxbase.TokenAPI;
import com.xiaoshabao.wechat.api.wxbase.result.TokenResult;
import com.xiaoshabao.wechat.dao.AccessTokenDao;
import com.xiaoshabao.wechat.entity.AccessToken;

/**
 * 微信token管理类
 */
@Component("tokenManager")
public class TokenManager {
	private static Logger logger = LoggerFactory.getLogger(TokenManager.class);
	/**
	 * token静态类，使用内存缓存
	 */
	public static Map<Integer, AccessToken> accessTokens = new HashMap<Integer, AccessToken>();
	@Autowired
	private AccessTokenDao tokenDao;
	/** 延迟时间 **/
	private int time;

	/**
	 * 获取所有token信息
	 * @param accountId
	 * @return
	 */
	public AccessToken getToken(Integer accountId) {
		AccessToken accessToken=null;
		try {
			accessToken =this.getAccessToken(accountId);
			AccessToken jsToken=this.getJSToken(accountId, accessToken);
			accessToken.setJsupdateTime(jsToken.getJsupdateTime());
			accessToken.setJsaccessToken(jsToken.getJsaccessToken());
			accessToken.setJsexpiresIn(jsToken.getJsexpiresIn());
		} catch (Exception e) {
			logger.error("获取token错误"+e.getMessage());
			e.printStackTrace();
			throw new ServiceException("获取token错误");
		}
		return accessToken;
	}
	
	/**
	 * 获取accessToken
	 * @param accountId
	 * @return
	 */
	public AccessToken getAccessToken(Integer accountId) {
		AccessToken accessToken=null;
		try {
			//现有
			accessToken =this.getMemoryToken(accountId);
			accessToken=this.getAccessToken(accountId,accessToken);
		} catch (Exception e) {
			logger.error("获取token错误"+e.getMessage());
			e.printStackTrace();
			throw new ServiceException("获取token错误");
		}
		return accessToken;
	}
	/**
	 * 获取accessToken
	 * @param accountId
	 * @param accessToken
	 * @return
	 */
	public AccessToken getAccessToken(Integer accountId,AccessToken accessToken) {
		//计算存在的token是否符合规则
		long time_now_long = new java.util.Date().getTime();
		if (time_now_long - accessToken.getUpdateTime().getTime() > accessToken.getExpiresIn() * 1000 - time) {
			logger.debug("内存中的accessToken不在有效期内需要重新换取");
			TokenResult token=TokenAPI.getAccessTokenAll(accessToken.getAppid(), accessToken.getAppsecret());
			accessToken.setUpdateTime(new Timestamp(time_now_long));
			accessToken.setAccessToken(token.getToken());
			accessToken.setExpiresIn(token.getExpires_in());
			int i=tokenDao.updateAccessToken(accessToken);
			if(i<1){
				logger.error("换取的accessToken未能更新到数据库");
			}
			accessTokens.put(accountId, accessToken);
			logger.debug(accountId+"成功在服务器获取accessToken");
		}
		return accessToken;
	}
	/**
	 * 获取jsToken
	 */
	public AccessToken getJSToken(Integer accountId){
		AccessToken accessToken=null;
		try {
			accessToken =this.getMemoryToken(accountId);
			accessToken=this.getJSToken(accountId,accessToken);
		} catch (Exception e) {
			logger.error("获取token错误"+e.getMessage());
			e.printStackTrace();
			throw new ServiceException("获取token错误");
		}
		return accessToken;
	}
	/**
	 * 获取JSToken
	 * @param accountId
	 * @param accessToken
	 * @return
	 */
	public AccessToken getJSToken(Integer accountId,AccessToken accessToken){
		//计算存在的token是否符合规则
		long time_now_long = new java.util.Date().getTime();
		if (time_now_long - accessToken.getJsupdateTime().getTime() > accessToken.getJsexpiresIn() * 1000 - time) {
			logger.debug("内存中的jsToken不在有效期内需要重新换取");
			//判断accessToken
			accessToken=getAccessToken(accountId,accessToken);
			TokenResult token=TokenAPI.getJSTokenAll(accessToken.getAccessToken());
			accessToken.setJsupdateTime(new Timestamp(time_now_long));
			accessToken.setJsaccessToken(token.getToken());
			accessToken.setJsexpiresIn(token.getExpires_in());
			int i=tokenDao.updateJSToken(accessToken);
			if(i<1){
				logger.error("换取的jsToken未能更新到数据库");
			}
			accessTokens.put(accountId, accessToken);
			logger.debug(accountId+"成功在服务器获取jsToken");
		}
		return accessToken;
	}
	/**
	 * 获得内存中的token
	 * <br>
	 * 如果没有会获取数据库的token放到内存中
	 * @param accountId
	 * @return
	 * @throws ServiceException
	 */
	private AccessToken getMemoryToken(Integer accountId){
		logger.debug(accountId+"开始获取Token");
		// 获得静态变量里缓存的token
		AccessToken accessToken = accessTokens.get(accountId);
		
		//将数据库的信息取出放到内存
		if (accessToken == null) {
			logger.debug("内存中不存在accessToken，在数据库中获取放到内存");
			AccessToken token =getRealToken(accountId);
			accessTokens.put(accountId, token);
			accessToken=token;
		}
		return accessToken;
	}
	
	/**
	 * 获得数据库信息
	 */
	private AccessToken getRealToken(Integer accountId){
		AccessToken token = tokenDao.getTokenById(accountId);
		if (token == null) {
			throw new ServiceException("未在数据库中获得token信息");
		}
		return token;
	}

}
