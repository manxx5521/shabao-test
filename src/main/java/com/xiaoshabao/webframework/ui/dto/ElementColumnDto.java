package com.xiaoshabao.webframework.ui.dto;

import com.xiaoshabao.webframework.ui.entity.ElementEntity;
import com.xiaoshabao.webframework.ui.entity.TableColumnEntity;
import com.xiaoshabao.webframework.ui.entity.TemplateElementEntity;

/**
 * 需要的元素属性
 */
public class ElementColumnDto extends TemplateElementEntity {

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
