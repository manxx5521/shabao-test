package com.xiaoshabao.system.entity;
/**
 * 存放个人信息sessionBean
 */
public class SessionUserInfo {

	/**
	 * 用户id
	 */
	private Integer user_id;
	/**
	 * 用户名，昵称
	 */
	private String user_name;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

}
