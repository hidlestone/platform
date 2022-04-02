package com.fallframework.platform.starter.rbac.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.rbac.entity.Menu;
import com.fallframework.platform.starter.rbac.mapper.MenuMapper;
import com.fallframework.platform.starter.rbac.model.MenuQueryRequest;
import com.fallframework.platform.starter.rbac.model.MenuRequest;
import com.fallframework.platform.starter.rbac.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public ResponseResult<Page<Menu>> list(MenuRequest request) {
		Page<Menu> page = new Page<>(request.getPageNum(), request.getPageSize());
		page = menuMapper.list(page, request);
		return ResponseResult.success(page);
	}

	@Override
	public ResponseResult<Page<Menu>> getMenusByUserId(MenuQueryRequest request) {
		Page<Menu> page = new Page<>(request.getPageNum(), request.getPageSize());
		page = menuMapper.getMenusByUserId(page, request.getUserId());
		return ResponseResult.success(page);
	}

	@Override
	public ResponseResult<Page<Menu>> getMenusByRoleIds(MenuQueryRequest request) {
		Page<Menu> page = new Page<>(request.getPageNum(), request.getPageSize());
		page = menuMapper.getMenusByRoleIds(page, request.getRoleIds());
		return ResponseResult.success(page);
	}

	@Override
	public ResponseResult<List<Menu>> getAllMenusByUserId(Long userId) {
		List<Menu> menuList = menuMapper.getAllMenusByUserId(userId);
		return ResponseResult.success(menuList);
	}
	
}
