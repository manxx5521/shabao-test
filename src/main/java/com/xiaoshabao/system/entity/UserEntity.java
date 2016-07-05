package com.xiaoshabao.system.entity;

import java.util.Date;

/**
 * 用户user实体类
 */
public class UserEntity {
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 用户名，昵称
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 登录状态：1可登录，0不可登录
	 */
	private Integer loginState;

	/**
	 * 资料修改时时间
	 */
	private Date updateTime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getLoginState() {
		return loginState;
	}

	public void setLoginState(Integer loginState) {
		this.loginState = loginState;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
