package com.xiaoshabao.webframework.ui.entity;

public class FormField {
	private String fieldCode;
	private String fieldName;
	private Integer fieldType;
	private Integer fieldAttr;
	private Integer fieldLength;
	private Integer fieldDecimal;
	private boolean isKey;
	/**是否非空**/
	private boolean isNull;
	private boolean isRef;
	private String refTable;
	
	public String getFieldCode() {
		return fieldCode;
	}
	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Integer getFieldType() {
		return fieldType;
	}
	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}
	public Integer getFieldAttr() {
		return fieldAttr;
	}
	public void setFieldAttr(Integer fieldAttr) {
		this.fieldAttr = fieldAttr;
	}
	public Integer getFieldLength() {
		return fieldLength;
	}
	public void setFieldLength(Integer fieldLength) {
		this.fieldLength = fieldLength;
	}
	public Integer getFieldDecimal() {
		return fieldDecimal;
	}
	public void setFieldDecimal(Integer fieldDecimal) {
		this.fieldDecimal = fieldDecimal;
	}
	public boolean isKey() {
		return isKey;
	}
	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}
	public boolean isNull() {
		return isNull;
	}
	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}
	public boolean isRef() {
		return isRef;
	}
	public void setRef(boolean isRef) {
		this.isRef = isRef;
	}
	public String getRefTable() {
		return refTable;
	}
	public void setRefTable(String refTable) {
		this.refTable = refTable;
	}
	
}
