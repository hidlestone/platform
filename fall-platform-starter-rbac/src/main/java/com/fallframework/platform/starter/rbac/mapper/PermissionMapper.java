package com.fallframework.platform.starter.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fallframework.platform.starter.rbac.entity.Permission;
import com.fallframework.platform.starter.rbac.model.RolePermissionResponse;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {
	
	List<RolePermissionResponse> selectAllRolePermission();

	List<RolePermissionResponse> getPermissionListByUserId(Long id);
	
}