package com.fallframework.platform.starter.guard.filter;

import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.config.model.SysParamGroupEnum;
import com.fallframework.platform.starter.config.service.PlatformSysParamUtil;
import com.fallframework.platform.starter.core.constant.CoreContextConstant;
import com.fallframework.platform.starter.guard.exception.UnknownAccountException;
import com.fallframework.platform.starter.guard.util.JWTUtil;
import com.fallframework.platform.starter.rbac.constant.RbacStarterConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * token过滤器<br/>
 * 双token动态刷新策略
 *
 * @author zhuangpf
 */
public class TokenFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(TokenFilter.class);

	/**
	 * refreshtoken刷新的时间间隔
	 */
	private final Long GUARD_REFRESH_TOKEN_TIME_INTERVAL;
	/**
	 * 系统静态资源
	 */
	private final String STATIC_RESORUCES;
	private final List<String> staticResourceList;
	/**
	 * 可匿名访问的资源
	 */
	private final String ANON_RESORUCES;
	private final List<String> anonResourceList;

	private RedisUtil redisUtil;
	private JWTUtil jwtUtil;
	// antPathMatcher路径匹配
	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	/**
	 * 构造函数
	 */
	public TokenFilter(PlatformSysParamUtil platformSysParamUtil, RedisUtil redisUtil, JWTUtil jwtUtil) {
		this.redisUtil = redisUtil;
		this.jwtUtil = jwtUtil;
		Map<String, String> sysItemMap = platformSysParamUtil.getSysParamGroupItemMap(SysParamGroupEnum.GUARD.toString()).getData();
		GUARD_REFRESH_TOKEN_TIME_INTERVAL = Long.valueOf(platformSysParamUtil.mapGet(sysItemMap, "GUARD_REFRESH_TOKEN_TIME_INTERVAL"));
		STATIC_RESORUCES = platformSysParamUtil.mapGet(sysItemMap, "STATIC_RESORUCES");
		String[] staticResourceArr = STATIC_RESORUCES.split(",");
		staticResourceList = new ArrayList<>(staticResourceArr.length);
		Collections.addAll(staticResourceList, staticResourceArr);
		ANON_RESORUCES = platformSysParamUtil.mapGet(sysItemMap, "ANON_RESORUCES");
		String[] anonResourceArr = ANON_RESORUCES.split(",");
		anonResourceList = new ArrayList<>(anonResourceArr.length);
		Collections.addAll(anonResourceList, anonResourceArr);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.debug("TokenFilter init.");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 是否是匿名访问资源
		if (isPermissive(request)) {
			chain.doFilter(request, response);
		}
		boolean loggedIn = false;
		// 是否可以尝试进行认证
		if (this.isLoginAttempt(request, response)) {
			loggedIn = this.executeLogin(request, response);
		}
	}

	/**
	 * 进行用户进行登录认证授权
	 */
	private boolean executeLogin(ServletRequest request, ServletResponse response) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String accesstoken = httpRequest.getHeader(CoreContextConstant.ACCESSTOKEN);
		// 用户id
		String idStr = jwtUtil.getClaim(accesstoken, "id");
		String accesstokenCache = (String) redisUtil.get(RbacStarterConstant.CACHE_KEY_ACCESSTOKEN + idStr);
		if (!accesstoken.equals(accesstokenCache)) {
			throw new UnknownAccountException("invalid accesstoken");
		}
		// 将用户信息存入到当前上下文环境中，便于在业务代码中取出相关的信息
		// TODO
		return true;
	}

	@Override
	public void destroy() {
		LOGGER.debug("TokenFilter destroy.");
	}

	/**
	 * 是否是可以访问的静态/匿名请求
	 * getRequestURI:/test/test.jsp
	 * getRequestURL:http://localhost:8080/test/test.jsp
	 */
	protected boolean isPermissive(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();
		// 是否是静态资源/匿名资源
		return isStaticResource(requestURI) && isAnonResource(requestURI);
	}

	/**
	 * 是否是可以匿名访问的资源
	 */
	private boolean isAnonResource(String requestURI) {
		for (String pattern : anonResourceList) {
			boolean match = antPathMatcher.match(pattern, requestURI);
			if (match) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否是请求静态资源
	 */
	private boolean isStaticResource(String requestURI) {
		for (String suffix : staticResourceList) {
			if (requestURI.endsWith(suffix)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否是可尝试登录的请求
	 */
	protected final boolean isLoginRequest(ServletRequest request, ServletResponse response) {
		return this.isLoginAttempt(request, response);
	}

	/**
	 * 当前的请求是否可以尝试进行登录授权<br/>
	 * 检测header里面是否包含accesstoken、refreshtoken字段，有则进行Token登录认证授权
	 */
	protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
		// 需要是BASIC开头的token
		/*return super.isLoginAttempt(request, response);*/
		// 拿到当前Header中Authorization的AccessToken(Shiro中getAuthzHeader方法已经实现)
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String accessToken = httpRequest.getHeader(CoreContextConstant.ACCESSTOKEN);
		String refreshToken = httpRequest.getHeader(CoreContextConstant.REFRESHTOKEN);
		return !StringUtils.isBlank(accessToken) && !StringUtils.isBlank(refreshToken);
	}

}
