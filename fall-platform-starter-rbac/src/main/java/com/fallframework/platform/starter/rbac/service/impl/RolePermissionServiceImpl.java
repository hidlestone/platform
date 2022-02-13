package com.fallframework.platform.starter.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.rbac.entity.RolePermission;
import com.fallframework.platform.starter.rbac.mapper.RolePermissionMapper;
import com.fallframework.platform.starter.rbac.service.RolePermissionService;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

}
