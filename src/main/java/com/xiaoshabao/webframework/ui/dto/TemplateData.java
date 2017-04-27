package com.xiaoshabao.webframework.ui.dto;

/**
 * 表单返回数据
 */
public class TemplateData {
	/** 数据返回是否正常 */
	private boolean success=false;
	/** 返回信息 */
	private String message;

	// -------------------
	/** 生成的html代码 */
	private String html;

	public TemplateData() {
	}
	public TemplateData(String message) {
		this.message = message;
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

}
