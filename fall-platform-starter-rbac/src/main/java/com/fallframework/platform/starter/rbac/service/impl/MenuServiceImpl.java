package com.fallframework.platform.starter.rbac.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.data.mp.util.LeafPageUtil;
import com.fallframework.platform.starter.rbac.entity.Menu;
import com.fallframework.platform.starter.rbac.mapper.MenuMapper;
import com.fallframework.platform.starter.rbac.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public Leaf<Menu> list(Menu menu) {
		Page<Menu> page = new Page<>(menu.getPageNum(), menu.getPageSize());
		page = menuMapper.list(page, menu);
		return LeafPageUtil.pageToLeaf(page);
	}

	@Override
	public List<Menu> getAllMenusByUserId(Long userId) {
		List<Menu> menuList = menuMapper.getAllMenusByUserId(userId);
		return menuList;
	}

	@Override
	public List<Menu> getMenuTree(Menu menu) {
		List<Menu> menuList = menuMapper.getMenuTree(menu);
		return menuList;
	}

	@Override
	public List<Menu> getMenusByParentId(Long parentId) {
		List<Menu> menuList = menuMapper.getMenusByParentId(parentId);
		return menuList;
	}

}
