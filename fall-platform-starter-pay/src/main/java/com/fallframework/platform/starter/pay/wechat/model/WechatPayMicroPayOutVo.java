package com.fallframework.platform.starter.pay.wechat.model;

import com.fallframework.platform.starter.api.response.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 微信刷卡支付  接口 返回值
 * <p/>
 * 目前比微信接口少了 cash_fee等字段
 */
@Getter
@Setter
public class WechatPayMicroPayOutVo extends BaseEntityResponse {

	private static final long serialVersionUID = -29496211297388659L;

	// 公众账号ID
	private String appId;

	// 微信支付商户号
	private String mchId;

	// 设备号
	private String deviceInfo;

	// 随机字符串
	private String nonceStr;

	// 签名
	private String sign;

	// 错误代码  USERPAYING：用户支付中，需要输入密码， 此场景需要轮询订单，直到支付成功或超时。
	private String errCode;

	// 错误代码描述
	private String errCodeDes;

	// 以下字段 在return_code 和result_code都为SUCCESS的时候有返回

	// 交易类型 TradeType，刷卡支付为：MICROPAY
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
