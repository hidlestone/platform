package com.fallframework.platform.starter.guard.exception;

/**
 * 未知账户异常
 *
 * @author zhuangpf
 */
public class UnknownAccountException extends RuntimeException {

	public UnknownAccountException() {
	}

	public UnknownAccountException(String message) {
		super(message);
	}

	public UnknownAccountException(Throwable cause) {
		super(cause);
	}

	public UnknownAccountException(String message, Throwable cause) {
		super(message, cause);
	}

}
