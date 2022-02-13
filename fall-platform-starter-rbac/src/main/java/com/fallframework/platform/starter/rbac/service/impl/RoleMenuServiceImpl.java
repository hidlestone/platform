package com.fallframework.platform.starter.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.rbac.entity.RoleMenu;
import com.fallframework.platform.starter.rbac.mapper.RoleMenuMapper;
import com.fallframework.platform.starter.rbac.service.RoleMenuService;
import org.springframework.stereotype.Service;

@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}
