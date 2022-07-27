package com.fallframework.platform.starter.rbac.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.data.mp.util.LeafPageUtil;
import com.fallframework.platform.starter.rbac.entity.Department;
import com.fallframework.platform.starter.rbac.mapper.DepartmentMapper;
import com.fallframework.platform.starter.rbac.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public Leaf<Department> list(Department department) {
		Page<Department> page = new Page<>(department.getPageNum(), department.getPageSize());
		page = departmentMapper.list(page, department);
		return LeafPageUtil.pageToLeaf(page);
	}

	@Override
	public List<Department> getDepartmentTree(Department department) {
		List<Department> departmentList = departmentMapper.getDepartmentTree(department);
		return departmentList;
	}

	@Override
	public List<Department> getDepartmentsByParentId(Long parentId) {
		List<Department> departmentList = departmentMapper.getDepartmentsByParentId(parentId);
		return departmentList;
	}

}
