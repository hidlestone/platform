package com.fallframework.platform.starter.pay.wechat.enums;

/**
 * 支付类型
 */
public enum TradeTypeEnum {

	JSAPI("JSAPI", "JSAPI支付"),
	NATIVE("NATIVE", "Native支付"),
	APP("APP", "APP支付");

	private String code;
	private String name;

	TradeTypeEnum(String code, String name) {
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
