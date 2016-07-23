package com.xiaoshabao.wechat.entity;

import java.util.Date;

/**
 * 投票选手
 */
public class VotePlayerEntity {

	private Integer playerId;
	/**
	 * 投票编码
	 */
	private Integer voteId;
	/**
	 * 状态1已审核0初始
	 */
	private Integer status;
	/**
	 * 选手名字
	 */
	private String playerName;
	/**
	 * 联系方式
	 */
	private String phone;
	/**
	 * 描述
	 */
	private String des;
	/**
	 * 选手编码，是几号选手
	 */
	private Integer playerNum;
	/**
	 * 图片
	 */
	private String image;
	/**
	 * 投票数
	 */
	private Integer voteNum;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	public Integer getVoteId() {
		return voteId;
	}
	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Integer getPlayerNum() {
		return playerNum;
	}
	public void setPlayerNum(Integer playerNum) {
		this.playerNum = playerNum;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getVoteNum() {
		return voteNum;
	}
	public void setVoteNum(Integer voteNum) {
		this.voteNum = voteNum;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
