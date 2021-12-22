package com.fallframework.platform.starter.pay.wechat.service;

import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.fallframework.platform.starter.core.entity.response.ResponseResult;
import com.fallframework.platform.starter.pay.wechat.model.UnifiedOrderRequest;
import com.google.zxing.WriterException;
import org.jdom.JDOMException;

import java.io.IOException;

/**
 * NATIVE 支付<br/>
 * 商户系统按微信支付协议生成支付二维码，用户再用微信“扫一扫”完成支付的模式
 * <p>
 * 参考<br/>
 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_1<br/>
 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
 *
 * @author zhuangpf
 */
public interface WechatPayNativeService {

	/**
	 * 统一下单
	 * https://api.mch.weixin.qq.com/pay/unifiedorder
	 */
	ResponseResult<String> unifiedOrder(UnifiedOrderRequest request) throws HttpProcessException, JDOMException, IOException, WriterException;

	/**
	 * 查询订单
	 * https://api.mch.weixin.qq.com/pay/orderquery
	 */
	String orderQuery();

	/**
	 * 关闭订单
	 * https://api.mch.weixin.qq.com/pay/closeorder
	 */
	String closeOrder();

	/**
	 * 申请退款
	 * https://api.mch.weixin.qq.com/secapi/pay/refund
	 */
	String refund();

	/**
	 * 查询退款
	 * https://api.mch.weixin.qq.com/pay/refundquery
	 */
	String refundQuery();

	/**
	 * 下载交易账单
	 * https://api.mch.weixin.qq.com/pay/downloadbill
	 */
	String downloadBill();

}
