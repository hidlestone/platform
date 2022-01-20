package com.fallframework.platform.starter.i18n.service;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.i18n.model.I18nResourceResponse;
import com.fallframework.platform.starter.i18n.model.TranslateReqeust;

/**
 * @author zhuangpf
 */
public interface TranslateService {

	/**
	 * 翻译词条
	 *
	 * @param request 请求参数
	 * @return 多语言词条
	 */
	ResponseResult<I18nResourceResponse> translate(TranslateReqeust request);

}
