package com.xiaoshabao.wechat.dao;

import com.xiaoshabao.wechat.entity.AccessToken;
/**
 * token等信息获取
 */
public interface AccessTokenDao {
	/**
	 * 获取token实体 信息
	 * @param accountId
	 * @return
	 */
	public AccessToken getTokenById(Integer accountId);
	/**
	 * 更新token
	 * @param accessToken
	 * @return
	 */
	public int updateToken(AccessToken accessToken);
	/**
	 * 更新jsToken
	 * @param accessToken
	 * @return
	 */
	public int updateJSToken(AccessToken accessToken);
	/**
	 * 更新Token
	 * @param accessToken
	 * @return
	 */
	public int updateAccessToken(AccessToken accessToken);

}
