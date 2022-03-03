package com.fallframework.platform.starter.guard.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.guard.entity.Permission;
import com.fallframework.platform.starter.guard.mapper.PermissionMapper;
import com.fallframework.platform.starter.guard.model.PermissionRequest;
import com.fallframework.platform.starter.guard.model.PermissionResponse;
import com.fallframework.platform.starter.guard.service.PermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public ResponseResult insert(PermissionRequest request) {
		Permission permission = new Permission();
		BeanUtils.copyProperties(request, permission);
		int i = permissionMapper.insert(permission);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult delete(Long id) {
		int i = permissionMapper.deleteById(id);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult update(PermissionRequest request) {
		Permission permission = new Permission();
		BeanUtils.copyProperties(request, permission);
		int i = permissionMapper.updateById(permission);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult<PermissionResponse> get(Long id) {
		Permission permission = permissionMapper.selectById(id);
		PermissionResponse response = new PermissionResponse();
		BeanUtils.copyProperties(permission, response);
		return ResponseResult.success(response);
	}

	@Override
	public ResponseResult<Page<Permission>> list(PermissionRequest request) {
		QueryWrapper<Permission> wrapper = new QueryWrapper();
		wrapper.like("permission_code", request.getPermissionCode())
				.or()
				.like("permission_name", request.getPermissionName());
		Page<Permission> page = new Page<>(request.getPageNum(), request.getPageSize());
		Page<Permission> resultPage = permissionMapper.selectPage(page, wrapper);
		return ResponseResult.success(resultPage);
	}

}

