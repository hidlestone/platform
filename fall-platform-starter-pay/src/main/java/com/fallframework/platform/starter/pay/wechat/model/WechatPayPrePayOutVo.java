package com.fallframework.platform.starter.pay.wechat.model;

import com.fallframework.platform.starter.api.response.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class WechatPayPrePayOutVo extends BaseEntityResponse {

	private static final long serialVersionUID = -4425814936699445249L;

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

	// 错误代码
	private String errCode;

	// 错误代码描述
	private String errCodeDes;

	// 以下字段 在return_code 和result_code都为SUCCESS的时候有返回
	// 交易类型 TradeType
	private String tradeType;

	// 预支付交易会话标识  微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
	private String prepayId;

	// trade_type为NATIVE是有返回，可将该参数值生成二维码展示出来进行扫码支付
	private String codeUrl;

}
