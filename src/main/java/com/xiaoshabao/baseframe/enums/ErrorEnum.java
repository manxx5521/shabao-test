package com.xiaoshabao.baseframe.enums;
/**
 * 错误信息
 */
public enum ErrorEnum implements ErrorInterface{
	ERROR(1,"系统错误，请重试"),
	INNER_ERROR(2,"系统内部错误，请重试"),
	/** 数据保存失败 */
	SAVE_ERROR(3,"数据保存失败");
	
	/** 代码 */
	private int code;
	/** 错误信息 */
	private String message;
	
	private ErrorEnum(int code,String message){
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
