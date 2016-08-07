package com.xiaoshabao.webframe.dto;

import com.xiaoshabao.baseframe.enums.ErrorInterface;
import com.xiaoshabao.webframe.enums.AJAXResultEnum;

/**
 * AJAX返回值
 * @param <T>
 */
public class AjaxResult {
	/**
	 * 结果是否成功
	 */
	private boolean success;
	/**
	 * 成功后返回数据
	 */
	private Object data;
	/**
	 * 返回信息
	 */
	private String message;
	
	public AjaxResult() {
	}
	/**
	 * 返回错误信息的构造方法
	 * @param success
	 * @param message
	 */
	public AjaxResult( String message) {
		this.success = false;
		this.message = message;
	}
	/**
	 * 一般返回成功信息构造
	 * @param success
	 * @param data
	 */
	public AjaxResult( ErrorInterface error) {
		this.success = false;
		this.message = error.getMessage();
	}
	/**
	 * 一般返回成功信息构造
	 * @param success
	 * @param data
	 */
	public AjaxResult( Object data) {
		this.success = true;
		this.data = data;
	}
	
	/**
	 * 一般返回成功信息构造
	 * @param success
	 * @param data
	 */
	public AjaxResult(boolean success, Object data) {
		this.success = success;
		this.data = data;
	}
	
	/**
	 * 返回信息的构造方法
	 * @param success
	 * @param message
	 */
	public AjaxResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	/**
	 * 返回信息的构造方法
	 * @param success
	 * @param message
	 */
	public AjaxResult(boolean success, String message,Object data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}
	
	/**
	 * 返回错误信息的构造方法
	 * @param success
	 * @param message
	 */
	public AjaxResult(AJAXResultEnum result) {
		this.success = result.isSuccess();
		this.message = result.getMessage();
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
