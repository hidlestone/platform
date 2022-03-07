package com.fallframework.platform.starter.urule.client.config;

import com.bstek.urule.KnowledgePackageReceiverServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author zhuangpf
 */
public class URuleServletRegistration {

	@Bean
	public ServletRegistrationBean registerURuleServlet() {
		return new ServletRegistrationBean(new KnowledgePackageReceiverServlet(), "/knowledgepackagereceiver");
	}
	
}
