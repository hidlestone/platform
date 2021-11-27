package com.fallframework.platform.starter.core.entity.response;

/**
 * @author payn
 */
public interface ResultCode {
	
	// 操作是否成功：true-成功，false-失败
	boolean success();

	// 操作代码
	String code();

	// 提示信息
	String message();
}
