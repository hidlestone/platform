package com.fallframework.platform.starter.pay.wechat.model.base;

/**
 * 微信退款接口 参数
 *
 * @author zhuangpf
 */
public class WechatPayRefundModel extends WechatPayBaseModel {

	private static final long serialVersionUID = 5215660026652112901L;

	// 非必填 设备号  终端设备号(门店号或收银设备ID)
	private String deviceInfo;

	// 微信订单号，优先使用
	private String transactionId;

	// 商户订单号， 商户内部系统的订单号，  32个字符内，可包含字母。 微信支付要求商户订单号保持唯一性
	private String outTradeNo;

	// 商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
	private String outRefundNo;

	// 订单金额，精确到分
	private int totalFee;

	// 退款金额 ，精确到分
	private int refundFee;

	// 货币种类	默认人民币
	private String refundFeeType;


	// 操作员帐号, 默认为商户号
	private String opUserId;

}
