package com.fallframework.platform.starter.wechatwork.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.wechatwork.entity.Department;

import java.util.List;

public interface DepartmentService extends IService<Department> {

	ResponseResult<Page<Department>> list(Department department);

	ResponseResult<List<Department>> getDepartmentTree(Department department);

	ResponseResult<Department> getDepartmentsByParentId(Long parentId);
	
}
