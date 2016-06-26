package com.xiaoshabao.webframe.enums;

public enum AJAXResultEnum {
	INNER_ERROR(false,"系统内部异常，请重试"),
	NO_LOGIN(false,"您未登录系统，请登录"),
	NO_PERMISSIONS(false,"您没有权限操作");
	/**
	 * 是否成功
	 */
	private boolean success;
	/**
	 * 返回信息
	 */
	private String message;
	
	private AJAXResultEnum(boolean success,String message){
		this.success=success;
		this.message=message;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}
}
