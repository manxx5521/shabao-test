package com.xiaoshabao.wechat.dao;

import com.xiaoshabao.wechat.dto.VoteListResult;
import com.xiaoshabao.wechat.entity.VoteEntity;

public interface VoteDao {
	/**
	 * 查询投票界面需要的信息
	 */
	public VoteListResult getVoteListResult(Integer voteId);
	
	/**
	 * 查询投票信息
	 */
	public VoteEntity getVote(Integer voteId);
}
