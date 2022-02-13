package com.fallframework.platform.starter.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fallframework.platform.starter.rbac.entity.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

	List<Role> getRolesByUserId(Long userId);

}