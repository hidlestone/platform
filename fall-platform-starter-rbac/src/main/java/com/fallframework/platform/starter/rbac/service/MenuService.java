package com.fallframework.platform.starter.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.data.mp.model.Leaf;
import com.fallframework.platform.starter.rbac.entity.Menu;

import java.util.List;

public interface MenuService extends IService<Menu> {

	Leaf<Menu> list(Menu menu);

	List<Menu> getAllMenusByUserId(Long userId);

	List<Menu> getMenuTree(Menu menu);

	List<Menu> getMenusByParentId(Long parentId);

}
