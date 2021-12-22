package com.fallframework.platform.starter.pay.wechat.model.base;

import com.fallframework.platform.starter.pay.enums.TradeTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 微信统一下单 接口参数
 *
 * @author zhuangpf
 */
@Getter
@Setter
public class WechatPayPrePayModel extends WechatPayBaseModel {

	private static final long serialVersionUID = -2164434171296537853L;

	// 非必填 设备号  终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
	private String deviceInfo;

	// 商品描述  商品或支付简要描述
	private String body;

	// 商品名称 明细列表，json格式，传输签名前请务必使用CDATA标签将JSON文本串保护起来。
	private String detail;

	// 非必填 附加数据， 在查询Api和支付通知中 原样返回，该字段主要用于商户携带订单的自定义数据
	private String attach;

	// 商户订单号， 商户内部系统的订单号，  32个字符内，可包含字母。 微信支付要求商户订单号保持唯一性
	private String outTradeNo;

	//非必填 默认人民币  CNY
	private String feeType;

	// 订单总金额，单位为分
	private int totalFee;

	//  app和网页支付提交用户Id，native支付填调用微信支付Api的机器IP
	private String spbillCreateIp;

	//  非必填  交易开始时间  格式为yyyyMMddHHmmss
	private Date timeStart;

	// 非必填   交易结束时间  格式为yyyyMMddHHmmss
	private Date timeExpire;

	// 非必填  商品标记，代金券或立减优惠功能的参数
	private String goodsTag;

	// 通知地址， 接受微信支付异步通知回调地址，不能携带参数。
	private String notifyUrl;

	// 交易类型，  JSAPI,NATIVE,APP
	private TradeTypeEnum tradeType;

	// 非必填  商品Id trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
	private String productId;

	// 非必填  指定支付方式  no_credit--指定不能使用信用卡支付
	private String limitPay;

	// 非必填 trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识
	private String openId;

}
