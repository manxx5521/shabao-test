package com.xiaoshabao.baseframe.exception;

/**
 * DAO层 异常信息提示
 */
public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DaoException(String mess) {
		super(mess);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
}
