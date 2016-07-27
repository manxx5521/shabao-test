package com.xiaoshabao.wechat.entity;

import java.util.Date;

/**
 * 砍价成功信息表
 */
public class BargainSuccess {
	private Integer id;
	private Integer bargainId;
	private String openid;
	/** 自己砍价为1，别人帮忙为2 */
	private String type;
	/** 本次砍掉价钱 */
	private Integer bargainPrice;
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
}
