package com.fallframework.platform.starter.rbac.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;

/**
 * @author zhuangpf
 */
public class RoleRequest extends BaseEntityRequest {

	private static final long serialVersionUID = 2426769720974796371L;

	private Long id;

	private String roleCode;

	private String roleName;

	private String roleDesc;

}
