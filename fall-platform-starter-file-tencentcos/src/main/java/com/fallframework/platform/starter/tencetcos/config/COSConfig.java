package com.fallframework.platform.starter.tencetcos.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * COS配置
 *
 * @author zhuangpf
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "platform.starter.tencetcos")
public class COSConfig {

	private String appId;

	private String secretId;

	private String secretKey;
	
	private String regionName;
	
	private String bucketName;

}
