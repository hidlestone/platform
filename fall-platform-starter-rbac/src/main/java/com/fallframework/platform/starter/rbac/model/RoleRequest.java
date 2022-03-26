package com.fallframework.platform.starter.rbac.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;

/**
 * @author zhuangpf
 */
public class RoleRequest extends BasePageRequest {

	private static final long serialVersionUID = 2426769720974796371L;

	private Long id;

	private String roleCode;

	private String roleName;

	private String roleDesc;

}
