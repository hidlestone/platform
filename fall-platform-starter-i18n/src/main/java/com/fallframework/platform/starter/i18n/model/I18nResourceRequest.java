package com.fallframework.platform.starter.i18n.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class I18nResourceRequest extends BasePageRequest {

	private static final long serialVersionUID = 7878442368913931813L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 语言编码
	 */
	private String langCode;

	/**
	 * 资源key
	 */
	private String resourceKey;

	/**
	 * 资源value
	 */
	private String resourceValue;

	/**
	 * 所属module
	 */
	private String moduleCode;

}
