package com.fallframework.platform.starter.pay.common.api;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.pay.entity.PayOrder;
import com.fallframework.platform.starter.pay.entity.PayWechatConfig;
import com.fallframework.platform.starter.pay.model.PayNotifyResponse;
import com.fallframework.platform.starter.pay.wechat.exception.WechatPayException;

/**
 * 支付接口定义， 微信，支付宝支付逻辑均 继承此接口。
 */
public interface PayService {

	/**
	 * 统一的支付接口
	 */
	ResponseResult pay(PayWechatConfig config, PayOrder order) throws WechatPayException;

	/**
	 * 获取回调的内容
	 */
	String getOutTradeNo(String notify);

	/**
	 * 解析支付回调内容，校验签名
	 */
	PayNotifyResponse parse();

	/**
	 * 同步订单， 查询微信、支付宝 交易查询状态，来同步订单状态
	 */
	boolean synchronize();
}
