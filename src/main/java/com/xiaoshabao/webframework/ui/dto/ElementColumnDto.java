package com.xiaoshabao.webframework.ui.dto;

import com.xiaoshabao.webframework.ui.entity.TableColumnEntity;
import com.xiaoshabao.webframework.ui.entity.TemplateElementEntity;

/**
 * 需要的元素属性
 */
public class ElementColumnDto extends TemplateElementEntity{
	
	private TableColumnEntity column;

	public TableColumnEntity getColumn() {
		return column;
	}

	public void setColumn(TableColumnEntity column) {
		this.column = column;
	}
	
}
