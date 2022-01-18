package com.fallframework.platform.starter.pay.alipay.model;

import com.alipay.api.domain.GoodsDetail;

/**
 * 统一收单线下交易预创建
 *
 * @author zhuangpf
 */
public class PrecreateRequest extends AliPayCommonRequest {

	private static final long serialVersionUID = 9044441513244162603L;

	/**
	 * 商户订单号。
	 * 由商家自定义，64个字符以内，仅支持字母、数字、下划线且需保证在商户端不重复。
	 */
	private String out_trade_no;

	/**
	 * 订单总金额，单位为元，精确到小数点后两位，取值范围为 [0.01,100000000]，金额不能为 0。
	 * 如果同时传入了【可打折金额】，【不可打折金额】，【订单总金额】三者，则必须满足如下条件：
	 * 【订单总金额】=【可打折金额】+【不可打折金额】
	 */
	private String total_amount;

	/**
	 * 订单标题。
	 * 注意：不可使用特殊字符，如 /，=，& 等。
	 */
	private String subject;

	/**
	 * 销售产品码。如果签约的是当面付快捷版，则传 OFFLINE_PAYMENT；其它支付宝当面付产品传 FACE_TO_FACE_PAYMENT；
	 * 不传则默认使用 FACE_TO_FACE_PAYMENT。
	 */
	private String product_code;

	/**
	 * 卖家支付宝用户 ID。 如果该值为空，则默认为商户签约账号对应的支付宝用户 ID。不允许收款账号与付款方账号相同
	 */
	private String seller_id;

	/**
	 * 订单附加信息。
	 * 如果请求时传递了该参数，将在异步通知、对账单中原样返回，同时会在商户和用户的pc账单详情中作为交易描述展示
	 */
	private String body;

	/**
	 * 订单包含的商品列表信息，为 JSON 格式，其它说明详见商品明细说明
	 */
	private GoodsDetail[] goods_detail;


}
