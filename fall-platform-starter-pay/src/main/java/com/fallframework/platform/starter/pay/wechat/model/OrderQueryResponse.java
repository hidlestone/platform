package com.fallframework.platform.starter.pay.wechat.model;

import com.fallframework.platform.starter.core.entity.response.BaseEntityResponse;

/**
 * 查询订单响应信息
 *
 * @author zhuangpf
 */
public class OrderQueryResponse extends BaseEntityResponse {

	private static final long serialVersionUID = 7422368420051523811L;

	/**
	 * 微信支付分配的终端设备号
	 */
	private String device_info;

	/**
	 * 用户在商户appid下的唯一标识
	 */
	private String openid;

	/**
	 * 用户是否关注公众账号，Y-关注，N-未关注
	 */
	private String is_subscribe;

	/**
	 * 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY，详细说明见参数规定
	 */
	private String trade_type;

	/**
	 * SUCCESS--支付成功
	 * REFUND--转入退款
	 * NOTPAY--未支付
	 * CLOSED--已关闭
	 * REVOKED--已撤销(刷卡支付)
	 * USERPAYING--用户支付中
	 * PAYERROR--支付失败(其他原因，如银行返回失败)
	 * ACCEPT--已接收，等待扣款
	 * 支付状态机请见下单API页面
	 */
	private String trade_state;

	/**
	 * 银行类型，采用字符串类型的银行标识
	 */
	private String bank_type;

	/**
	 * 订单总金额，单位为分
	 */
	private Integer total_fee;

	/**
	 * 当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。
	 */
	private Integer settlement_total_fee;

	/**
	 * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	 */
	private String fee_type;

	/**
	 * 现金支付金额订单现金支付金额，详见支付金额
	 */
	private Integer cash_fee;

	/**
	 * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	 */
	private String cash_fee_type;

	/**
	 * “代金券”金额<=订单金额，订单金额-“代金券”金额=现金支付金额，详见支付金额
	 */
	private Integer coupon_fee;

	/**
	 * 代金券使用数量
	 */
	private Integer coupon_count;

	/**
	 * CASH：充值代金券
	 * <p>
	 * NO_CASH：非充值优惠券
	 * <p>
	 * 开通免充值券功能，并且订单使用了优惠券后有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_$0
	 */
	private String coupon_type_0;

	/**
	 * 代金券ID, $n为下标，从0开始编号
	 */
	private String coupon_id_0;

	/**
	 * 单个代金券支付金额, $n为下标，从0开始编号
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
	 * 附加数据，原样返回
	 */
	private String attach;

	/**
	 * 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	 */
	private String time_end;

	/**
	 * 对当前查询订单状态的描述和下一步操作的指引
	 */
	private String trade_state_desc;

}
