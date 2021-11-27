package com.fallframework.platform.starter.mvc.model;

/**
 * 权限类型
 */
public enum AuthcTypeEnum {

	anon("anon"),
	auth("auth");

	private String type;

	AuthcTypeEnum(String type) {
		this.type = type;
	}
}
