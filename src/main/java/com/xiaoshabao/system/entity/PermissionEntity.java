package com.xiaoshabao.system.entity;

public class PermissionEntity {
	private Integer permission_id;
	private String name;
	private String permission_code;
	private Integer pid;
	private String des;
	private Integer type;

	public Integer getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(Integer permission_id) {
		this.permission_id = permission_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermission_code() {
		return permission_code;
	}

	public void setPermission_code(String permission_code) {
		this.permission_code = permission_code;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
