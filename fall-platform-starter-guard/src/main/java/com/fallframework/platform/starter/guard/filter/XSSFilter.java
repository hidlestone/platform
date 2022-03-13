package com.fallframework.platform.starter.guard.filter;

import cn.hutool.core.collection.CollectionUtil;
import com.fallframework.platform.starter.config.service.PlatformSysParamUtil;
import com.fallframework.platform.starter.guard.util.CookieFilterUtil;
import com.fallframework.platform.starter.guard.util.PlatformRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 过滤XSS跨站脚本攻击
 *
 * @author zhuangpf
 */
public class XSSFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(XSSFilter.class);

	@Autowired
	private static PlatformSysParamUtil platformSysParamUtil;
	private AntPathMatcher matcher = new AntPathMatcher();

	private static final Boolean XSS_ENABLE;
	private static final String EXCLUDE_PATTERN;
	private static final String INCLUDE_PATTERN;

	private static final List<String> excludePatternList;
	private static final List<String> includePatternlist;

	static {
		Map<String, String> sysItemMap = platformSysParamUtil.getSysParamGroupItemMap("XSS_CONFIG").getData();
		LOGGER.info("SYS PAPRAM XSS_CONFIG : " + sysItemMap.toString());
		XSS_ENABLE = Boolean.valueOf(sysItemMap.get("XSS_ENABLE"));
		EXCLUDE_PATTERN = sysItemMap.get("EXCLUDE_PATTERN");
		INCLUDE_PATTERN = sysItemMap.get("INCLUDE_PATTERN");

		String[] excludePatternArr = EXCLUDE_PATTERN.split(",");
		excludePatternList = new ArrayList<>(excludePatternArr.length);
		Collections.addAll(excludePatternList, excludePatternArr);
		String[] includePatternArr = INCLUDE_PATTERN.split(",");
		includePatternlist = new ArrayList<>(includePatternArr.length);
		Collections.addAll(includePatternlist, includePatternArr);
	}

	@Override
	public void init(FilterConfig filterConfig) {
		LOGGER.debug("XSSFilter init.");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		// 内部请求/不需要过滤的资源 则放行
		if (PlatformRequestUtil.isInternalCall(request) || !this.isXXSFilterRequest(request)) {
			chain.doFilter(request, response);
		}
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			int len = cookies.length;
			for (int i = 0; i < len; ++i) {
				Cookie cookie = cookies[i];
				if (cookie.getValue() != null && CookieFilterUtil.isValidate(cookie.getValue().toLowerCase())) {
					throw new RuntimeException("cookiename " + cookie.getName() + ":" + cookie.getValue() + " exist script!");
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
		if (!XSS_ENABLE) {
			return false;
		}
		if (CollectionUtil.isEmpty(includePatternlist) || !this.match(url, includePatternlist)) {
			return false;
		}
		if (CollectionUtil.isNotEmpty(excludePatternList) && this.match(url, excludePatternList)) {
			return false;
		}
		return true;
	}

	/**
	 * 是否匹配
	 */
	protected boolean match(String url, List<String> patterns) {
		for (String pattern : patterns) {
			if ((this.matcher.match(pattern, url) && this.matcher.match("/" + pattern, url))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void destroy() {
		LOGGER.debug("XSSFilter destroy.");
	}

}
