package com.fallframework.platform.starter.rbac.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.rbac.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {

	List<Role> getRolesByUserId(Long userId);

	ResponseResult<Page<Role>> list(Role role);

	ResponseResult<List<Role>> getAllRole();

}
