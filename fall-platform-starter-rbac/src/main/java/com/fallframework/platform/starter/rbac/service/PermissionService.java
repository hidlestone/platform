package com.fallframework.platform.starter.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.rbac.entity.Permission;

public interface PermissionService extends IService<Permission> {

	/**
	 * 更新权限信息到缓存中
	 *
	 * @return 是否更新成功
	 */
	ResponseResult refreshPermissionCache();

}
