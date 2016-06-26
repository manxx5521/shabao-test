package com.xiaoshabao.system.entity;
/**
 * 部门
 */
public class DepartEntity {
	private String departId;
	private String departName;
	private String departType;
	private String departFrame;
	private String parentDepartId;
	private Integer departLevel;
	private Integer used;
	private Integer childNum;
	private Integer orderNo;
	
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getDepartType() {
		return departType;
	}
	public void setDepartType(String departType) {
		this.departType = departType;
	}
	public String getDepartFrame() {
		return departFrame;
	}
	public void setDepartFrame(String departFrame) {
		this.departFrame = departFrame;
	}
	public String getParentDepartId() {
		return parentDepartId;
	}
	public void setParentDepartId(String parentDepartId) {
		this.parentDepartId = parentDepartId;
	}
	public Integer getDepartLevel() {
		return departLevel;
	}
	public void setDepartLevel(Integer departLevel) {
		this.departLevel = departLevel;
	}
	public Integer getChildNum() {
		return childNum;
	}
	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}
	
	public Integer getUsed() {
		return used;
	}
	public void setUsed(Integer used) {
		this.used = used;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
}
