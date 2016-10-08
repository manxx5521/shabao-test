package com.xiaoshabao.wechat.service.impl;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.wechat.entity.AccessToken;
/**
 * 正常获取token
 */
@Service("tokenServiceImpl")
public class TokenServiceImpl extends TokenAbstractServiceImpl{

	@Override
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

	@Override
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

	@Override
	public AccessToken getJSToken(Integer accountId) {
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

}
