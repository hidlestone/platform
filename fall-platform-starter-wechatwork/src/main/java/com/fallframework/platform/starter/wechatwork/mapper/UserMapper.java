package com.fallframework.platform.starter.wechatwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.wechatwork.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

	Page<User> list(Page<User> page, User user);

	List<User> getUsersByDeptId(Long deptId);
	
}