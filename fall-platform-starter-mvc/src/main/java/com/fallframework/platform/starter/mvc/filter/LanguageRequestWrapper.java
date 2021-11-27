package com.fallframework.platform.starter.mvc.filter;


import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.i18n.MessageBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Locale;

/**
 * 装饰者模式，多语言增强
 *
 * @author payn
 */
public class LanguageRequestWrapper extends HttpServletRequestWrapper {

	public LanguageRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	// Request Headers:language
	public static final String LANGUAGE = "language";

	public Locale getLocale() {
		// 语言码
		String langCode = this.getHeader(LANGUAGE);
		if (StringUtils.isNotBlank(langCode)) {
			return MessageBundle.toLocale(langCode);
		} else {
			// 遍历cookie，找到i18n_key
			Cookie[] cookies = this.getCookies();
			if (cookies != null) {
				Cookie[] cookieArr = cookies;
				int len = cookies.length;
				for (int i = 0; i < len; ++i) {
					Cookie cookie = cookieArr[i];
					if (CoreContextConstant.I18N_KEY.equals(cookie.getName())) {
						return MessageBundle.toLocale(cookie.getValue());
					}
				}
			}
			return MessageBundle.toLocale(super.getLocale().toString());
		}
	}
}
