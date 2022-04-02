package com.fallframework.platform.starter.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.rbac.entity.Menu;
import com.fallframework.platform.starter.rbac.model.MenuRequest;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

	Page<Menu> list(Page<Menu> page, MenuRequest request);

	Page<Menu> getMenusByUserId(Page<Menu> page, Long userId);

	Page<Menu> getMenusByRoleIds(Page<Menu> page, List<Long> roleIds);

	List<Menu> getAllMenusByUserId(Long userId);

}