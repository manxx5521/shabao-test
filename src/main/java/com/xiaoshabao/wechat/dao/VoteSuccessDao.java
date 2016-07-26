package com.xiaoshabao.wechat.dao;

import org.apache.ibatis.annotations.Param;

import com.xiaoshabao.wechat.entity.VoteSuccessEntity;

/**
 * 投票成功信息
 */
public interface VoteSuccessDao {
	/**
	 * 插入投票成功信息
	 * @param voteSuccess
	 * @return
	 */
	public int insertVoteSuccess(VoteSuccessEntity voteSuccess);
	/**
	 * 插入投票成功信息
	 */
	public int insertVoteSuccess(@Param("voteId")Integer voteId,@Param("openid")String openid);
	
	/**
	 * 查询成功信息
	 * @param voteId
	 * @param openid
	 * @return
	 */
	public VoteSuccessEntity getVoteSuccess(@Param("voteId")Integer voteId,@Param("openid")String openid);

}
