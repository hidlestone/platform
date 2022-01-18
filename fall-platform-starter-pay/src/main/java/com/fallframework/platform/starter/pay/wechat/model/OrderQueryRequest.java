package com.fallframework.platform.starter.pay.wechat.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 查询订单
 *
 * @author zhuangpf
 */
@Getter
@Setter
public class OrderQueryRequest extends BaseEntityRequest {

	private static final long serialVersionUID = 7213356297495706956L;

	/**
	 * 微信支付分配的公众账号ID（企业号corpid即为此appid）
	 */
	private String appid;

	/**
	 * 微信支付分配的商户号
	 */
	private String mch_id;

	/**
	 * 微信的订单号，建议优先使用
	 */
	private String transaction_id;

	/**
	 * 商户系统内部订单号，要求32个字符内（最少6个字符），只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号
	 */
	private String out_trade_no;

	/**
	 * 随机字符串，不长于32位。推荐随机数生成算法
	 */
	private String nonce_str;

	/**
	 * 通过签名算法计算得出的签名值，详见签名生成算法
	 */
	private String sign;

	/**
	 * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
	 */
	private String sign_type;

}
