package com.xiaoshabao.system.entity;

import java.util.Date;

/**
 * 用户user实体类
 */
public class UserEntity {
	/**
	 * 用户id
	 */
	private Integer user_id;
	/**
	 * 登录名
	 */
	private String login_name;
	/**
	 * 用户名，昵称
	 */
	private String user_name;
	/**
	 * 密码
	 */
	private String user_password;
	/**
	 * 登录状态：1可登录，0不可登录
	 */
	private Integer login_state;

	/**
	 * 资料修改时时间
	 */
	private Date update_time;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public Integer getLogin_state() {
		return login_state;
	}

	public void setLogin_state(Integer login_state) {
		this.login_state = login_state;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

}
