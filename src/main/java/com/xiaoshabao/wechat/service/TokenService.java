package com.xiaoshabao.wechat.service;

import com.xiaoshabao.wechat.entity.AccessToken;

/**
 * token
 */
public interface TokenService {
	/**
	 * 获得帐号信息，不保证token信息实时
	 * @param accountId
	 * @return
	 */
	public AccessToken getAccountToken(Integer accountId);
	
	/**
	 * 获取所有token信息
	 * @param accountId
	 * @return
	 */
	public AccessToken getToken(Integer accountId);
	/**
	 * 获取accessToken
	 * @param accountId
	 * @return
	 */
	public AccessToken getAccessToken(Integer accountId);
	
	/**
	 * 获取jsToken
	 */
	public AccessToken getJSToken(Integer accountId);
	
	/**
	 * 更新Tokon信息包括数据库和内存
	 * @param accountId
	 * @param accessToken
	 * @param time_now_long
	 * @return
	 */
	public AccessToken updateAccessToken(Integer accountId,AccessToken accessToken,long time_now_long);
	
	/**
	 * 更新jstoken
	 * @param accountId
	 * @param accessToken
	 * @param time_now_long
	 * @return
	 */
	public AccessToken updateJSToken(Integer accountId,AccessToken accessToken,long time_now_long );

}
