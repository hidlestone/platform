package com.fallframework.platform.starter.mvc.filter;

import com.fallframework.platform.starter.config.service.PlatformSysParamService;
import com.fallframework.platform.starter.core.constant.CoreContextConstant;
import com.fallframework.platform.starter.core.context.CurrentContextHelper;
import com.fallframework.platform.starter.core.context.UserAuthInfo;
import com.fallframework.platform.starter.core.util.EncryptionUtil;
import com.fallframework.platform.starter.mvc.constant.MvcStarterConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 一次请求当前上下文环境过滤器
 *
 * @author zhuangpf
 */
public class CurrentContextFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(CurrentContextFilter.class);

	@Autowired
	private static PlatformSysParamService platformSysParamService;
	// 内部请求超时时间(ms)
	private static final int INTERNAL_ACCESS_DEVIATION;
	// 内部请求加密盐
	private static final String INTERNAL_CALL_SIGN_SALT;

	static {
		Map<String, String> sysItemMap = platformSysParamService.getSysParamGroupItemMap("SECURITY").getData();
		INTERNAL_ACCESS_DEVIATION = Integer.valueOf(sysItemMap.get("INTERNAL_ACCESS_DEVIATION"));
		INTERNAL_CALL_SIGN_SALT = sysItemMap.get("INTERNAL_CALL_SIGN_SALT");
	}

	@Override
	public void init(FilterConfig filterConfig) {
		LOGGER.debug("currentContextFilter init.");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		// 获取用户授权信息
		UserAuthInfo userAuthInfo = this.getUserAuthInfo(httpServletRequest);
		// 更新当前当前上下文环境
		CurrentContextHelper.updateCurrentContext(userAuthInfo, httpServletRequest, CurrentContextHelper.getRequestTime());
		filterChain.doFilter(new BodyReaderHttpServletRequestWrapper(httpServletRequest), new ReadableResponseWrapper((HttpServletResponse) response));
	}

	/**
	 * 获取用户授权信息
	 */
	private UserAuthInfo getUserAuthInfo(HttpServletRequest request) {
		String uri = request.getRequestURI();
		if (!uri.endsWith(MvcStarterConstant.GET_UAER_AUTH_INFO_URL) && !uri.endsWith(MvcStarterConstant.HAS_PERMISSION_URL)) {
			// 获取请求的 token
			String token = this.getToken(request);
			// 是否是内部请求
			boolean internalCall = this.isInternalCall(request);
			if (internalCall) {
				return StringUtils.isNotBlank(token) ? UserPermissionUtil.getUserAuthInfo(token) : null;
			}
			// 是否可以无 token 访问
			boolean allowAccessWithoutToken = CeepApplicationContext.getPlatformConfig().getSecurityConfig().isAllowAccessWithoutToken();
			if (allowAccessWithoutToken) {
				return StringUtils.isNotBlank(token) ? UserPermissionUtil.getUserAuthInfo(token) : null;
			}
			// 是否是无需过滤的资源
			boolean authFilterExclude = CeepApplicationContext.getPlatformConfig().getSecurityConfig().isExcludeResource(request.getRequestURI());
			if (authFilterExclude) {
				return null;
			}
			// 是否是静态资源
			boolean staticResource = CeepApplicationContext.getPlatformConfig().getSecurityConfig().isStaticResource(request.getRequestURI());
			if (staticResource) {
				return null;
			}
			// 是否是匿名允许的
			boolean anonPermission = this.isAnonPermission(request);
			if (anonPermission) {
				return null;
			} else if (StringUtils.isBlank(token)) {
				throw new IllegalStateException("token not present.");
			} else {
				UserAuthInfo userAuthInfo = UserPermissionUtil.getUserAuthInfo(token);
				if (null == userAuthInfo) {
					throw new IllegalStateException("token invalid, please relogin and try again.");
				} else if (!this.hasPermission(request)) {
					throw new IllegalAccessError("You don't have permission to access [" + uri + "]");
				} else {
					return userAuthInfo;
				}
			}
		}
		return null;
	}

	/**
	 * 判断是否是内部请求
	 */
	private boolean isInternalCall(HttpServletRequest request) {
		// 请求携带内部请求签名是 sign
		String sign = request.getHeader(CoreContextConstant.INTERNAL_CALL_SIGN);
		if (StringUtils.isEmpty(sign)) {
			return false;
		}
		String time = request.getHeader(CoreContextConstant.INTERNAL_CALL_TIME);
		try {
			long currentTime = System.currentTimeMillis();
			long requestTime = Long.valueOf(time);
			// 内部请求超时时间
			if (currentTime - requestTime > INTERNAL_ACCESS_DEVIATION) {
				LOGGER.warn("Request devition more than " + INTERNAL_ACCESS_DEVIATION + "millisecond.");
				return false;
			}
		} catch (NumberFormatException e) {
			LOGGER.warn("Header internal-call-time is reqired for internal calls.");
			return false;
		}
		// 根据请求时间生成签名
		String generatedSign = EncryptionUtil.encrptSHA256(INTERNAL_CALL_SIGN_SALT + "@" + time);
		// 校验前端签名
		if (sign.equals(generatedSign)) {
			return true;
		} else {
			LOGGER.warn("internal call sign validation failed.");
			return false;
		}
	}

	/**
	 * 获取请求头(请求参数)中的token
	 */
	private String getToken(HttpServletRequest request) {
		String token = request.getHeader(CoreContextConstant.AUTHORIZATION);
		if (StringUtils.isEmpty(token)) {
			token = request.getParameter(CoreContextConstant.AUTHORIZATION);
			if (StringUtils.isEmpty(token)) {
				token = null;
			}
		}
		return token;
	}


}
