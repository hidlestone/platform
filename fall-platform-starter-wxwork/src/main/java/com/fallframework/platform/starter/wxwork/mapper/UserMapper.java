package com.fallframework.platform.starter.wxwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.wxwork.entity.User;

public interface UserMapper extends BaseMapper<User> {

	Page<User> list(Page<User> page, User user);
	
}