package com.xiaoshabao.webframework.ui.dto;

import java.util.List;

import com.xiaoshabao.webframework.ui.entity.TableColumnEntity;
import com.xiaoshabao.webframework.ui.entity.TableEntity;

public class TableDto extends TableEntity {

	private List<TableColumnEntity> tableElements;

	public List<TableColumnEntity> getTableElements() {
		return tableElements;
	}

	public void setTableElements(List<TableColumnEntity> tableElements) {
		this.tableElements = tableElements;
	}

}
