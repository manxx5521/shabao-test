package com.xiaoshabao.webframework.ui.dto;

import com.xiaoshabao.webframework.ui.entity.BillEngineEntity;
import com.xiaoshabao.webframework.ui.entity.BillEntity;

public class BillDto extends BillEntity{
	
	BillEngineEntity billEngineEntity;

	public BillEngineEntity getBillEngineEntity() {
		return billEngineEntity;
	}

	public void setBillEngineEntity(BillEngineEntity billEngineEntity) {
		this.billEngineEntity = billEngineEntity;
	}

}
