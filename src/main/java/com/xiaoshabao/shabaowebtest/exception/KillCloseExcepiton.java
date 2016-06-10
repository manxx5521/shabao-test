package com.xiaoshabao.shabaowebtest.exception;
/**
 * 秒杀关闭异常
 */
public class KillCloseExcepiton extends KillException{
	private static final long serialVersionUID = 3812708165794245714L;
	public KillCloseExcepiton(String message) {
		super(message);
	}
	public KillCloseExcepiton(String message, Throwable cause) {
		super(message, cause);
	}

	
	
}
