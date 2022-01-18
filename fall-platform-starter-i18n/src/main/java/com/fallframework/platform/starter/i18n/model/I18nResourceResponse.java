package com.fallframework.platform.starter.i18n.model;

import com.fallframework.platform.starter.core.entity.response.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class I18nResourceResponse extends BaseEntityResponse {

	private static final long serialVersionUID = 3210431016779977270L;
	
	/**
	 * 语言编码
	 */
	private String langCode;
	
	/**
	 * 资源value
	 */
	private String resourceValue;

}
