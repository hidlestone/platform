package com.fallframework.platform.starter.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.rbac.entity.User;
import com.fallframework.platform.starter.rbac.model.UserQueryRequest;

public interface UserMapper extends BaseMapper<User> {

	Page<User> list(Page<User> page, UserQueryRequest request);
	
}