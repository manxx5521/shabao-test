package com.xiaoshabao.system.entity;

import java.util.Date;

public class RoleEntity {
	/**
	 * 角色id
	 */
	private Integer role_id;
	/**
	 * 角色名
	 */
	private String name;
	/**
	 * 角色描述
	 */
	private String des;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
