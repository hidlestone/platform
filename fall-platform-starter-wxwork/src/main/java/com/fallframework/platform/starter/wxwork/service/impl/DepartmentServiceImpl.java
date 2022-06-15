package com.fallframework.platform.starter.wxwork.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.wxwork.entity.Department;
import com.fallframework.platform.starter.wxwork.mapper.DepartmentMapper;
import com.fallframework.platform.starter.wxwork.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
