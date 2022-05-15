package com.fallframework.platform.starter.i18n.service;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.i18n.entity.I18nResource;

/**
 * @author zhuangpf
 */
public interface TranslateService {

	/**
	 * 翻译词条
	 *
	 * @param i18nResource 请求参数
	 * @return 多语言词条
	 */
	ResponseResult<I18nResource> translate(I18nResource i18nResource);

}
