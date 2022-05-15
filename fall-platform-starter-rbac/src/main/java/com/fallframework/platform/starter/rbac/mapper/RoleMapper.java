package com.fallframework.platform.starter.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.rbac.entity.Role;
import com.fallframework.platform.starter.rbac.model.RoleRequest;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

	List<Role> getRolesByUserId(Long userId);

	Page<Role> list(Page<Role> page, Role role);
}