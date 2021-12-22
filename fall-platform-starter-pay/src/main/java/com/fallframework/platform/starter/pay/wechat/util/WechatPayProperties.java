package com.fallframework.platform.starter.pay.wechat.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信支付配置的基础参数
 *
 * @author zhuangpf
 */
@Data
@ConfigurationProperties(prefix = "cp.wx.pay")
public class WechatPayProperties {

	private String appId;
	private String appSecret;
	private String mchId;
	private String apiKey;
	private String signType;
	private String certPath;
	private String notifyUrl;

}
