package com.fallframework.platform.starter.pay.wechat.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信支付配置的基础参数见 pay-starter.properties
 *
 * @author zhuangpf
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "cp.wx.pay")
public class WechatPayProperties {

	/**
	 * 微信支付分配的公众账号ID（企业号corpid即为此appid）
	 */
	private String appId;

	/**
	 * 服务号的应用密钥
	 */
	private String appSecret;

	/**
	 * 商户号
	 */
	private String mchId;

	/**
	 * API密钥
	 */
	private String apiKey;

	/**
	 * 签名加密方式
	 */
	private String signType;

	/**
	 * 微信支付证书名称
	 */
	private String certPath;

	/**
	 * 回调URL
	 */
	private String notifyUrl;

	/**
	 * 是否使用沙盒
	 */
	private Boolean useSandbox;

}
