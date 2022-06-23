package com.fallframework.platform.starter.wechatwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.wechatwork.entity.Department;

import java.util.List;

public interface DepartmentMapper extends BaseMapper<Department> {

	Page<Department> list(Page<Department> page, Department department);

	List<Department> getDepartmentTree(Department department);

	Department getDepartmentsByParentId(Long parentId);
}