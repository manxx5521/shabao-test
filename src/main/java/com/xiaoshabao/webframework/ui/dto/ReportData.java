package com.xiaoshabao.webframework.ui.dto;

import java.util.HashSet;
import java.util.Set;

	
public class ReportData {
	
	private String reportHtml;
	
	private String reportJS;
	
	/**
	 * 头部引用
	 */
	private Set<String> header=new HashSet<String>();

	public String getReportHtml() {
		return reportHtml;
	}

	public void setReportHtml(String reportHtml) {
		this.reportHtml = reportHtml;
	}

	public String getReportJS() {
		return reportJS;
	}

	public void setReportJS(String reportJS) {
		this.reportJS = reportJS;
	}

	public Set<String> getHeader() {
		return header;
	}

	public void setHeader(Set<String> header) {
		this.header = header;
	}
	
	
}
