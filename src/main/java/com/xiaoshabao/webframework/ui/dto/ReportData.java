package com.xiaoshabao.webframework.ui.dto;

import java.util.HashSet;
import java.util.Set;

	
public class ReportData {
	
	private String reportHtml;
	
	private String reportScript;
	
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

	public String getReportScript() {
		return reportScript;
	}

	public void setReportScript(String reportScript) {
		this.reportScript = reportScript;
	}

	public Set<String> getHeader() {
		return header;
	}

	public void setHeader(Set<String> header) {
		this.header = header;
	}
	
	
}
