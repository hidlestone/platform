package com.fallframework.platform.starter.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.rbac.entity.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

	Page<Menu> list(Page<Menu> page, Menu menu);

	List<Menu> getAllMenusByUserId(Long userId);

	List<Menu> getMenuTree(Menu menu);

	List<Menu> getMenusByParentId(Long parentId);
}