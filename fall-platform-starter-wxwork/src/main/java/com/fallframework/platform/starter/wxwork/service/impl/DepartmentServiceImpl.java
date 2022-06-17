package com.fallframework.platform.starter.wxwork.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.wxwork.entity.Department;
import com.fallframework.platform.starter.wxwork.mapper.DepartmentMapper;
import com.fallframework.platform.starter.wxwork.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public ResponseResult<Page<Department>> list(Department department) {
		Page<Department> page = new Page<>(department.getPageNum(), department.getPageSize());
		page = departmentMapper.list(page, department);
		return ResponseResult.success(page);
	}

}
