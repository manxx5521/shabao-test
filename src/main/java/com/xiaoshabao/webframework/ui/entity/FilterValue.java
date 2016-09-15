package com.xiaoshabao.webframework.ui.entity;
/**
 * 组件数据过滤值
 * @author dell
 *
 */
public class FilterValue {
	/**
	 * 字段名称
	 */
	private String fieldname;
	/**
	 * 字段值
	 */
	private Object fieldvalue;

	/**
	 * @return the fieldname
	 */
	public String getFieldname() {
		return fieldname;
	}

	/**
	 * @param fieldname
	 *            the fieldname to set
	 */
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	/**
	 * @return the fieldvalue
	 */
	public Object getFieldvalue() {
		return fieldvalue;
	}

	/**
	 * @param fieldvalue
	 *            the fieldvalue to set
	 */
	public void setFieldvalue(Object fieldvalue) {
		this.fieldvalue = fieldvalue;
	}

}
