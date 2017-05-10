package com.xiaoshabao.webframework.ui.entity;

public class ReportEntity {
	private String reportId;
	private String tableId;
	private String reportName;
	private String reportEngine;
	private boolean isSumRow;
	private Integer rowNum;
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getReportEngine() {
		return reportEngine;
	}
	public void setReportEngine(String reportEngine) {
		this.reportEngine = reportEngine;
	}
	public boolean isSumRow() {
		return isSumRow;
	}
	public void setSumRow(boolean isSumRow) {
		this.isSumRow = isSumRow;
	}
	public Integer getRowNum() {
		return rowNum;
	}
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}
	
	
}
