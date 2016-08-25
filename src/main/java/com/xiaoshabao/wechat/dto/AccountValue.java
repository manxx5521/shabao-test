package com.xiaoshabao.wechat.dto;

/**
 * 用户帐号关联<br/>
 */
public class AccountValue {

	/**
	 * 所在部门
	 */
	private String departId;

	/**
	 * 微信帐号在系统对应id
	 */
	private Integer accountId;

	/**
	 * 微信应用的名字
	 */
	private String appName;
	
	public AccountValue() {
	}

	public AccountValue(Integer accountId, String appName) {
		this.accountId = accountId;
		this.appName = appName;
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

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

}
