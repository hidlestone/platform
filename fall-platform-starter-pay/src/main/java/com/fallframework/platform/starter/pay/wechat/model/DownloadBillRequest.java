package com.fallframework.platform.starter.pay.wechat.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;

/**
 * 下载交易账单请求参数
 *
 * @author zhuangpf
 */
public class DownloadBillRequest extends BaseEntityRequest {

	private static final long serialVersionUID = 5532376778203942021L;

	/**
	 * 微信支付分配的公众账号ID（企业号corpid即为此appid）
	 */
	private String appid;

	/**
	 * 微信支付分配的商户号
	 */
	private String mch_id;

	/**
	 * 随机字符串，不长于32位。推荐随机数生成算法
	 */
	private String nonce_str;

	/**
	 * 签名，详见签名生成算法
	 */
	private String sign;

	/**
	 * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
	 */
	private String sign_type;

	/**
	 * 下载对账单的日期，格式：20140603
	 */
	private String bill_date;

	/**
	 * ALL（默认值），返回当日所有订单信息（不含充值退款订单）
	 * <p>
	 * SUCCESS，返回当日成功支付的订单（不含充值退款订单）
	 * <p>
	 * REFUND，返回当日退款订单（不含充值退款订单）
	 * <p>
	 * RECHARGE_REFUND，返回当日充值退款订单
	 */
	private String bill_type;

	/**
	 * 非必传参数，固定值：GZIP，返回格式为.gzip的压缩包账单。不传则默认为数据流形式。
	 */
	private String tar_type;

}
