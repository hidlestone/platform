package com.fallframework.platform.starter.pay.wechat.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;

/**
 * 交易保障请求参数
 *
 * @author zhuangpf
 */
public class PayitilReportRequest extends BaseEntityRequest {

	private static final long serialVersionUID = -1780979236788321589L;

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
	 * 报对应的接口的完整URL，类似：
	 * <p>
	 * https://api.mch.weixin.qq.com/pay/unifiedorder
	 * <p>
	 * 对于刷卡支付，为更好的和商户共同分析一次业务行为的整体耗时情况，对于两种接入模式，请都在门店侧对一次刷卡支付进行一次单独的整体上报，上报URL指定为：
	 * <p>
	 * https://api.mch.weixin.qq.com/pay/micropay/total
	 * <p>
	 * 关于两种接入模式具体可参考本文档章节：刷卡支付商户接入模式
	 * <p>
	 * 其它接口调用仍然按照调用一次，上报一次来进行。
	 */
	private String interface_url;

	/**
	 * 接口耗时情况，单位为毫秒
	 * 注意：该参数最后带有下划线“_”，参数设计如此，非文档问题。
	 */
	private String execute_time_;

	/**
	 * SUCCESS/FAIL
	 * <p>
	 * 此字段是通信标识，非交易标识，交易是否成功需要查看trade_state来判断
	 */
	private String return_code;

	/**
	 * 当return_code为FAIL时返回信息为错误原因 ，例如
	 * <p>
	 * 签名失败
	 * <p>
	 * 参数格式校验错误
	 */
	private String return_msg;

	/**
	 * SUCCESS/FAIL
	 */
	private String result_code;

	/**
	 * ORDERNOTEXIST—订单不存在
	 * <p>
	 * SYSTEMERROR—系统错误
	 */
	private String err_code;

	/**
	 * 商户系统内部的订单号,商户可以在上报时提供相关商户订单号方便微信支付更好的提高服务质量。
	 */
	private String out_trade_no;

	/**
	 * 发起接口调用时的机器IP
	 */
	private String user_ip;

	/**
	 * 商户上报时间
	 * 系统时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则
	 */
	private String time;

}
