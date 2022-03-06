package com.fallframework.platform.starter.urule.server.config;

import com.bstek.urule.console.servlet.URuleServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * 后台规则注册URL
 *
 * @author zhuangpf
 */
public class URuleServletRegistration {

	@Bean
	public ServletRegistrationBean registerURuleServlet() {
		return new ServletRegistrationBean(new URuleServlet(), "/urule/*");
	}

}
