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
	/** 使用模版 ***/
	private String template;
	/** 砍价名字 */
	private String bargainName;
	/** 规则 */
	private String rules;
	/** 总价 */
	private Integer totalPrice;
	/** 砍到的最小价钱 */
	private Integer mimPrice;
	/** 单次砍掉最大金额 */
	private Integer onePrice;
	/** 砍价的最大次数 **/
	private Integer naxBargainNum;
	/** 活动砍价的次数 */
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

	public String getBargainName() {
		return bargainName;
	}

	public void setBargainName(String bargainName) {
		this.bargainName = bargainName;
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

	public Integer getNaxBargainNum() {
		return naxBargainNum;
	}

	public void setNaxBargainNum(Integer naxBargainNum) {
		this.naxBargainNum = naxBargainNum;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

}
