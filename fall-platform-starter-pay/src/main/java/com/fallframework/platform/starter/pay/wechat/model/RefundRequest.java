package com.fallframework.platform.starter.pay.wechat.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class RefundRequest extends BaseEntityRequest {

	private static final long serialVersionUID = -6351309459379073170L;

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
	 * 微信生成的订单号，在支付通知中有返回
	 */
	private String transaction_id;

	/**
	 * 商户系统内部订单号，要求32个字符内（最少6个字符），只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号
	 * transaction_id、out_trade_no二选一，如果同时存在优先级：transaction_id> out_trade_no
	 */
	private String out_trade_no;

	/**
	 * 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
	 */
	private String out_refund_no;

	/**
	 * 订单总金额，单位为分，只能为整数，详见支付金额
	 */
	private Integer total_fee;

	/**
	 * 退款总金额，订单总金额，单位为分，只能为整数，详见支付金额
	 */
	private Integer refund_fee;

	/**
	 * 退款货币类型，需与支付一致，或者不填。符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	 */
	private String refund_fee_type;

	/**
	 * 若商户传入，会在下发给用户的退款消息中体现退款原因
	 * 注意：若订单退款金额≤1元，且属于部分退款，则不会在退款消息中体现退款原因
	 */
	private String refund_desc;

	/**
	 * 仅针对老资金流商户使用
	 * <p>
	 * REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
	 * <p>
	 * REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
	 */
	private String refund_account;

	/**
	 * 异步接收微信支付退款结果通知的回调地址，通知URL必须为外网可访问的url，不允许带参数
	 * 公网域名必须为https，如果是走专线接入，使用专线NAT IP或者私有回调域名可使用http
	 * <p>
	 * 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效。
	 */
	private String notify_url;

}
