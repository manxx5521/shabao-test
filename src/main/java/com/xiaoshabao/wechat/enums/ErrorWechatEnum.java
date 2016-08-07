package com.xiaoshabao.wechat.enums;

import com.xiaoshabao.baseframe.enums.ErrorInterface;

public enum ErrorWechatEnum implements ErrorInterface{
	ERROR(1,"系统错误，请重试"),
	INNER_ERROR(2,"系统内部错误，请重试"),
	LOGIN_ERROR(9001,"自动登录失败，重新打开微信号或者是重新关注");
	
	/** 代码 */
	private int code;
	/** 错误信息 */
	private String message;
	
	private ErrorWechatEnum(int code,String message){
		this.code=code;
		this.message=message;
	}
	/**
	 * 代码
	 * @return
	 */
	public int getCode() {
		return code;
	}
	/**
	 * 错误信息
	 * @return
	 */
	public String getMessage() {
		return message;
	}

}
