package com.fallframework.platform.starter.control.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 使用feign进行调用的时候，需要往下游的服务携带请求头
 *
 * @author zhuangpf
 */
@Configuration
public class FeignRequestConfig implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		Enumeration<String> headerNames = request.getHeaderNames();
		// template.header(自定义请求头Key, 自定义请求头值);
		if (headerNames == null) {
			return;
		}
		// 处理上游请求头信息，传递时继续携带
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			String values = request.getHeader(name);
			template.header(name, values);
		}
	}

}
