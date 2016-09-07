package com.xiaoshabao.wechat.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 砍价成功信息表
 */
public class BargainSuccessEntity {
	private Integer id;
	private Integer joinId;
	private String openid;
	/** 当前商品价格 ，当次砍到的价钱 */
	private BigDecimal price;
	/** 本次砍掉价钱 */
	private BigDecimal bargainPrice;
	private Date createTime;

	public BargainSuccessEntity() {
	}

	public BargainSuccessEntity(Integer joinId, String openid) {
		this.joinId = joinId;
		this.openid = openid;
	}

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getBargainPrice() {
		return bargainPrice;
	}

	public void setBargainPrice(BigDecimal bargainPrice) {
		this.bargainPrice = bargainPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getJoinId() {
		return joinId;
	}

	public void setJoinId(Integer joinId) {
		this.joinId = joinId;
	}

}
