package com.fallframework.platform.starter.rbac.model;

import com.fallframework.platform.starter.api.response.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class RolePermissionResponse extends BaseEntityResponse {

	private static final long serialVersionUID = -7434236526548639238L;

	private Long id;

	private String roleCode;

	private String permissionCode;
	
	private String resourceValue;

	private AuthcTypeEnum authcType;
	
}
