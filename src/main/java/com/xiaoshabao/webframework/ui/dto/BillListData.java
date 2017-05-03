package com.xiaoshabao.webframework.ui.dto;


/**
 * list界面返回结果值
 */
public class BillListData {
	/**
	 * 头部引用
	 */
	private String headerJS;
	/**
	 * 头部引用
	 */
	private String headerCSS;
	/**
	 * 模版html代码
	 */
	private String templateHtml;
	
	public String getHeaderJS() {
		return headerJS;
	}
	public void setHeaderJS(String headerJS) {
		this.headerJS = headerJS;
	}
	public String getHeaderCSS() {
		return headerCSS;
	}
	public void setHeaderCSS(String headerCSS) {
		this.headerCSS = headerCSS;
	}
	public String getTemplateHtml() {
		return templateHtml;
	}
	public void setTemplateHtml(String templateHtml) {
		this.templateHtml = templateHtml;
	}

}
