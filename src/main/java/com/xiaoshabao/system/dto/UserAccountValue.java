package com.xiaoshabao.system.dto;

/**
 * 用户帐号关联bean<br/>
 */
public class UserAccountValue{
	
	/**
	 * 用户的user_id
	 */
	private Integer user_id;
	
	/**
	 * 微信帐号在系统对应id
	 */
	private Integer account_id;
	
	/**
	 * 微信应用的名字
	 */
	private String app_name;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	
}
