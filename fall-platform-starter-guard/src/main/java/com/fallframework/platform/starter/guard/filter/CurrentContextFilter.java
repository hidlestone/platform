package com.fallframework.platform.starter.guard.filter;

import com.alibaba.fastjson.JSON;
import com.fallframework.platform.starter.config.model.SysParamGroupEnum;
import com.fallframework.platform.starter.config.service.PlatformSysParamUtil;
import com.fallframework.platform.starter.core.constant.CoreContextConstant;
import com.fallframework.platform.starter.core.context.CurrentContextHelper;
import com.fallframework.platform.starter.core.context.ReadableResponseWrapper;
import com.fallframework.platform.starter.core.context.UserAuthInfo;
import com.fallframework.platform.starter.core.util.ApplicationInstanceManager;
import com.fallframework.platform.starter.guard.constant.GuardStarterConstant;
import com.fallframework.platform.starter.guard.util.PermissionUtil;
import com.fallframework.platform.starter.guard.util.PlatformRequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 一次请求当前上下文环境过滤器
 *
 * @author zhuangpf
 */
public class CurrentContextFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(CurrentContextFilter.class);

	/**
	 * 内部请求超时时间(ms)
	 */
	private final Integer INTERNAL_ACCESS_DEVIATION;
	/**
	 * 内部请求加密盐
	 */
	private final String INTERNAL_CALL_SIGN_SALT;
	/**
	 * 系统静态资源
	 */
	private final String STATIC_RESORUCES;
	/**
	 * 系统允许的访问客户端
	 */
	private final String ALLOW_CLIENTS;
	/**
	 * 授权过滤排除的资源
	 */
	private final String AUTH_FILTER_EXCLUDES;
	/**
	 * 授权过滤排除的资源
	 */
	private final Boolean CLIENT_APP_PERMISSION_VERIFICATION;
	/**
	 * 是否启用数据库密码加密
	 */
	private final Boolean ENABLE_DATABASE_PASSWORD_ENCRYPTION;
	/**
	 * 是否允许无 token 访问
	 */
	private final Boolean ALLOW_ACCESS_WITHOUT_TOKEN;
	/**
	 * 授权过滤排除的资源
	 */
	private final List<String> exludeList;
	/**
	 * 系统静态资源
	 */
	private final List<String> staticResourceeList;

	private PlatformSysParamUtil platformSysParamUtil;

	/**
	 * 构造函数
	 */
	public CurrentContextFilter(PlatformSysParamUtil platformSysParamUtil) {
		this.platformSysParamUtil = platformSysParamUtil;
		// 从缓存中获取配置参数：JWT
		Map<String, String> sysItemMap = platformSysParamUtil.getSysParamGroupItemMap(SysParamGroupEnum.SECURITY.toString()).getData();
		INTERNAL_ACCESS_DEVIATION = Integer.valueOf(platformSysParamUtil.mapGet(sysItemMap, "INTERNAL_ACCESS_DEVIATION"));
		INTERNAL_CALL_SIGN_SALT = platformSysParamUtil.mapGet(sysItemMap, "INTERNAL_CALL_SIGN_SALT");
		STATIC_RESORUCES = platformSysParamUtil.mapGet(sysItemMap, "STATIC_RESORUCES");
		ALLOW_CLIENTS = platformSysParamUtil.mapGet(sysItemMap, "ALLOW_CLIENTS");
		AUTH_FILTER_EXCLUDES = platformSysParamUtil.mapGet(sysItemMap, "AUTH_FILTER_EXCLUDES");
		CLIENT_APP_PERMISSION_VERIFICATION = Boolean.valueOf(platformSysParamUtil.mapGet(sysItemMap, "CLIENT_APP_PERMISSION_VERIFICATION"));
		ENABLE_DATABASE_PASSWORD_ENCRYPTION = Boolean.valueOf(platformSysParamUtil.mapGet(sysItemMap, "ENABLE_DATABASE_PASSWORD_ENCRYPTION"));
		ALLOW_ACCESS_WITHOUT_TOKEN = Boolean.valueOf(platformSysParamUtil.mapGet(sysItemMap, "ALLOW_ACCESS_WITHOUT_TOKEN"));
		// 授权过滤排除的资源
		String[] exludeArr = AUTH_FILTER_EXCLUDES.split(",");
		// 授权过滤排除的资源
		String[] staticResourceArr = STATIC_RESORUCES.split(",");
		// 转成列表
		exludeList = new ArrayList<String>(exludeArr.length);
		staticResourceeList = new ArrayList<String>(staticResourceArr.length);
		LOGGER.info("SECURITY CONFIG : " + JSON.toJSONString(sysItemMap));
	}

	/**
	 * 初始化
	 */
	@Override
	public void init(FilterConfig filterConfig) {
		LOGGER.debug("currentContextFilter init.");
	}

	/**
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		// 获取用户授权信息
		UserAuthInfo userAuthInfo = this.getUserAuthInfo(httpServletRequest);
		if (null != userAuthInfo) {
			// 更新当前当前上下文环境
			CurrentContextHelper.updateCurrentContext(userAuthInfo, httpServletRequest, CurrentContextHelper.getRequestTime());
		}
		filterChain.doFilter(new BodyReaderHttpServletRequestWrapper(httpServletRequest), new ReadableResponseWrapper((HttpServletResponse) response));
	}

	/**
	 * 获取用户授权信息
	 */
	private UserAuthInfo getUserAuthInfo(HttpServletRequest request) {
		String uri = request.getRequestURI();
		if (uri.endsWith(GuardStarterConstant.GET_UAER_AUTH_INFO_URL) || uri.endsWith(GuardStarterConstant.HAS_PERMISSION_URL)) {
			return null;
		}
		// 获取请求的 token
		String token = this.getToken(request);
		// 是否是内部请求
		boolean internalCall = PlatformRequestUtil.isInternalCall(request);
		if (internalCall) {
			return StringUtils.isNotBlank(token) ? PermissionUtil.getUserAuthInfo(token) : null;
		}
		// 是否可以无 token 访问
		boolean allowAccessWithoutToken = ALLOW_ACCESS_WITHOUT_TOKEN;
		if (allowAccessWithoutToken) {
			return StringUtils.isNotBlank(token) ? PermissionUtil.getUserAuthInfo(token) : null;
		}
		// 是否是无需过滤的资源
		boolean authFilterExclude = exludeList.contains(request.getRequestURI());
		if (authFilterExclude) {
			return null;
		}
		// 是否是静态资源
		boolean staticResource = this.isStaticResource(request.getRequestURI());
		if (staticResource) {
			return null;
		}
		// 是否是匿名允许的
		boolean anonPermission = this.isAnonPermission(request);
		if (anonPermission) {
			return null;
		}
		if (StringUtils.isBlank(token)) {
			throw new IllegalStateException("token not present.");
		} else {
			UserAuthInfo userAuthInfo = PermissionUtil.getUserAuthInfo(token);
			if (null == userAuthInfo) {
				throw new IllegalStateException("token is invalid, please relogin and try again.");
			} else if (!this.checkPermission(request)) {
				throw new IllegalAccessError("don't have permission to access [" + uri + "]");
			} else {
				return userAuthInfo;
			}
		}
	}

	/**
	 * 校验权限
	 */
	private boolean checkPermission(HttpServletRequest request) {
		String token = this.getToken(request);
		String uri = request.getRequestURI();
		String checkUrl = GuardStarterConstant.SLASH + ApplicationInstanceManager.getApplicationName() + uri.replace(this.getTarget(request), "");
		return PermissionUtil.checkPermission(token, checkUrl).isSuccess();
	}

	/**
	 * 是否允许匿名访问
	 */
	private boolean isAnonPermission(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String checkUrl = GuardStarterConstant.SLASH + ApplicationInstanceManager.getApplicationName() + uri.replace(this.getTarget(request), "");
		return PermissionUtil.isAnonResource(checkUrl);
	}

	/**
	 * 获取请求头target
	 */
	private String getTarget(HttpServletRequest request) {
		String target = request.getHeader(CoreContextConstant.TARGET);
		if (StringUtils.isBlank(target)) {
			target = request.getHeader(CoreContextConstant.TARGET);
		}
		return target == null ? "" : target;
	}

	/**
	 * 是否为系统静态资源
	 */
	private boolean isStaticResource(String requestURI) {
		// .js
		String lastStr = requestURI.substring(requestURI.lastIndexOf("."));
		if (StringUtils.isEmpty(requestURI)) {
			lastStr = requestURI.substring(requestURI.lastIndexOf("/"));
		}
		return staticResourceeList.contains(lastStr);
	}

	/**
	 * 获取请求头(请求参数)中的token
	 */
	private String getToken(HttpServletRequest request) {
		String token = request.getHeader(CoreContextConstant.TOKEN);
		if (StringUtils.isEmpty(token)) {
			token = request.getParameter(CoreContextConstant.TOKEN);
			if (StringUtils.isEmpty(token)) {
				token = null;
			}
		}
		return token;
	}

}
