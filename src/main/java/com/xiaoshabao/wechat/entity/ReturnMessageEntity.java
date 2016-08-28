package com.xiaoshabao.wechat.entity;

import java.util.Date;

/**
 * 回复消息 
 */
public class ReturnMessageEntity {
	
	private Integer messageId;
	/** wx_return_template的id */
	private Integer templateId;
	/** 帐号 1时为全部 */
	private Integer accountId;
	/** 回复内容 */
	private String content;
	/** 排序 1最优先 */
	private Integer orderNo;
	
	private Integer updateUser;
	
	private Date updateTime;

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
