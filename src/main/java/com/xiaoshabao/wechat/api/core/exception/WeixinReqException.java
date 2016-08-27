package com.xiaoshabao.wechat.api.core.exception;

/**
 * 常规异常信息
 * <p>
 * 微信异常信息类，抛出请求微信API时的异常信息
 * </p>
 */
public class WeixinReqException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WeixinReqException(String message) {
		super(message);
	}

	public WeixinReqException(Throwable cause) {
		super(cause);
	}

	public WeixinReqException(String message, Throwable cause) {
		super(message, cause);
	}
}
