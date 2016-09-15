package com.xiaoshabao.webframework.ui.entity;

public class SelectElementDef extends FormElementDef {
	/** 类型 */
	private Integer sourcetype;
	/** 要枚举的表 */
	private String tablename;
	/** 查询表返回的key */
	private String codecolname;
	/** 查询表返回的value,显示的值 */
	private String namecolname;
	/** 条件 */
	private String condition;
	/** 过滤 */
	private String filtercol;
	/**
	 * 数据源sql
	 */
	private String sql;
	
	/**
	 * 是否显示全部
	 */
	private boolean TextAll=false;
	/**
	 * 是否初始化值
	 */
	private boolean initData=true;

	public Integer getSourcetype() {
		return sourcetype;
	}

	public void setSourcetype(Integer sourcetype) {
		this.sourcetype = sourcetype;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getCodecolname() {
		return codecolname;
	}

	public void setCodecolname(String codecolname) {
		this.codecolname = codecolname;
	}

	public String getNamecolname() {
		return namecolname;
	}

	public void setNamecolname(String namecolname) {
		this.namecolname = namecolname;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getFiltercol() {
		return filtercol;
	}

	public void setFiltercol(String filtercol) {
		this.filtercol = filtercol;
	}

	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @param sql the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	public boolean isTextAll() {
		return TextAll;
	}

	public void setTextAll(boolean textAll) {
		TextAll = textAll;
	}

	public boolean isInitData() {
		return initData;
	}

	public void setInitData(boolean initData) {
		this.initData = initData;
	}
	
}
