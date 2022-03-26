package com.fallframework.platform.starter.rbac.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.rbac.entity.Menu;
import com.fallframework.platform.starter.rbac.mapper.MenuMapper;
import com.fallframework.platform.starter.rbac.model.MenuRequest;
import com.fallframework.platform.starter.rbac.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
