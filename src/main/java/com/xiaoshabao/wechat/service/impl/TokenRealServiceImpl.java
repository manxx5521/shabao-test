package com.xiaoshabao.wechat.service.impl;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.wechat.entity.AccessToken;
/**
 * 直接在数据库侧获得
 */
@Service("tokenRealServiceImpl")
public class TokenRealServiceImpl extends TokenAbstractServiceImpl{

	@Override
	public AccessToken getToken(Integer accountId) {
		AccessToken accessToken=null;
		try {
			accessToken=this.getRealToken(accountId);
		} catch (Exception e) {
			logger.error("webService获取token错误"+e.getMessage());
			e.printStackTrace();
			throw new ServiceException("获取token错误");
		}
		return accessToken;
	}

	@Override
	public AccessToken getAccessToken(Integer accountId) {
		AccessToken accessToken=null;
		try {
			accessToken=this.getRealToken(accountId);
		} catch (Exception e) {
			logger.error("webService获取token错误"+e.getMessage());
			e.printStackTrace();
			throw new ServiceException("获取token错误");
		}
		return accessToken;
	}

	@Override
	public AccessToken getJSToken(Integer accountId) {
		AccessToken accessToken=null;
		try {
			accessToken=this.getRealToken(accountId);
		} catch (Exception e) {
			logger.error("webService获取token错误"+e.getMessage());
			e.printStackTrace();
			throw new ServiceException("获取token错误");
		}
		return accessToken;
	}

}
