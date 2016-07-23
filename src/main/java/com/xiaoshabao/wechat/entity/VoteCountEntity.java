package com.xiaoshabao.wechat.entity;


/**
 * 投票统计表
 */
public class VoteCountEntity {
	/**
	 * 投票id
	 */
	private Integer voteId;
	/**
	 * 选手人数
	 */
	private int userNum;
	/**
	 * 投票数
	 */
	private int voteNum;
	/**
	 * 访问数
	 */
	private int visitNum;
	public Integer getVoteId() {
		return voteId;
	}
	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public int getVoteNum() {
		return voteNum;
	}
	public void setVoteNum(int voteNum) {
		this.voteNum = voteNum;
	}
	public int getVisitNum() {
		return visitNum;
	}
	public void setVisitNum(int visitNum) {
		this.visitNum = visitNum;
	}
}
