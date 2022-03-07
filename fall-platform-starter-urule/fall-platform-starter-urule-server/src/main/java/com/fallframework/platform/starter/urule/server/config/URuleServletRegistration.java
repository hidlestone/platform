package com.fallframework.platform.starter.urule.server.config;

import com.bstek.urule.console.servlet.URuleServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * 配置Urule Servlet，后台规则注册URL<br/>
 * urule后台地址：<br/>
 * http://localhost:[port]/[contextPath]/urule/frame<br/>
 * http://localhost:8787/urule/frame<br/>
 *
 * @author zhuangpf
 */
public class URuleServletRegistration {

	@Bean
	public ServletRegistrationBean registerURuleServlet() {
		return new ServletRegistrationBean(new URuleServlet(), "/urule/*");
	}

}
