package com.xiaoshabao.shabaowebtest.dto;
/**
 * 返回JSON结果
 */
public class SeckillResult<T> {
	private boolean success;
	private T data;
	private String error;
	public SeckillResult() {
	}
	
	public SeckillResult(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	public SeckillResult(boolean success, String error) {
		this.success = success;
		this.error = error;
	}

	/**
	 * 获得 success
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 设置 success
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * 获得 data
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * 设置 data
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * 获得 error
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * 设置 error
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
	
}
