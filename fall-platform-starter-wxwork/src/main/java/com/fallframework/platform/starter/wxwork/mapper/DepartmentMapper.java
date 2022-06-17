package com.fallframework.platform.starter.wxwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.wxwork.entity.Department;

public interface DepartmentMapper extends BaseMapper<Department> {

	Page<Department> list(Page<Department> page, Department department);
	
}