package com.fallframework.platform.starter.pay.alipay.enums;

public enum EquipStatusEnum {
	
	ON("10"),

	OFF("20"),

	NORMAL("30"),

	SLEEP("40"),

	AWAKE("41");

	private String value;

	private EquipStatusEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
