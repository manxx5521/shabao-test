package com.xiaoshabao.webframework.ui.entity;

/**
 * 单据引擎设置
 */
public class BillEngineEntity {
	/** 单据的引擎 */
	private String billEngine;
	
	/** 列表界面使用的引擎 */
	private String billEngineName;
	
	/** 列表界面使用的引擎 */
	private String listEngine;
	
	/** 视图界面使用的引擎 */
	private String viewEngine;
	
	private Integer orderNo;
	
	private boolean isUsed;

	public String getBillEngine() {
		return billEngine;
	}

	public void setBillEngine(String billEngine) {
		this.billEngine = billEngine;
	}

	public String getBillEngineName() {
		return billEngineName;
	}

	public void setBillEngineName(String billEngineName) {
		this.billEngineName = billEngineName;
	}

	public String getListEngine() {
		return listEngine;
	}

	public void setListEngine(String listEngine) {
		this.listEngine = listEngine;
	}

	public String getViewEngine() {
		return viewEngine;
	}

	public void setViewEngine(String viewEngine) {
		this.viewEngine = viewEngine;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	
}
