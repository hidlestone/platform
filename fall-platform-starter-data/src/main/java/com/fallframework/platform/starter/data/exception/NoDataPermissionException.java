package com.fallframework.platform.starter.data.exception;

/**
 * 无数据权限异常
 *
 * @author payn
 */
public class NoDataPermissionException extends RuntimeException {

	private static final long serialVersionUID = -6975314442375515736L;

	public NoDataPermissionException(String message) {
		super(message);
	}

	public NoDataPermissionException(String message, Throwable cause) {
		super(message, cause);
	}

}
