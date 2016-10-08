package com.xiaoshabao.wechat.service.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.wechat.api.wxbase.TokenAPI;
import com.xiaoshabao.wechat.api.wxbase.result.TokenResult;
import com.xiaoshabao.wechat.dao.AccessTokenDao;
import com.xiaoshabao.wechat.entity.AccessToken;
import com.xiaoshabao.wechat.service.TokenService;
import com.xiaoshabao.wechat.util.WechatUtil;
/**
 * 基础token实现
 */
public abstract class TokenAbstractServiceImpl implements TokenService{
	
	protected static Logger logger = LoggerFactory.getLogger(TokenAbstractServiceImpl.class);
	
	@Autowired
	private AccessTokenDao tokenDao;
	
	/** 延迟时间 **/
	private int time=60000;
	
	//获得帐号信息，不保证token信息实时
	public AccessToken getAccountToken(Integer accountId){
		return this.getMemoryToken(accountId);
	}
	/**
	 * 获取accessToken 判断内存中token是否过期，过期重新换取
	 * @param accountId
	 * @param accessToken 内存中的
	 * @return
	 */
	protected AccessToken getAccessToken(Integer accountId,AccessToken accessToken) {
		//计算存在的token是否符合规则
		long time_now_long = new java.util.Date().getTime();
		if (time_now_long - accessToken.getUpdateTime().getTime() > accessToken.getExpiresIn() * 1000 - time) {
			accessToken=this.updateAccessToken(accountId, accessToken, time_now_long);
		}
		return accessToken;
	}
	/**
	 * 更新Tokon信息包括数据库和内存
	 * @param accountId
	 * @param accessToken
	 * @param time_now_long
	 * @return
	 */
	public AccessToken updateAccessToken(Integer accountId,AccessToken accessToken,long time_now_long) {
		logger.debug("内存中的accessToken不在有效期内需要重新换取");
		TokenResult token=TokenAPI.getAccessTokenAll(accessToken.getAppid(), accessToken.getAppsecret());
		accessToken.setUpdateTime(new Timestamp(time_now_long));
		accessToken.setAccessToken(token.getToken());
		accessToken.setExpiresIn(token.getExpires_in());
		int i=tokenDao.updateAccessToken(accessToken);
		if(i<1){
			logger.error("换取的accessToken未能更新到数据库");
		}
		WechatUtil.accessTokens.put(accountId, accessToken);
		logger.debug(accountId+"成功在服务器获取accessToken");
		return accessToken;
	}
	
	/**
	 * 获取JSToken 判断内存中token是否过期，过期重新换取
	 * @param accountId
	 * @param accessToken 内存中token
	 * @return
	 */
	protected AccessToken getJSToken(Integer accountId,AccessToken accessToken){
		//计算存在的token是否符合规则
		long time_now_long = new java.util.Date().getTime();
		if (time_now_long - accessToken.getJsupdateTime().getTime() > accessToken.getJsexpiresIn() * 1000 - time) {
			this.updateJSToken(accountId, accessToken, time_now_long);
		}
		return accessToken;
	}
	/**
	 * 更新jstoken
	 * @param accountId
	 * @param accessToken
	 * @param time_now_long
	 * @return
	 */
	public AccessToken updateJSToken(Integer accountId,AccessToken accessToken,long time_now_long ){
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
		WechatUtil.accessTokens.put(accountId, accessToken);
		logger.debug(accountId+"成功在服务器获取jsToken");
		return accessToken;
	}
	/**
	 * 获得内存中的token
	 * <br>
	 * 如果没有会获取数据库的token放到内存中(不保证token有效)
	 * @param accountId
	 */
	protected AccessToken getMemoryToken(Integer accountId){
		logger.debug(accountId+"开始获取Token");
		// 获得静态变量里缓存的token
		AccessToken accessToken = WechatUtil.accessTokens.get(accountId);
		
		//将数据库的信息取出放到内存
		if (accessToken == null) {
			logger.debug("内存中不存在accessToken，在数据库中获取放到内存");
			AccessToken token =getRealToken(accountId);
			WechatUtil.accessTokens.put(accountId, token);
			accessToken=token;
		}
		return accessToken;
	}
	/**
	 * 获得数据库信息
	 */
	protected AccessToken getRealToken(Integer accountId){
		AccessToken token = tokenDao.getTokenById(accountId);
		if (token == null) {
			throw new ServiceException("未在数据库中获得token信息");
		}
		return token;
	}

}
