package com.xiaoshabao.webframework.ui.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * 表单返回数据
 */
public class TemplateData {
	/** 数据返回是否正常 */
	private boolean success;
	/** 返回信息 */
	private String message;

	private String contentHtml;

	/**
	 * 头部引用
	 */
	private Set<String> header=new HashSet<String>();

	// -------------------
	/** 生成的html代码 */
	private String html;

	public TemplateData() {
	}

	public TemplateData(boolean success) {
		this.success = success;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getContentHtml() {
		return contentHtml;
	}

	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}

	public void setHeader(Set<String> header) {
		this.header = header;
	}

	public Set<String> getHeader() {
		return header;
	}

}
