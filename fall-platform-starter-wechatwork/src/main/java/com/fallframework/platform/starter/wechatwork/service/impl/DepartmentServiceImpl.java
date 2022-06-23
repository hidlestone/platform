package com.fallframework.platform.starter.wechatwork.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.wechatwork.entity.Department;
import com.fallframework.platform.starter.wechatwork.mapper.DepartmentMapper;
import com.fallframework.platform.starter.wechatwork.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

	@Override
	public ResponseResult<List<Department>> getDepartmentTree(Department department) {
		List<Department> departmentList = departmentMapper.getDepartmentTree(department);
		return ResponseResult.success(departmentList);
	}

	@Override
	public ResponseResult<Department> getDepartmentsByParentId(Long parentId) {
		Department department = departmentMapper.getDepartmentsByParentId(parentId);
		return ResponseResult.success(department);
	}

}
