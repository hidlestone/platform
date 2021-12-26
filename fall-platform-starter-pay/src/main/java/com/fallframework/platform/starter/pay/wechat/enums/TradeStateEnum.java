package com.fallframework.platform.starter.pay.wechat.enums;

/**
 * 交易状态：
 * <p>
 * SUCCESS--支付成功
 * REFUND--转入退款
 * NOTPAY--未支付
 * CLOSED--已关闭
 * REVOKED--已撤销(刷卡支付)
 * USERPAYING--用户支付中
 * PAYERROR--支付失败(其他原因，如银行返回失败)
 * ACCEPT--已接收，等待扣款
 * 支付状态机请见下单API页面
 */
public enum TradeStateEnum {

	SUCCESS("SUCCESS", "支付成功"),
	REFUND("REFUND", "转入退款"),
	NOTPAY("NOTPAY", "未支付"),
	REVOKED("REVOKED", "已撤销(刷卡支付)"),
	USERPAYING("USERPAYING", "用户支付中"),
	PAYERROR("PAYERROR", "支付失败(其他原因，如银行返回失败)"),
	ACCEPT("ACCEPT", "已接收");

	private String code;
	private String name;

	TradeStateEnum(String code, String name) {
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
