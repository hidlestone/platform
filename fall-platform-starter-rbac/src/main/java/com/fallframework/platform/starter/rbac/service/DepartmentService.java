package com.fallframework.platform.starter.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.data.mp.model.Leaf;
import com.fallframework.platform.starter.rbac.entity.Department;

import java.util.List;

public interface DepartmentService extends IService<Department> {

	Leaf<Department> list(Department department);

	List<Department> getDepartmentTree(Department department);

	List<Department> getDepartmentsByParentId(Long parentId);
}
