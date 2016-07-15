package com.xiaoshabao.wechat.dao;

import com.xiaoshabao.wechat.dto.VoteListResult;

public interface VoteDao {
	/**
	 * 查询投票界面需要的信息
	 */
	public VoteListResult getVoteListResult(Integer voteId);
}
