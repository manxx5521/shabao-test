package com.xiaoshabao.webframework.ui.dto;

import com.xiaoshabao.webframework.ui.entity.ReportColumnEntity;
import com.xiaoshabao.webframework.ui.entity.TableColumnEntity;

public class ReportColumnDto extends ReportColumnEntity{
	
	private TableColumnEntity tableColumn;

	public TableColumnEntity getTableColumn() {
		return tableColumn;
	}

	public void setTableColumn(TableColumnEntity tableColumn) {
		this.tableColumn = tableColumn;
	}
	
}
