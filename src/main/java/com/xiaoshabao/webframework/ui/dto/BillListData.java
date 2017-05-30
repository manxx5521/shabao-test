package com.xiaoshabao.webframework.ui.dto;

import java.util.List;


/**
 * list界面返回结果值
 */
public class BillListData {
	/**
	 * JSP文件目录(基于/webframe/ui/)
	 */
	private String pagePath;
	
	/**
	 * 头部title
	 */
	private String title;
	/**
	 * 头部引用
	 */
	private String headerScript;
	/**
	 * 头部引用(放在上边，跟在CSS之后加载)
	 */
	private String headerBeforeScript;
	/**
	 * 头部引用
	 */
	private String headerCSS;
	/**
	 * 模版html代码
	 */
	private String templateHtml;
	
	/**
	 * reprot代码
	 */
	private String reportHtml;
	/**
	 * report脚本
	 */
	private String reportScript;
	
	private List<ButtonDto> buttons;
	
	public String getHeaderScript() {
		return headerScript;
	}
	public void setHeaderScript(String headerScript) {
		this.headerScript = headerScript;
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
	public String getPagePath() {
		return pagePath;
	}
	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHeaderBeforeScript() {
		return headerBeforeScript;
	}
	public void setHeaderBeforeScript(String headerBeforeScript) {
		this.headerBeforeScript = headerBeforeScript;
	}
	public List<ButtonDto> getButtons() {
		return buttons;
	}
	public void setButtons(List<ButtonDto> buttons) {
		this.buttons = buttons;
	}
}
