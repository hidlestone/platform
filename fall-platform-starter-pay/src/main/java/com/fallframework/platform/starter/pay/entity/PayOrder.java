package com.fallframework.platform.starter.pay.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.data.mp.entity.BaseEntity;
import com.fallframework.platform.starter.pay.enums.TradeTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName(value = "pay_order")
public class PayOrder extends BaseEntity {

	private static final long serialVersionUID = -2331901983463549442L;

	/**
	 * 主键
	 */
	@TableField(value = "id")
	private Long id;

	/**
	 * 支付类型：PayTypeCode
	 */
	@TableField(value = "pay_type_code")
	private String payTypeCode;

	/**
	 * 业务方，支付订单号
	 */
	@TableField(value = "pay_order_no")
	private String payOrderNo;

	/**
	 * 微信/支付宝的商户交易流水号
	 */
	@TableField(value = "trade_pay_no")
	private String tradePayNo;

	/**
	 * 微信/支付宝返回的给app或者网页的支付凭证，客户端通过此信息调起支付界面。
	 */
	@TableField(value = "pre_pay_id")
	private String prePayId;

	/**
	 * 微信/支付宝的商户交易流水号
	 */
	@TableField(value = "pay_id")
	private String payId;

	/**
	 * 微信/支付宝的商户交易流水号
	 */
	@TableField(value = "user_ip")
	private String userIp;

	/**
	 * 支付金额，精确到分
	 */
	@TableField(value = "pay_amount")
	private Integer payAmount;

	/**
	 * 支付时间
	 */
	@TableField(value = "pay_time")
	private Date payTime;

	/**
	 * 支付状态：PayProcessStatus
	 */
	@TableField(value = "status")
	private Short status;

	/**
	 * 如果创建订单失败，则保存第三方返回的失败错误码
	 */
	@TableField(value = "error_code")
	private String errorCode;

	/**
	 * 错误信息
	 */
	@TableField(value = "error_msg")
	private String errorMsg;

	/**
	 * 支付申请时间
	 */
	@TableField(value = "start_time")
	private Date startTime;

	/**
	 * 支付过期时间，默认为2小时
	 */
	@TableField(value = "expire_time")
	private Date expireTime;

	/**
	 * 微信为用户的openId，支付宝为buyer_id买家支付宝用户号
	 */
	@TableField(value = "open_id")
	private String openId;

	/**
	 * 支付宝中：买家支付宝账号
	 */
	@TableField(value = "buyer_logon_id")
	private String buyerLogonId;

	/**
	 * 回调业务方的url
	 */
	@TableField(value = "notify_url")
	private String notifyUrl;

	/**
	 * 附加信息，支付完成后通知时候会原封不动返回业务方
	 */
	@TableField(value = "extra")
	private String extra;

	/**
	 * 订单标题，微信中对应body字段
	 */
	@TableField(value = "subject")
	private String subject;

	/**
	 * 订单描述，微信中对应detail字段，为json格式。支付宝中对应body字段，表示描述
	 */
	@TableField(value = "detail")
	private String detail;

	/**
	 * 二维码链接
	 */
	@TableField(value = "code_url")
	private String codeUrl;

	/**
	 * 业务方商户号：PayMerchant
	 */
	@TableField(value = "merchant_id")
	private String merchantId;

	/**
	 * 支付类型，如扫码、app支付、wap支付等：TradeTypeCode
	 */
	@TableField(value = "trade_type")
	private TradeTypeEnum tradeType;

	/**
	 * 支付成功页，支付宝：页面跳转同步通知页面路径，需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问。微信需要在前端自己设置
	 */
	@TableField(value = "return_url")
	private String returnUrl;

	/**
	 * 退款额度，精确到分
	 */
	@TableField(value = "refund_amount")
	private Integer refundAmount;

}