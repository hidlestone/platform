package com.fallframework.platform.starter.i18n.service.impl;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.i18n.model.I18nResourceResponse;
import com.fallframework.platform.starter.i18n.model.TranslateReqeust;
import com.fallframework.platform.starter.i18n.model.langCodeEnum;
import com.fallframework.platform.starter.i18n.service.TranslateService;
import com.fallframework.platform.starter.i18n.util.TraditionalSimplifyConverter;

/**
 * @author zhuangpf
 */
public class TranslateServiceImpl implements TranslateService {

	@Override
	public ResponseResult<I18nResourceResponse> translate(TranslateReqeust request) {
		I18nResourceResponse i18nResourceResponse = new I18nResourceResponse();
		// 简体->繁体
		if (langCodeEnum.zh_CN.getCode().equals(request.getSourceLangCode())
				&& langCodeEnum.zh_TW.getCode().equals(request.getTrargetLangCode())) {
			String convertTraditional = TraditionalSimplifyConverter.convert(request.getResourceValue(), TraditionalSimplifyConverter.TargetEnum.TRADITIONAL);
		}
		// 简体->en TODO 
		if (langCodeEnum.zh_CN.getCode().equals(request.getSourceLangCode())
				&& langCodeEnum.en.getCode().equals(request.getTrargetLangCode())) {

		}
		return ResponseResult.success(i18nResourceResponse);
	}

}
