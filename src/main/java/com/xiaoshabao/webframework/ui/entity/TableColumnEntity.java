package com.xiaoshabao.webframework.ui.entity;

import java.io.Serializable;

public class TableColumnEntity extends FormField implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tableId;
	private String remark;

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
