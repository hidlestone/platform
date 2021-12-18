package com.fallframework.platform.starter.pay.api;

import com.fallframework.platform.starter.core.entity.response.ResponseResult;
import com.fallframework.platform.starter.pay.model.PayNotifyResponse;

/**
 * 支付接口定义， 微信，支付宝支付逻辑均 继承此接口。
 */
public interface PayService {

	/**
	 * 统一的支付接口
	 */
	ResponseResult pay();

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
