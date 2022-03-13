package com.fallframework.platform.starter.guard.filter;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.fallframework.platform.starter.config.model.SysParamGroupEnum;
import com.fallframework.platform.starter.config.service.PlatformSysParamUtil;
import com.fallframework.platform.starter.guard.util.CookieFilterUtil;
import com.fallframework.platform.starter.guard.util.PlatformRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
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
 * XSS跨站脚本攻击过滤器
 *
 * @author zhuangpf
 */
public class XSSFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(XSSFilter.class);

	// 系统参数工具
	private static PlatformSysParamUtil platformSysParamUtil;
	// antPathMatcher路径匹配
	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	/**
	 * 是否开启XSS过滤
	 */
	private final Boolean XSS_ENABLE;
	/**
	 * 排除URL规则
	 */
	private final String EXCLUDE_PATTERN;
	/**
	 * 包括URL规则
	 */
	private final String INCLUDE_PATTERN;

	private final List<String> excludePatternList;
	private final List<String> includePatternlist;

	/**
	 * 构造函数
	 *
	 * @param platformSysParamUtil 系统参数工具
	 */
	public XSSFilter(PlatformSysParamUtil platformSysParamUtil) {
		this.platformSysParamUtil = platformSysParamUtil;
		// 从缓存中获取配置参数
		Map<String, String> sysItemMap = platformSysParamUtil.getSysParamGroupItemMap(SysParamGroupEnum.XSS_CONFIG.toString()).getData();
		XSS_ENABLE = Boolean.valueOf(platformSysParamUtil.mapGet(sysItemMap, "XSS_ENABLE"));
		// 按照,分割
		EXCLUDE_PATTERN = platformSysParamUtil.mapGet(sysItemMap, "EXCLUDE_PATTERN");
		INCLUDE_PATTERN = platformSysParamUtil.mapGet(sysItemMap, "INCLUDE_PATTERN");
		// 转成数组
		String[] excludePatternArr = EXCLUDE_PATTERN.split(",");
		excludePatternList = new ArrayList<>(excludePatternArr.length);
		String[] includePatternArr = INCLUDE_PATTERN.split(",");
		includePatternlist = new ArrayList<>(includePatternArr.length);
		Collections.addAll(excludePatternList, excludePatternArr);
		Collections.addAll(includePatternlist, includePatternArr);
		LOGGER.info("XSS CONFIG : " + JSON.toJSONString(sysItemMap));
	}

	/**
	 * 过滤操作
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		// 内部请求 或者 不需要过滤的资源，则放行
		if (PlatformRequestUtil.isInternalCall(request) || !this.isNeedXXSFilterRequest(request)) {
			chain.doFilter(request, response);
		}
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; ++i) {
				Cookie cookie = cookies[i];
				if ((null != cookie.getValue()) && CookieFilterUtil.isValidate(cookie.getValue().toLowerCase())) {
					throw new RuntimeException("cookie name - " + cookie.getName() + ":" + cookie.getValue() + " exist script!");
				}
			}
		}
		chain.doFilter(request, response);
	}


	/**
	 * 是否是需要xss过滤的请求
	 *
	 * @param request 请求
	 * @return 是否是需要xss过滤的请求
	 */
	protected boolean isNeedXXSFilterRequest(HttpServletRequest request) {
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
	 * antPathMatcher规则是否匹配
	 */
	protected boolean match(String url, List<String> patterns) {
		for (String pattern : patterns) {
			if ((this.antPathMatcher.match(pattern, url) && this.antPathMatcher.match("/" + pattern, url))) {
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
