package com.fallframework.platform.starter.pay.wechat.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;

/**
 * 支付结果通知
 * 支付完成后，微信会把相关支付结果及用户信息通过数据流的形式发送给商户，商户需要接收处理，并按文档规范返回应答。
 *
 * @author zhuangpf
 */
public class NotifyResultRequest extends BaseEntityRequest {

	private static final long serialVersionUID = 2706383063976719880L;

	/**
	 * 微信支付分配的公众账号ID（企业号corpid即为此appid）
	 */
	private String appid;

	/**
	 * 微信支付分配的商户号
	 */
	private String mch_id;

	/**
	 * 微信支付分配的终端设备号
	 */
	private String device_info;

	/**
	 * 随机字符串，不长于32位
	 */
	private String nonce_str;

	/**
	 * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
	 */
	private String sign_type;

	/**
	 * SUCCESS/FAIL
	 */
	private String result_code;

	/**
	 * 错误返回的信息描述
	 */
	private String err_code;

	/**
	 * 错误返回的信息描述
	 */
	private String err_code_des;

	/**
	 * 用户在商户appid下的唯一标识
	 */
	private String openid;

	/**
	 * 用户是否关注公众账号，Y-关注，N-未关注
	 */
	private String is_subscribe;

	/**
	 * JSAPI、NATIVE、APP
	 */
	private String trade_type;

	/**
	 * 银行类型，采用字符串类型的银行标识，银行类型见银行列表
	 */
	private String bank_type;

	/**
	 * 订单总金额，单位为分
	 */
	private Integer total_fee;

	/**
	 * 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
	 */
	private String settlement_total_fee;

	/**
	 * 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	 */
	private String fee_type;

	/**
	 * 现金支付金额订单现金支付金额，详见支付金额
	 */
	private Integer cash_fee;

	/**
	 * 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	 */
	private String cash_fee_type;

	/**
	 * 代金券金额<=订单金额，订单金额-代金券金额=现金支付金额，详见支付金额
	 */
	private Integer coupon_fee;

	/**
	 * 代金券使用数量
	 */
	private Integer coupon_count;

	/**
	 * CASH--充值代金券
	 * NO_CASH---非充值代金券
	 * <p>
	 * 并且订单使用了免充值券后有返回（取值：CASH、NO_CASH）。$n为下标，该笔订单使用多张代金券时，从0开始编号，举例：coupon_type_0、coupon_type_1
	 * <p>
	 * 注意：只有下单时订单使用了优惠，回调通知才会返回券信息。
	 * 下列情况可能导致订单不可以享受优惠：可能情况。
	 */
	private String coupon_type_0;

	/**
	 * 代金券ID,$n为下标，该笔订单使用多张代金券时，从0开始编号，举例：coupon_id_0、coupon_id_1
	 * 注意：只有下单时订单使用了优惠，回调通知才会返回券信息。
	 * 下列情况可能导致订单不可以享受优惠：可能情况。
	 */
	private String coupon_id_0;

	/**
	 * 单个代金券支付金额,$n为下标，从0开始编号
	 */
	private String coupon_fee_0;

	/**
	 * 微信支付订单号
	 */
	private String transaction_id;

	/**
	 * 商户系统内部订单号，要求32个字符内（最少6个字符），只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号
	 */
	private String out_trade_no;

	/**
	 * 商家数据包，原样返回
	 */
	private String attach;

	/**
	 * 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	 */
	private String time_end;

}
