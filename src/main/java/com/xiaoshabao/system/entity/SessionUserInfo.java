package com.xiaoshabao.system.entity;
/**
 * 存放个人信息sessionBean
 */
public class SessionUserInfo {
	/** 用户id */
	private Integer userId;
	/** 用户名，昵称 */
	private String userName;
	/** 部门 */
	private String departId;
	/** 权限部门 */
	private String priDepart;
	/** 部门frame */
	private String priFrame;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public String getPriDepart() {
		return priDepart;
	}
	public void setPriDepart(String priDepart) {
		this.priDepart = priDepart;
	}
	public String getPriFrame() {
		return priFrame;
	}
	public void setPriFrame(String priFrame) {
		this.priFrame = priFrame;
	}
}
