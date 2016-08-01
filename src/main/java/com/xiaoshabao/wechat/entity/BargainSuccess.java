package com.xiaoshabao.wechat.entity;

import java.util.Date;

/**
 * 砍价成功信息表
 */
public class BargainSuccess {
	private Integer id;
	private Integer joinId;
	private String openid;
	/** 当前商品价格 ，类型为1时表示当前砍价的价钱，2时表示当次砍到的价钱 */
	private Integer price;
	/** 本次砍掉价钱 */
	private Integer bargainPrice;
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getBargainPrice() {
		return bargainPrice;
	}

	public void setBargainPrice(Integer bargainPrice) {
		this.bargainPrice = bargainPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getJoinId() {
		return joinId;
	}

	public void setJoinId(Integer joinId) {
		this.joinId = joinId;
	}

}
