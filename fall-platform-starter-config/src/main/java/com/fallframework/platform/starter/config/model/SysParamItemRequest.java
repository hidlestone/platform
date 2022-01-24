package com.fallframework.platform.starter.config.model;

import com.fallframework.platform.starter.api.model.StatusEnum;
import com.fallframework.platform.starter.api.request.BaseEntityRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class SysParamItemRequest extends BaseEntityRequest {

	private static final long serialVersionUID = -2057514027454643999L;

	private String code;

	private String value;

	private String encryptedValue;

	private String desc;

	private StatusEnum status;

	private String groupCode;

}
