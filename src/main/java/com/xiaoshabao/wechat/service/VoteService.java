package com.xiaoshabao.wechat.service;

import com.xiaoshabao.baseframe.service.AbstractService;
import com.xiaoshabao.webframe.dto.AjaxResult;
import com.xiaoshabao.wechat.dto.VoteDetailResult;
import com.xiaoshabao.wechat.dto.VoteListResult;
import com.xiaoshabao.wechat.dto.VoteParams;
import com.xiaoshabao.wechat.entity.VotePlayerEntity;

public interface VoteService extends AbstractService{
	
	/**
	 * 获得蓝色主题，投票选手信息
	 * @param account_id
	 * @return
	 */
	public VoteListResult getVoteListResult(Integer voteId,String code,VoteParams params);
	/**
	 * 获得蓝色主题，投票选手信息
	 * @param account_id
	 * @return
	 */
	public VoteListResult getVoteListResult(Integer voteId);
	
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
	/**
	 * 添加选手报名信息
	 * @param player
	 * @return
	 */
	public AjaxResult addVotePlayer(VotePlayerEntity player,String[] imgs);
	/**
	 * 进行投票
	 * @param player
	 * @param imgs
	 * @return
	 */
	public AjaxResult addVoteNum(Integer voteId,Integer playerId);
}
