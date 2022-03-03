package com.fallframework.platform.starter.guard.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.guard.entity.Permission;
import com.fallframework.platform.starter.guard.model.PermissionRequest;
import com.fallframework.platform.starter.guard.model.PermissionResponse;

public interface PermissionService extends IService<Permission> {

	ResponseResult insert(PermissionRequest request);

	ResponseResult delete(Long id);

	ResponseResult update(PermissionRequest request);

	ResponseResult<PermissionResponse> get(Long id);

	ResponseResult<Page<Permission>> list(PermissionRequest request);
}

