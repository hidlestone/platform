package com.fallframework.platform.starter.pay.wechat.model;

import com.fallframework.platform.starter.core.entity.response.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class WechatPayRefundOutVo extends BaseEntityResponse {

	private static final long serialVersionUID = -2387457314757346797L;

	private String appId;
	private String mchId;
	private String deviceInfo;
	private String nonceStr;
	private String sign;
	private String errCode;
	private String errCodeDes;

	// 商户退款单号
	private String outRefundNo;

	// 微信退款单号
	private String refundId;

	// 退款渠道 ORIGINAL—原路退款，    BALANCE—退回到余额
	private String refundChannel;

	private String totalFee;

	private String feeType;

	// 微信订单号
	private String transactionId;

	// 商户订单号， 商户内部系统的订单号，  32个字符内，可包含字母。 微信支付要求商户订单号保持唯一性
	private String outTradeNo;

	private String refundFee;

}
