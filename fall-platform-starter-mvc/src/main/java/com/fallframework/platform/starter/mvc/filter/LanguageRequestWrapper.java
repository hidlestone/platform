package com.fallframework.platform.starter.mvc.filter;


import com.fallframework.platform.starter.core.constant.CoreContextConstant;
import com.fallframework.platform.starter.i18n.service.MessageBundleUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

/**
 * 装饰者模式，多语言增强
 *
 * @author payn
 */
public class LanguageRequestWrapper extends HttpServletRequestWrapper {

	public LanguageRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	public Locale getLocale() {
		// 语言码
		String langCode = this.getHeader(CoreContextConstant.LANGUAGE);
		if (StringUtils.isNotBlank(langCode)) {
			return MessageBundleUtil.toLocale(langCode);
		} else {
			// 遍历cookie，找到i18n_key
			Cookie[] cookieArr = this.getCookies();
			Optional<Cookie> cookie = Arrays.stream(cookieArr).filter(it -> CoreContextConstant.LANGUAGE.equals(it.getName())).findFirst();
			if ((null != cookie.get()) && StringUtils.isNotEmpty(cookie.get().getValue())) {
				return MessageBundleUtil.toLocale(cookie.get().getValue());
			}
			// 默认zh_CN
			return MessageBundleUtil.toLocale(CoreContextConstant.DEFAULT_LANGUAGE);
		}
	}
}
