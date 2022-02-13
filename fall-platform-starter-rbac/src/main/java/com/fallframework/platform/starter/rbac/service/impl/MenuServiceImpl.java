package com.fallframework.platform.starter.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.rbac.entity.Menu;
import com.fallframework.platform.starter.rbac.mapper.MenuMapper;
import com.fallframework.platform.starter.rbac.service.MenuService;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
