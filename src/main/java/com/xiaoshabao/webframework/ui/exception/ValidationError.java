package com.xiaoshabao.webframework.ui.exception;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 校验错误保存对象
 */
public class ValidationError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Map<String, String> fielderror = new HashMap<String, String>();

	private String target;

	private String message;
	private int errorCode;

	private boolean haserror = false;

	public ValidationError() {
		super();
	}

	public ValidationError(String message) {
		this.message = message;
		haserror = true;
	}

	public void setFieldError(String field, String error) {
		this.fielderror.put(field, error);
		haserror = true;
	}

	public void clearFildError() {
		this.fielderror.clear();
		this.message = "";
		haserror = false;
	}

	public Map<String, String> getFieldMessage() {
		return fielderror;
	}

	public void setFieldMessage(Map<String, String> fieldMessage) {
		this.fielderror = fieldMessage;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public boolean hasError() {
		return this.haserror;
	}

}
