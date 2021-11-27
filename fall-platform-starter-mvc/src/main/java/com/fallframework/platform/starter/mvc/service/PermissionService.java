package com.fallframework.platform.starter.mvc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.core.entity.response.ResponseResult;
import com.fallframework.platform.starter.mvc.entity.Permission;
import com.fallframework.platform.starter.mvc.model.PermissionRequest;
import com.fallframework.platform.starter.mvc.model.PermissionResponse;

public interface PermissionService extends IService<Permission> {

	ResponseResult insert(PermissionRequest request);

	ResponseResult delete(Long id);

	ResponseResult update(PermissionRequest request);

	ResponseResult<PermissionResponse> get(Long id);

	ResponseResult<Page<Permission>> list(PermissionRequest request);
}

