package com.fallframework.platform.starter.i18n.model;

import com.fallframework.platform.starter.data.mp.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class TranslateDto extends BaseEntity {

	private static final long serialVersionUID = 8226446624782625014L;

	/**
	 * 源语言编码
	 */
	private String sourceLangCode;

	/**
	 * 目标语言编码
	 */
	private String trargetLangCode;

	/**
	 * 资源value
	 */
	private String resourceValue;

}
