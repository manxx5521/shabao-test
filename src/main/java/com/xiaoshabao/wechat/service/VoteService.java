package com.xiaoshabao.wechat.service;

import com.xiaoshabao.baseframe.service.AbstractService;
import com.xiaoshabao.wechat.dto.VoteDetailResult;
import com.xiaoshabao.wechat.dto.VoteListResult;

public interface VoteService extends AbstractService{
	
	/**
	 * 获得蓝色主题，投票选手信息
	 * @param account_id
	 * @return
	 */
	public VoteListResult getVoteList(Integer voteId);
	
	/**
	 * 获得蓝色主题，单个选手信息
	 * @param account_id
	 * @return
	 */
	public VoteDetailResult getVoteDetailById(Integer playerId);
	
	/**
	 * 报名参加活动
	 * @param account_id
	 * @return
	 */
	public VoteListResult getVoteParticipate(Integer voteId);
	/**
	 * 排行榜信息
	 * @param voteId
	 */
	public  VoteListResult getVoteRanking(Integer voteId,Integer type);
	
}
