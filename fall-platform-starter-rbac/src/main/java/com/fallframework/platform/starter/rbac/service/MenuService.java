package com.fallframework.platform.starter.rbac.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.rbac.entity.Menu;

import java.util.List;

public interface MenuService extends IService<Menu> {

	ResponseResult<Page<Menu>> list(Menu menu);

	ResponseResult<List<Menu>> getAllMenusByUserId(Long userId);

	ResponseResult<List<Menu>> getMenuTree(Menu menu);

	ResponseResult<List<Menu>> getMenusByParentId(Long parentId);

}
