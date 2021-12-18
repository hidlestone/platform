package com.fallframework.platform.starter.pay.wechat.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 微信支付接口的 基础 参数
 * <p>
 * 返回值中，java类中字段可以比 微信返回的xml结果少字段， 在WechatPayClient中对返回值计算签名的时候，是对xml进行校验签名。
 * <p>
 * WechatPayClient.convert转换， 会处理data类型
 */
@Getter
@Setter
public class WechatPayDto {

	// 公众账号ID
	protected String appId;

	// 随机字符串
	protected String nonceStr;

	// 签名
	protected String sign;

	// 微信支付 商户号
	protected String mchId;

}
