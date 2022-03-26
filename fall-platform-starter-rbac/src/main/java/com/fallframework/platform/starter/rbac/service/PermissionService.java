package com.fallframework.platform.starter.rbac.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.rbac.entity.Permission;
import com.fallframework.platform.starter.rbac.model.PermissionRequest;
import com.fallframework.platform.starter.rbac.model.RolePermissionResponse;

import java.util.List;

public interface PermissionService extends IService<Permission> {

	/**
	 * 更新权限信息到缓存中
	 *
	 * @return 是否更新成功
	 */
	ResponseResult refreshPermissionCache();

	/**
	 * @param id 用户ID
	 * @return 资源权限列表
	 */
	List<RolePermissionResponse> getPermissionListByUserId(Long id);

	/**
	 * @param request 请求参数
	 * @return 分页的权限列表
	 */
	ResponseResult<Page<Permission>> list(PermissionRequest request);
}
