package com.fallframework.platform.starter.pay.enums;

/**
 * 支付类型
 */
public enum PayTypeEnum {

	WECHAT_PAY("WECHAT_PAY", "微信"),
	ALI_PAY("ALI_PAY", "支付宝");

	private String code;
	private String name;

	PayTypeEnum(String code, String name) {
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
