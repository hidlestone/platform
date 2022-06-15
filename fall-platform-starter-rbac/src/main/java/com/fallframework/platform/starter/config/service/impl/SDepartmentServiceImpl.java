package com.fallframework.platform.starter.config.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.rbac.entity.Department;
import com.fallframework.platform.starter.rbac.mapper.SDepartmentMapper;
import com.fallframework.platform.starter.config.service.SDepartmentService;
@Service
public class SDepartmentServiceImpl extends ServiceImpl<SDepartmentMapper, Department> implements SDepartmentService{

}
