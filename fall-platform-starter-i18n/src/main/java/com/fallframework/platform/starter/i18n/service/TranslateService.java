package com.fallframework.platform.starter.i18n.service;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.i18n.entity.I18nResource;
import com.fallframework.platform.starter.i18n.model.TranslateDto;

/**
 * @author zhuangpf
 */
public interface TranslateService {

	/**
	 * 翻译词条
	 *
	 * @param dto 请求参数
	 * @return 多语言词条
	 */
	ResponseResult<I18nResource> translate(TranslateDto dto);

}
