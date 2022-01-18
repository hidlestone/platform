package com.fallframework.platform.starter.pay.wechat.enums;

public enum SignTypeEnum {

	MD5("MD5", "MD5"),
	HMACSHA256("HMACSHA256", "HMACSHA256");

	private String code;
	private String name;

	SignTypeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
