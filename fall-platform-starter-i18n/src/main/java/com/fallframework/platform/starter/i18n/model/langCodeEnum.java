package com.fallframework.platform.starter.i18n.model;

/**
 * 语言编码枚举类
 */
public enum langCodeEnum {

	en("en"),
	zh_CN("zh_CN"),
	zh_TW("zh_TW");

	private String contentType;

	langCodeEnum(String contentType) {
		this.contentType = contentType;
	}

}
