package com.xiaoshabao.wechat.entity;

import java.util.Date;

/**
 * 投票活动实体类
 */
public class VoteEntity {
	/**
	 * 投票id
	 */
	private Integer voteId;
	/**
	 * 微信帐号id
	 */
	private Integer accountId;
	/**
	 * 是否需要审核1需要
	 */
	private Integer audit;
	/**
	 * 投票活动名字
	 */
	private String voteName;
	/**
	 * 使用的模版
	 */
	private String template;
	/**
	 * 规则描述
	 */
	private String rules;
	/**
	 * 描述
	 */
	private String des;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 未关注挑战url
	 */
	private String loginUrl;
	/**
	 * 创建帐号
	 */
	private Integer createStaff;
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	public Integer getVoteId() {
		return voteId;
	}
	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getVoteName() {
		return voteName;
	}
	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
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
	public Integer getAudit() {
		return audit;
	}
	public void setAudit(Integer audit) {
		this.audit = audit;
	}
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
}
