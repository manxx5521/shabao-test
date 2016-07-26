package com.xiaoshabao.wechat.entity;

/**
 * 投票成功信息
 */
public class VoteSuccessEntity {

	private Integer id;
	private Integer voteId;
	private String openid;
	private String updateTime;

	public VoteSuccessEntity() {
	}

	public VoteSuccessEntity(Integer voteId, String openid) {
		this.voteId = voteId;
		this.openid = openid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVoteId() {
		return voteId;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
