package com.fallframework.platform.starter.rbac.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.rbac.entity.Permission;
import com.fallframework.platform.starter.rbac.entity.Role;
import com.fallframework.platform.starter.rbac.mapper.RoleMapper;
import com.fallframework.platform.starter.rbac.model.RoleRequest;
import com.fallframework.platform.starter.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Role> getRolesByUserId(Long userId) {
		return roleMapper.getRolesByUserId(userId);
	}

	@Override
	public ResponseResult<Page<Role>> list(RoleRequest request) {
		Page<Role> page = new Page<>(request.getPageNum(), request.getPageSize());
		page = roleMapper.list(page, request);
		return ResponseResult.success(page);
	}

}
