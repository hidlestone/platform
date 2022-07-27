package com.fallframework.platform.starter.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.rbac.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {

	List<Role> getRolesByUserId(Long userId);

	Leaf<Role> list(Role role);

	List<Role> getAllRole();

}
