package com.xiaoshabao.baseframe.util.jdbc;

/**
 * jdbc异常信息类
 */
public class XjdbcException extends Exception {

	private static final long serialVersionUID = 1L;

	public XjdbcException(String mess) {
		super(mess);
	}
	
	public XjdbcException(String message, Throwable cause) {
		super(message, cause);
	}

}
