package com.fallframework.platform.starter.i18n.model;

import com.fallframework.platform.starter.core.entity.request.BaseEntityRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 翻译词条请求参数
 *
 * @author zhuangpf
 */
@Getter
@Setter
public class TranslateReqeust extends BaseEntityRequest {

	private static final long serialVersionUID = 5918603763590912033L;

	private String resourceValue;

	private String sourceLangCode;

	private String trargetLangCode;
	
}
