package com.xiaoshabao.webframe.entity;

public class ImageElement {
	/**
	 * 类型 1完整SQL形式
	 */
	private String type;
	/**
	 * SQL带有id、text和url
	 */
	private String sql;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
}
