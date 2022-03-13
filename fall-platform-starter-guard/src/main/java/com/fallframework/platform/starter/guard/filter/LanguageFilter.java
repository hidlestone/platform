package com.fallframework.platform.starter.guard.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 语言过滤器
 *
 * @author zhuangpf
 */
public class LanguageFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.debug("LanguageFilter init.");
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(LanguageFilter.class);

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		ServletRequest request = new LanguageRequestWrapper((HttpServletRequest) servletRequest);
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		LOGGER.debug("LanguageFilter destroy.");
	}
}
