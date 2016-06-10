package com.xiaoshabao.shabaowebtest.exception;
/**
 * 重复秒杀异常
 */
public class KillRepeatException extends KillException{
	private static final long serialVersionUID = 9146637348199348474L;
	public KillRepeatException(String message) {
		super(message);
	}
	public KillRepeatException(String message, Throwable cause) {
		super(message, cause);
	}

	
	
}
