package com.fallframework.platform.starter.pay.enums;

/**
 * JSAPI 微信公众号， 微信浏览器支付（数据库中配置为  WAP,service中特殊处理即可）
 * <p/>
 * 业务方调用的时候，传以下3中类型即可，payService中对自动把WAP根据需要解析成JSAPI：
 * NATIVE 扫码
 * App  app支付
 * WAP 浏览器，网页版支付
 * <p/>
 * 微信支付类型：
 * https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=4_2
 */
public enum TradeTypeEnum {

	JSAPI("TradeTypeCode", "WAP支付"),
	NATIVE("NATIVE", "扫码支付"),
	APP("APP", "APP支付"),
	WAP("WAP", "WAP支付"),
	MICROPAY("MICROPAY", "刷卡支付");
	
	private String code;
	private String name;

	TradeTypeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
