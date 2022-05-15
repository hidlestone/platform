package com.fallframework.platform.starter.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.rbac.entity.Role;
import com.fallframework.platform.starter.rbac.mapper.RoleMapper;
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
	public ResponseResult<Page<Role>> list(Role role) {
		Page<Role> page = new Page<>(role.getPageNum(), role.getPageSize());
		page = roleMapper.list(page, role);
		return ResponseResult.success(page);
	}

	@Override
	public ResponseResult<List<Role>> getAllRole() {
		QueryWrapper wrapper = new QueryWrapper();
		wrapper.orderByAsc("id");
		List<Role> roleList = roleMapper.selectList(wrapper);
		return ResponseResult.success(roleList);
	}

}
