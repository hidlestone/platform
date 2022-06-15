package com.fallframework.platform.starter.rbac.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.rbac.entity.Department;

import java.util.List;

public interface DepartmentService extends IService<Department> {

	ResponseResult<Page<Department>> list(Department department);

	ResponseResult<List<Department>> getDepartmentTree(Department department);

	ResponseResult<List<Department>> getDepartmentsByParentId(Long parentId);
}
