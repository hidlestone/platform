package com.fallframework.platform.starter.mvc.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.core.entity.request.BaseEntityRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class PermissionResponse extends BaseEntityRequest {

	private static final long serialVersionUID = -7365211497019879616L;

	private Long id;
	
	private String permissionCode;

	private String permissionName;

	private String resourceValue;

	private Integer orderNum;

	private AuthcTypeEnum authcType;

}