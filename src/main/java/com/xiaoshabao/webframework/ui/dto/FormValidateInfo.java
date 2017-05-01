package com.xiaoshabao.webframework.ui.dto;

/**
 * 验证消息存放
 */
public class FormValidateInfo {

	private boolean success;
	
	private String message;
	
	public FormValidateInfo() {
	}
	
	public FormValidateInfo(boolean success) {
		this.success = success;
	}
	
	public FormValidateInfo(String message) {
		this.success = false;
		this.message = message;
	}

	public FormValidateInfo(boolean success, String message) {
		this.success = success;
		this.message = message;
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
