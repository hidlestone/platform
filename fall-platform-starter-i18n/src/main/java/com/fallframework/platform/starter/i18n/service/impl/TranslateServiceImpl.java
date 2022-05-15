package com.fallframework.platform.starter.i18n.service.impl;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.i18n.entity.I18nResource;
import com.fallframework.platform.starter.i18n.model.langCodeEnum;
import com.fallframework.platform.starter.i18n.service.TranslateService;
import com.fallframework.platform.starter.i18n.util.TraditionalSimplifyConverter;
import org.springframework.stereotype.Service;

/**
 * 词条翻译工具类
 *
 * @author zhuangpf
 */
@Service
public class TranslateServiceImpl implements TranslateService {

	@Override
	public ResponseResult<I18nResource> translate(I18nResource i18nResource) {
		I18nResource i18nResourceResponse = new I18nResource();
		// 简体->繁体
		if (langCodeEnum.zh_CN.getCode().equals(i18nResource.getSourceLangCode())
				&& langCodeEnum.zh_TW.getCode().equals(i18nResource.getTrargetLangCode())) {
			String convertTraditional = TraditionalSimplifyConverter.convert(i18nResource.getResourceValue(), TraditionalSimplifyConverter.TargetEnum.TRADITIONAL);
		}
		// 简体->en TODO 
		if (langCodeEnum.zh_CN.getCode().equals(i18nResource.getSourceLangCode())
				&& langCodeEnum.en.getCode().equals(i18nResource.getTrargetLangCode())) {

		}
		return ResponseResult.success(i18nResourceResponse);
	}

}
