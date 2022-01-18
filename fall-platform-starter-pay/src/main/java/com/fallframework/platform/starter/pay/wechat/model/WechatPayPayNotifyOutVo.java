package com.fallframework.platform.starter.pay.wechat.model;

import com.fallframework.platform.starter.api.response.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 微信支付成功，异步通知接口
 */
@Getter
@Setter
public class WechatPayPayNotifyOutVo extends BaseEntityResponse {

	private String appId;

	private String mchId;

	private String deviceInfo;

	private String nonceStr;

	private String sign;

	private String errCode;

	private String errCodeDes;

	// 客户 微信 openid
	private String openId;

	// 是否关注公众账号
	private String isSubscribe;

//    @ApiResponseField("trade_state")
//    private String tradeState;

	// 交易方式  TradeTypeCode
	private String tradeType;

	//付款银行	银行类型，采用字符串类型的银行标识
	private String bankType;

	// 总金额  单位为分
	private String totalFee;

	// 货币种类  默认人民币，CNY
	private String feeType;

	// 微信支付订单号，微信内部订单号
	private String transactionId;

	// 客户订单号
	private String outTradeNo;

	// 附加数据， 统一下单时候 传过来的 再原样传回去
	private String attach;

	// 支付完成时间  格式为yyyyMMddHHmmss
	private Date timeEnd;

}
