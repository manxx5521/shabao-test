package com.xiaoshabao.wechat.component;

import com.xiaoshabao.wechat.entity.AccessToken;
import com.xiaoshabao.wechat.service.TokenService;

/**
 * 微信token管理类
 */
//@Component("tokenManager")通过配置文件注入
public class TokenManager {
	/**
	 * spring注入的token服务
	 */
	private TokenService tokenService;
	/**
	 * 获取所有token信息
	 * @param accountId
	 * @return
	 */
	public AccessToken getToken(Integer accountId) {
		return tokenService.getToken(accountId);
	}
	
	/**
	 * 获取accessToken
	 * @param accountId
	 * @return
	 */
	public AccessToken getAccessToken(Integer accountId) {
		return tokenService.getAccountToken(accountId);
	}
	/**
	 * 获取accessTokenString
	 * @param accountId
	 * @return
	 */
	public String getAccessTokenString(Integer accountId) {
		return tokenService.getAccountToken(accountId).getAccessToken();
	}
	
	/**
	 * 获取jsToken
	 */
	public AccessToken getJSToken(Integer accountId){
		return tokenService.getJSToken(accountId);
	}

	
	public TokenService getTokenService() {
		return tokenService;
	}

	public void setTokenService(TokenService tokenService) {
		this.tokenService = tokenService;
	}
}
