package com.fallframework.platform.starter.shiro.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * shiro 配置参数
 *
 * @author zhuangpf
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "fall.shiro")
@PropertySource("classpath:config.properties")
public class ShiroConfigProperties {

	private String loginUrl;
	private String successUrl;
	private String unauthorizedUrl;
	private String filterChainDefinition;
	private String rememberMeCookieMaxAge;

}
