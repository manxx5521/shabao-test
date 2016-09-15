package com.xiaoshabao.webframework.ui.exception;

import com.xiaoshabao.baseframework.exception.ServiceException;

/**
 * 校验错误
 */
public class ValidationException extends ServiceException {
	private static final long serialVersionUID = 1L;
	private ValidationError error;

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(ValidationError errors) {
		super(errors.getMessage());
		this.error = errors;
	}

	public ValidationError getValidationError() {
		return this.error;
	}

}
