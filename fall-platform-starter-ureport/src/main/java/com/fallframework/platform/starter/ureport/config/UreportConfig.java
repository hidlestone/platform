package com.fallframework.platform.starter.ureport.config;

import com.bstek.ureport.console.UReportServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.servlet.Servlet;

/**
 * ureport 配置
 *
 * @author zhuangpf
 */
@Configuration
@ImportResource("classpath:ureport-context.xml")
public class UreportConfig {

	@Bean
	public ServletRegistrationBean<Servlet> buildUreportServlet() {
		return new ServletRegistrationBean<Servlet>(new UReportServlet(), "/ureport/*");
	}

}
