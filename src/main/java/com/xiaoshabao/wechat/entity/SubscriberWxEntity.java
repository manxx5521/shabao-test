package com.xiaoshabao.wechat.entity;

import java.util.Date;

/**
 * 关注取消实体类
 */
public class SubscriberWxEntity {
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 微信用户id
	 */
	private String openid;
	/**
	 * 类型1关注、2取消关注
	 */
	private Integer type;
	/**
	 * 微信id
	 */
	private Integer accountId;
	/**
	 * 操作时间
	 */
	private Date updateTime;

	public SubscriberWxEntity() {
	}

	public SubscriberWxEntity(Integer accountId,String openid, Integer type) {
		this.accountId=accountId;
		this.openid = openid;
		this.type = type;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
