package com.fallframework.platform.starter.mvc.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionRequest extends BasePageRequest {

	private static final long serialVersionUID = -7365211497019879616L;

	private Long id;

	private String permissionCode;

	private String permissionName;

	private String resourceValue;

	private Integer orderNum;

	private AuthcTypeEnum authcType;

}