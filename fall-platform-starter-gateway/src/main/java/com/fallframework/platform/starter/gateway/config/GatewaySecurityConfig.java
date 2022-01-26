package com.fallframework.platform.starter.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * 网关安全配置
 *
 * @author zhuangpf
 */
public class GatewaySecurityConfig {

	@Bean
	public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity serverHttpSecurity)
			throws Exception {
		serverHttpSecurity
				.csrf().disable()
				.authorizeExchange().pathMatchers("/**").permitAll()
				.anyExchange()
				.authenticated();
		return serverHttpSecurity.build();
	}

}
