package com.xiaoshabao.webframework.ui.dto;

import com.xiaoshabao.webframework.ui.entity.BillEntity;
import com.xiaoshabao.webframework.ui.entity.ListEntity;

public class BillListDto extends BillEntity{
	
	private ListEntity list;

	public ListEntity getList() {
		return list;
	}

	public void setList(ListEntity list) {
		this.list = list;
	}
	
}
