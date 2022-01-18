package com.fallframework.platform.starter.i18n.service.impl;

import com.fallframework.platform.starter.core.entity.response.ResponseResult;
import com.fallframework.platform.starter.i18n.model.I18nResourceResponse;
import com.fallframework.platform.starter.i18n.model.TranslateReqeust;
import com.fallframework.platform.starter.i18n.model.langCodeEnum;
import com.fallframework.platform.starter.i18n.service.TranslateService;
import com.github.nobodxbodon.zhconverter.简繁转换类;

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
			String convertTraditional = 简繁转换类.转换(request.getResourceValue(), 简繁转换类.目标.繁体);
		}
		// 简体->en TODO 
		if (langCodeEnum.zh_CN.getCode().equals(request.getSourceLangCode())
				&& langCodeEnum.en.getCode().equals(request.getTrargetLangCode())) {

		}
		return ResponseResult.success(i18nResourceResponse);
	}

}
