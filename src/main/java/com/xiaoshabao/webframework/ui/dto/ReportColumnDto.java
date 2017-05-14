package com.xiaoshabao.webframework.ui.dto;

import com.xiaoshabao.webframework.ui.entity.ElementEntity;
import com.xiaoshabao.webframework.ui.entity.ReportColumnEntity;
import com.xiaoshabao.webframework.ui.entity.TableColumnEntity;

public class ReportColumnDto extends ReportColumnEntity{
	
	private TableColumnEntity tableColumn;
	
	private ElementEntity element;

	public TableColumnEntity getTableColumn() {
		return tableColumn;
	}

	public void setTableColumn(TableColumnEntity tableColumn) {
		this.tableColumn = tableColumn;
	}

	public ElementEntity getElement() {
		return element;
	}

	public void setElement(ElementEntity element) {
		this.element = element;
	}
	
}
