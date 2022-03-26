package com.fallframework.platform.starter.rbac.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;

/**
 * @author zhuangpf
 */
public class PermissionRequest extends BaseEntityRequest {

	private static final long serialVersionUID = -4168044877328604448L;
	
	private Long id;

	private String permissionCode;

	private String permissionName;

	private String resourceValue;

	private Integer orderNum;

	private AuthcTypeEnum authcType;
}
