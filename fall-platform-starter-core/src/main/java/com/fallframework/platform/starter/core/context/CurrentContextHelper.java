package com.fallframework.platform.starter.core.context;

import com.fallframework.platform.starter.core.constant.CoreContextConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 当前上下文环境工具类
 *
 * @author zhuangpf
 */
public class CurrentContextHelper {

	public static final Logger LOGGER = LoggerFactory.getLogger(CurrentContextHelper.class);

	// 当前请求的线程的上下文环境
	private static final ThreadLocal<CurrentContext> CURRENT_CONTEXT = new InheritableThreadLocal();

	/**
	 * 初始化当前请求的上下文
	 *
	 * @param userAuthInfo          用户授权信息
	 * @param request               请求request
	 * @param requestTime           请求时间
	 * @param applicationInstanceId 应用实例ID
	 * @param serviceName           服务名称
	 */
	public static void initCurrentContext(UserAuthInfo userAuthInfo, HttpServletRequest request, Date requestTime, Long applicationInstanceId, String serviceName) {
		CURRENT_CONTEXT.set(new CurrentContext(userAuthInfo, request, requestTime, applicationInstanceId, serviceName));
	}

	/**
	 * 更新当前请求的上下文
	 *
	 * @param userAuthInfo 用户授权信息
	 * @param request      请求request
	 * @param requestTime  请求时间
	 */
	public static void updateCurrentContext(UserAuthInfo userAuthInfo, HttpServletRequest request, Date requestTime) {
		CurrentContext currentContext = (CurrentContext) CURRENT_CONTEXT.get();
		currentContext.setUserAuthInfo(userAuthInfo);
		currentContext.setRequestTime(requestTime);
		currentContext.setUserAuthInfo(userAuthInfo);
	}

	/**
	 * 获取当前请求的上下文
	 *
	 * @return 当前请求上下文
	 */
	public static CurrentContext getCurrent() {
		CurrentContext currentContext = (CurrentContext) CURRENT_CONTEXT.get();
		return currentContext;
	}

	/**
	 * 设置当前请求的上下文
	 *
	 * @param currentContext 请求上下文
	 */
	public static void set(CurrentContext currentContext) {
		CURRENT_CONTEXT.set(currentContext);
	}

	/**
	 * 获取授权信息
	 */
	public static UserAuthInfo getUserAuthInfo() {
		return getCurrent() == null ? null : getCurrent().getUserAuthInfo();
	}

	/**
	 * 获取userId
	 */
	public static Long getUserId() {
		if (getCurrent() == null) {
			return null;
		} else {
			return getUserAuthInfo() != null ? getUserAuthInfo().getUserId() : null;
		}
	}

	/**
	 * 获取userCode
	 */
	public static String getUserCode() {
		return getUserAuthInfo() != null ? getUserAuthInfo().getUserCode() : null;
	}

	/**
	 * 是否是admin
	 */
	public static boolean isAdmin() {
		return "admin".equals(getUserCode());
	}

	/**
	 * 获取username
	 */
	public static String getUserName() {
		return getUserAuthInfo() != null ? getUserAuthInfo().getUserName() : null;
	}

	/**
	 * 获取角色码
	 */
	public static String getRoleCodes() {
		return getUserAuthInfo() != null ? getUserAuthInfo().getRoleCodes() : null;
	}

	/**
	 * 请求时间
	 */
	public static Date getRequestTime() {
		return getCurrent() == null ? null : getCurrent().getRequestTime();
	}

	/**
	 * 获取请求IP
	 */
	public static String getUserRealIp() {
		if (getCurrent() == null) {
			return null;
		} else {
			Map<String, String> headers = getCurrent().getHttpHeaders();
			Iterator iterator = headers.keySet().iterator();
			String name;
			String value;
			do {
				if (!iterator.hasNext()) {
					iterator = headers.keySet().iterator();
					do {
						if (!iterator.hasNext()) {
							try {
								return getRequest().getRemoteAddr();
							} catch (Exception e) {
								return "";
							}
						}
						name = (String) iterator.next();
						value = (String) headers.get(name);
					} while (!"x-forwarded-for".equalsIgnoreCase(name) || !StringUtils.isNotBlank(value));
					return value.split(",")[0];
				}

				name = (String) iterator.next();
				value = (String) headers.get(name);
			} while (!"x-real-ip".equalsIgnoreCase(name) || !StringUtils.isNotBlank(value));
			return value;
		}
	}

	/**
	 * 获取请求的终端
	 */
	public static String getTerminal() {
		if (getCurrent() == null) {
			return null;
		} else {
			String clientCode = getCurrent().getHeader(CoreContextConstant.CLIENT_CODE);
			if (StringUtils.isEmpty(clientCode)) {
				return "";
			}
			return clientCode;
		}
	}

	/**
	 * 获取request对象
	 */
	public static HttpServletRequest getRequest() {
		try {
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			return servletRequestAttributes.getRequest();
		} catch (Exception var1) {
			return null;
		}
	}

	/**
	 * 请求url
	 */
	public static String getUrl() {
		return getCurrent() == null ? null : getCurrent().getUrl();
	}

	/**
	 * 获取Locale对象
	 */
	public static Locale getLocale() {
		return toLocale(getLanguageCode());
	}

	/**
	 * 获取Locale对象
	 */
	public static Locale toLocale(String locale) {
		if (!StringUtils.isBlank(locale) && !"null".equalsIgnoreCase(locale)) {
			if (locale.contains("_#")) {
				locale = locale.split("_#")[0];
			} else if (locale.contains("#")) {
				locale = locale.split("#")[0];
			}

			StringTokenizer st = new StringTokenizer(locale, "-_");
			return new Locale(st.nextToken(), st.hasMoreTokens() ? st.nextToken() : "", st.hasMoreTokens() ? st.nextToken() : "");
		} else {
			return null;
		}
	}

	/**
	 * 语言编码
	 */
	public static String getLanguageCode() {
		if (getCurrent() == null) {
			return CoreContextConstant.DEFAULT_LANGUAGE;
		} else {
			String language = getCurrent().getHeader(CoreContextConstant.LANGUAGE);
			if (StringUtils.isBlank(language)) {
				language = CoreContextConstant.DEFAULT_LANGUAGE;
			}
			return language;
		}
	}

	/**
	 * 根据name获取请求头值
	 */
	public static String getHeader(String name) {
		return getCurrent() == null ? null : getCurrent().getHeader(name);
	}

	/**
	 * 根据name获取请求头值列表
	 */
	public static List<String> getHeaders(String name) {
		return getCurrent() == null ? null : getCurrent().getHeaders(name);
	}

	/**
	 * 请求头
	 */
	public static Map<String, String> getHeaders() {
		return getCurrent() == null ? null : getCurrent().getHeaders();
	}

	/**
	 * 获取GET请求参数
	 */
	public static String getParameter(String name) {
		return getRequest() != null ? getRequest().getParameter(name) : "";
	}


	/**
	 * 清除线程变量
	 */
	public static void cleanup() {
		CURRENT_CONTEXT.remove();
	}
}
