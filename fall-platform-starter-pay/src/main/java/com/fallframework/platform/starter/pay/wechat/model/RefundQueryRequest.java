package com.fallframework.platform.starter.pay.wechat.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 查询退款请求参数
 *
 * @author zhuangpf
 */
@Getter
@Setter
public class RefundQueryRequest extends BaseEntityRequest {

	private static final long serialVersionUID = -7587225943283554564L;

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
	 * 微信订单号查询的优先级是： refund_id > out_refund_no > transaction_id > out_trade_no
	 */
	private String transaction_id;

	/**
	 * 商户系统内部订单号，要求32个字符内（最少6个字符），只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号
	 */
	private String out_trade_no;

	/**
	 * 微信生成的退款单号，在申请退款接口有返回
	 */
	private String refund_id;

	/**
	 * 偏移量，当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录
	 */
	private String offset;
	
}
