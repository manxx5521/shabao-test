package com.xiaoshabao.wechat.entity;
/**
 * 图片列表
 */
public class VoteImageEntity {
	private Integer voteId;
	private String type;
	private Integer playerId;
	private String image;
	
	public Integer getVoteId() {
		return voteId;
	}
	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
