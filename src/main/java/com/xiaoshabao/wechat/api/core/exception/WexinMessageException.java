package com.xiaoshabao.wechat.api.core.exception;

/**
 * 异常消息信息
 * <p>
 * 返回一些可知的异常信息
 * </p>
 */
public class WexinMessageException extends WexinReqException {

	private static final long serialVersionUID = 1L;
	
	public WexinMessageException(String message) {
		super(message);
	}

	public WexinMessageException(Throwable cause) {
		super(cause);
	}

	public WexinMessageException(String message, Throwable cause) {
		super(message, cause);
	}
}
