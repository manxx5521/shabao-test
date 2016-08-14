package com.xiaoshabao.wechat.dao;

import java.util.List;

import com.xiaoshabao.wechat.dto.TokenUpdateDto;
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
	/**
	 * 获得调度任务需要更新的帐号列表
	 * @return
	 */
	public List<TokenUpdateDto> getTokenUpdateDto();

}
