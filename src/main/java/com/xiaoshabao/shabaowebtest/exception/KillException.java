package com.xiaoshabao.shabaowebtest.exception;
/**
 * 秒杀异常
 */
public class KillException extends RuntimeException {

	private static final long serialVersionUID = -8416990960208577180L;

	public KillException(String message) {
		super(message);
	}

	public KillException(String message, Throwable cause) {
		super(message, cause);
	}

}
