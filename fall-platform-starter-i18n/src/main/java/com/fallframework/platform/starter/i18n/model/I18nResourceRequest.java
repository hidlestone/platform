package com.fallframework.platform.starter.i18n.model;

import com.fallframework.platform.starter.core.entity.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class I18nResourceRequest extends BasePageRequest {

	private String langCode;
	
}
