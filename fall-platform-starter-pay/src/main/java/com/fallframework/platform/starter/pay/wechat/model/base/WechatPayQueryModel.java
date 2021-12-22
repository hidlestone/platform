package com.fallframework.platform.starter.pay.wechat.model.base;

/**
 * 微信支付订单查询 接口参数
 * <p/>
 * 两个参数 至少传一个
 *
 * @author zhuangpf
 */
public class WechatPayQueryModel extends WechatPayBaseModel {

	private static final long serialVersionUID = -7951564247375988960L;

	// 商户订单号：商户系统内部的订单号，当没提供transaction_id时需要传这个。
	private String outTradeNo;

	// 微信订单号，优先使用
	private String transactionId;

}
