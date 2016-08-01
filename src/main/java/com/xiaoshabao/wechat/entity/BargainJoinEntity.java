package com.xiaoshabao.wechat.entity;

import java.util.Date;

/**
 * 砍价-参与表
 */
public class BargainJoinEntity {
	/** 砍价id */
	private Integer joinId;
	/** 砍价id */
	private Integer bargainId;
	/** 微信id ***/
	private String openid;
	/** 状态，1-砍价中，2-兑奖 */
	private Integer status;
	/** 当前价格 */
	private Integer price;
	/** 砍掉价钱 */
	private Integer bargainPrice;
	/** 当前砍价的次数 */
	private Integer bargainNum;
	/** 更新时间 **/
	private Date updateTime;
	
	public Integer getJoinId() {
		return joinId;
	}
	public void setJoinId(Integer joinId) {
		this.joinId = joinId;
	}
	public Integer getBargainId() {
		return bargainId;
	}
	public void setBargainId(Integer bargainId) {
		this.bargainId = bargainId;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getBargainPrice() {
		return bargainPrice;
	}
	public void setBargainPrice(Integer bargainPrice) {
		this.bargainPrice = bargainPrice;
	}
	public Integer getBargainNum() {
		return bargainNum;
	}
	public void setBargainNum(Integer bargainNum) {
		this.bargainNum = bargainNum;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
