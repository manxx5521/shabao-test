package com.xiaoshabao.wechat.dao;
/**
 * 投票统计
 */
public interface VoteCountDao {
	/**
	 * 将选手用户加一
	 * @param voteId
	 * @return
	 */
	public int addUserNum(Integer voteId);
	/**
	 * 将访问量加一
	 * @param voteId
	 * @return
	 */
	public int addVisitNum(Integer voteId);
	/**
	 * 将总票数加一
	 * @param voteId
	 * @return
	 */
	public int addVoteNum(Integer voteId);
	

}
