package com.fallframework.platform.starter.pay.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付过程状态
 */
public enum PayProcessStatus {
	CREATE_PAYMENT(0, "创建支付订单"),
	CREATE_PAYMENT_SUCCESS(1, "创建支付订单成功"),
	PAY_SUCCESS(2, "支付成功"),
	CREATE_PAYMENT_FAIL(3, "创建支付订单失败"),
	PAY_FAIL(4, "支付失败"),
	PAY_CHECKING(5, "支付中"),
	PAY_CLOSE(6, "支付订单关闭"),
	REFUND_PART(7, "部分退款"),
	REFUND_SUCCESS(8, "全部退款"),;

	private int value;
	private String name;

	PayProcessStatus(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static PayProcessStatus getPayStatus(int status) {
		for (PayProcessStatus it : PayProcessStatus.values()) {
			if (it.getValue() == status) {
				return it;
			}
		}
		return null;
	}

	private static Map<Integer, String> map = new HashMap<Integer, String>(PayProcessStatus.values().length);

	static {
		for (PayProcessStatus payProcessStatus : PayProcessStatus.values()) {
			map.put(payProcessStatus.getValue(), payProcessStatus.getName());
		}
	}

	public static Map<Integer, String> getPayStatusMap() {
		return map;
	}
}
