package com.fallframework.platform.starter.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.rbac.entity.UserRole;
import com.fallframework.platform.starter.rbac.mapper.UserRoleMapper;
import com.fallframework.platform.starter.rbac.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
