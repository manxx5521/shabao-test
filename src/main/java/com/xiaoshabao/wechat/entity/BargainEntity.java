package com.xiaoshabao.wechat.entity;

import java.util.Date;

/**
 * 砍价
 */
public class BargainEntity {
	/** 砍价id */
	private Integer bargainId;
	/** 帐号 */
	private Integer accountId;
	/** 砍价名字 */
	private String name;
	/** 总价 */
	private Integer totalPrice;
	/** 砍到的最小价钱 */
	private Integer mimPrice;
	/** 单词砍掉最大金额 */
	private Integer onePrice;
	/** 砍价的最大次数 **/
	private Integer naxNumber;
	/** 当前商品价格 */
	private Integer price;
	/** 一共砍掉的价钱 */
	private Integer bargainPrice;
	/** 砍价的次数 */
	private Integer bargainNum;
	/** 销量 */
	private Integer saleNum;
	/** 参与用户数 */
	private Integer userNum;
	/** 开始时间 */
	private Date startTime;
	/** 结束时间 */
	private Date endTime;
	private Integer createStaff;
	private Date createTime;

	public Integer getBargainId() {
		return bargainId;
	}

	public void setBargainId(Integer bargainId) {
		this.bargainId = bargainId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getMimPrice() {
		return mimPrice;
	}

	public void setMimPrice(Integer mimPrice) {
		this.mimPrice = mimPrice;
	}

	public Integer getOnePrice() {
		return onePrice;
	}

	public void setOnePrice(Integer onePrice) {
		this.onePrice = onePrice;
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

	public Integer getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(Integer createStaff) {
		this.createStaff = createStaff;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getBargainNum() {
		return bargainNum;
	}

	public void setBargainNum(Integer bargainNum) {
		this.bargainNum = bargainNum;
	}

	public Integer getNaxNumber() {
		return naxNumber;
	}

	public void setNaxNumber(Integer naxNumber) {
		this.naxNumber = naxNumber;
	}

}
