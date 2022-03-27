package com.fallframework.platform.starter.rbac.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.rbac.entity.Menu;
import com.fallframework.platform.starter.rbac.model.MenuQueryRequest;
import com.fallframework.platform.starter.rbac.model.MenuRequest;

public interface MenuService extends IService<Menu> {

	ResponseResult<Page<Menu>> list(MenuRequest request);

	ResponseResult<Page<Menu>> getMenusByUserId(MenuQueryRequest request);

	ResponseResult<Page<Menu>> getMenusByRoleIds(MenuQueryRequest request);

}
