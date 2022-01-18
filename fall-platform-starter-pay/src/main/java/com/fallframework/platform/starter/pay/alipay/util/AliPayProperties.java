package com.fallframework.platform.starter.pay.alipay.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhuangpf
 */
@Data
@ConfigurationProperties(prefix = "cp.ali.pay")
public class AliPayProperties {

	private String protocol;
	private String gatewayHost;
	private String signType;
	private String appId;
	private String merchantPrivateKey;
	private String aliPayPublicKey;
	private String notifyUrl;
	private String encryptKey;

}
