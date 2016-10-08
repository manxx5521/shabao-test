package com.xiaoshabao.wechat.webservice.server;

import javax.jws.WebService;

import com.xiaoshabao.wechat.entity.AccessToken;

/**
 * token的webService
 */
@WebService
public interface TokenServerWS {
	/**
	 * 给本地提供支持
	 * @param accountId
	 * @return
	 */
	public AccessToken getToken(Integer accountId);
}
