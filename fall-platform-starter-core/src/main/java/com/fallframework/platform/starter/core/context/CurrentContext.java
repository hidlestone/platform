package com.fallframework.platform.starter.core.context;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 上下文环境
 *
 * @author zhuangpf
 */
@Getter
@Setter
public class CurrentContext implements Serializable {

	private static final long serialVersionUID = -8246330740532032461L;

	/**
	 * 应用实例ID
	 */
	private Long applicationInstanceId;
	/**
	 * 服务名称
	 */
	private String serviceName;
	/**
	 * 用户授权信息
	 */
	private UserAuthInfo userAuthInfo;
	/**
	 * 请求时间
	 */
	private Date requestTime;
	/**
	 * 请求url
	 */
	private String url;
	/**
	 * 请求头
	 */
	private Map<String, String> httpHeaders;

	/**
	 * 构造
	 */
	public CurrentContext(UserAuthInfo userAuthInfo, HttpServletRequest request, Date requestTime, Long applicationInstanceId, String serviceName) {
		this.applicationInstanceId = applicationInstanceId;
		this.serviceName = serviceName;
		this.url = "/" + serviceName + (request.getRequestURI().startsWith("/") ? request.getRequestURI() : "/" + request.getRequestURI());
		this.userAuthInfo = userAuthInfo;
		this.requestTime = requestTime;
	}

	/**
	 * 根据name获取请求头信息
	 */
	public String getHeader(String name) {
		if (this.httpHeaders == null) {
			return null;
		} else {
			String value = (String) this.httpHeaders.get(name);
			if (StringUtils.isBlank(value)) {
				value = (String) this.httpHeaders.get(name.toLowerCase());
			}
			return StringUtils.isNotBlank(value) ? value.split("@@@")[0] : null;
		}
	}

	/**
	 * 根据name获取请求头信息
	 */
	public List<String> getHeaders(String name) {
		String value = (String) this.httpHeaders.get(name);
		if (StringUtils.isBlank(value)) {
			value = (String) this.httpHeaders.get(name.toLowerCase());
		}
		return StringUtils.isNotBlank(value) ? new ArrayList(Arrays.asList(value.split("@@@"))) : null;
	}

	/**
	 * 获取请求头
	 */
	public Map<String, String> getHeaders() {
		return this.httpHeaders;
	}
}
