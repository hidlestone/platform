package com.fallframework.platform.starter.mvc.filter;

import com.wordplay.ceep.core.constant.CoreContextConstant;
import com.wordplay.ceep.core.context.CeepApplicationContext;
import com.wordplay.ceep.core.context.config.PlatformConfig;
import com.wordplay.ceep.mvc.util.CookieFilterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 过滤XSS跨站脚本攻击
 *
 * @author payn
 */
public class XSSFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(LanguageFilter.class);

	// exclude urls
	private static Set<String> excludes = new HashSet();
	// include urls
	private static Set<String> includes = new HashSet();
	private AntPathMatcher matcher = new AntPathMatcher();
	private PlatformConfig platformConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.debug("XSSFilter init.");
		// 获取默认配置
		if ((null != this.platformConfig) && (null != this.excludes) && (null != this.includes)) {
			this.platformConfig = CeepApplicationContext.getBean(PlatformConfig.class);
			if (this.platformConfig != null && this.platformConfig.getSecurityConfig() != null && this.platformConfig.getSecurityConfig().getXssConfig() != null) {
				this.excludes = new HashSet(Arrays.asList(this.platformConfig.getSecurityConfig().getXssConfig().getExcludes()));
				this.includes = new HashSet(Arrays.asList(this.platformConfig.getSecurityConfig().getXssConfig().getIncludes()));
			}
		}
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if (this.isXXSFilterRequest(request) && !this.isInternalCall(request)) {
			request = this.wrapperRequest(request);
		}
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			Cookie[] cookieArr = cookies;
			int len = cookies.length;
			for (int i = 0; i < len; ++i) {
				Cookie cookie = cookieArr[i];
				if (cookie.getValue() != null && CookieFilterUtil.isValidate(cookie.getValue().toLowerCase())) {
					response.sendError(403, cookie.getName() + ":" + cookie.getValue() + " exist script!");
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * 是否是需要xss过滤的请求
	 */
	protected boolean isXXSFilterRequest(HttpServletRequest request) {
		String url = request.getRequestURI();
		if (!this.includes.isEmpty() && this.match(url, this.includes)) {
			return true;
		} else if (!this.isXssEnabled()) {
			return false;
		} else {
			return this.excludes.isEmpty() || !this.match(url, this.excludes);
		}
	}

	/**
	 * 是否匹配
	 */
	protected boolean match(String url, Set<String> patterns) {
		Iterator iterator = patterns.iterator();
		String p;
		do {
			if (!iterator.hasNext()) {
				return false;
			}
			p = (String) iterator.next();
		} while (!this.matcher.match(p, url) && !this.matcher.match("/" + p, url));
		return true;
	}

	/**
	 * 是否启用
	 */
	private boolean isXssEnabled() {
		return this.platformConfig != null && this.platformConfig.getSecurityConfig() != null
				&& this.platformConfig.getSecurityConfig().getXssConfig() != null
				&& this.platformConfig.getSecurityConfig().getXssConfig().isEnable();
	}

	private boolean isInternalCall(HttpServletRequest request) {
		String callType = request.getHeader(CoreContextConstant.CALL_TYPE);
		return CoreContextConstant.CALL_TYPE.equals(callType);
	}


	protected HttpServletRequest wrapperRequest(HttpServletRequest request) throws IOException {
		return null;
//		return new XSSHttpRequestWrapper(request);
	}

	@Override
	public void destroy() {
		LOGGER.debug("XSSFilter destroy.");
	}
}
