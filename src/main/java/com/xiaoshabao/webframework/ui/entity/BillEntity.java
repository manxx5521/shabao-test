package com.xiaoshabao.webframework.ui.entity;
/**
 * 单据
 */
public class BillEntity {
	private String billId;
	private String billName;
	private String billClass;
	private String billEngine;
	private Integer orderNo;
	private Integer state;
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getBillName() {
		return billName;
	}
	public void setBillName(String billName) {
		this.billName = billName;
	}
	public String getBillClass() {
		return billClass;
	}
	public void setBillClass(String billClass) {
		this.billClass = billClass;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getBillEngine() {
		return billEngine;
	}
	public void setBillEngine(String billEngine) {
		this.billEngine = billEngine;
	}
	
}
