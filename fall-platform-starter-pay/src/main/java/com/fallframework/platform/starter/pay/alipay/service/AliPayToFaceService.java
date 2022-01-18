package com.fallframework.platform.starter.pay.alipay.service;

/**
 * https://opendocs.alipay.com/open/194/105072
 * <p>
 * 支付宝-当面付
 * <p>
 * 当面付包括付款码支付和扫码支付两种收款方式。适用于线下实体店支付、面对面支付、自助售货机等场景。
 * 付款码支付：商家使用扫码枪或其他扫码机具扫描用户出示的付款码，来实现收款。
 * 扫码支付：商家提供收款二维码，由用户通过支付宝扫码支付，来实现收款。
 */
public interface AliPayToFaceService {

	void precreate();

}
