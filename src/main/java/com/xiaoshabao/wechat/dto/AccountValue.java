package com.xiaoshabao.wechat.dto;

/**
 * 用户帐号关联<br/>
 */
public class AccountValue {

	/**
	 * 用户的user_id
	 */
	private Integer userId;

	/**
	 * 微信帐号在系统对应id
	 */
	private Integer accountId;

	/**
	 * 微信应用的名字
	 */
	private String appName;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

}
