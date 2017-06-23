package com.xiaoshabao.webframework.ui.entity;

public class ReportColumnEntity {
	private String reportId;
	private String columnId;
	private String elementId;
	private String title;
	private String titleGroup1;
	private String titleGroup2;
	private String titleGroup3;
	private String titleGroup4;
	/** 是否是超链接*/
	private boolean isHref;
	private String extParams;
	private boolean isDisplay;
	private boolean isUsed;
	private Integer orderNo;
	
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleGroup1() {
		return titleGroup1;
	}
	public void setTitleGroup1(String titleGroup1) {
		this.titleGroup1 = titleGroup1;
	}
	public String getTitleGroup2() {
		return titleGroup2;
	}
	public void setTitleGroup2(String titleGroup2) {
		this.titleGroup2 = titleGroup2;
	}
	public String getTitleGroup3() {
		return titleGroup3;
	}
	public void setTitleGroup3(String titleGroup3) {
		this.titleGroup3 = titleGroup3;
	}
	public String getTitleGroup4() {
		return titleGroup4;
	}
	public void setTitleGroup4(String titleGroup4) {
		this.titleGroup4 = titleGroup4;
	}
	public boolean isDisplay() {
		return isDisplay;
	}
	public void setDisplay(boolean isDisplay) {
		this.isDisplay = isDisplay;
	}
	public boolean isUsed() {
		return isUsed;
	}
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	public String getExtParams() {
		return extParams;
	}
	public void setExtParams(String extParams) {
		this.extParams = extParams;
	}
	public boolean isHref() {
		return isHref;
	}
	public void setHref(boolean isHref) {
		this.isHref = isHref;
	}
	
}
