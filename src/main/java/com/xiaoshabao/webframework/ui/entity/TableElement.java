package com.xiaoshabao.webframework.ui.entity;

import java.io.Serializable;

public class TableElement implements Serializable {
	private static final long serialVersionUID = 1L;

	private String tableId;
	private String fieldCode;
	private String fieldName;
	private Integer fieldType;
	private Integer fieldLength;
	private Integer fieldDecimal;
	private Integer isKey;
	private Integer isNull;
	private Integer isRef;
	private String refTable;
	private String refTableKey;
	private String refTableValue;
	private String refTableSql;
	private String remark;

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

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

	public Integer getFieldDecimal() {
		return fieldDecimal;
	}

	public void setFieldDecimal(Integer fieldDecimal) {
		this.fieldDecimal = fieldDecimal;
	}

	public Integer getIsKey() {
		return isKey;
	}

	public void setIsKey(Integer isKey) {
		this.isKey = isKey;
	}

	public Integer getIsNull() {
		return isNull;
	}

	public void setIsNull(Integer isNull) {
		this.isNull = isNull;
	}

	public Integer getIsRef() {
		return isRef;
	}

	public void setIsRef(Integer isRef) {
		this.isRef = isRef;
	}

	public String getRefTable() {
		return refTable;
	}

	public void setRefTable(String refTable) {
		this.refTable = refTable;
	}

	public String getRefTableKey() {
		return refTableKey;
	}

	public void setRefTableKey(String refTableKey) {
		this.refTableKey = refTableKey;
	}

	public String getRefTableValue() {
		return refTableValue;
	}

	public void setRefTableValue(String refTableValue) {
		this.refTableValue = refTableValue;
	}

	public String getRefTableSql() {
		return refTableSql;
	}

	public void setRefTableSql(String refTableSql) {
		this.refTableSql = refTableSql;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(Integer fieldLength) {
		this.fieldLength = fieldLength;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
