package com.fallframework.platform.starter.config.model;

import com.fallframework.platform.starter.core.entity.response.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class SysParamItemResponse extends BaseEntityResponse {

	private static final long serialVersionUID = 5238751258431396649L;

	private String code;

	private String value;

	private String encryptedValue;

	private String desc;

	private String status;

	private String groupCode;
	
}
