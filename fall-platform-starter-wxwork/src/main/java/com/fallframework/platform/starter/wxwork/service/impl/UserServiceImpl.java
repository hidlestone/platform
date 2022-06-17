package com.fallframework.platform.starter.wxwork.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.wxwork.entity.User;
import com.fallframework.platform.starter.wxwork.mapper.UserMapper;
import com.fallframework.platform.starter.wxwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public ResponseResult<Page<User>> list(User user) {
		Page<User> page = new Page<>(user.getPageNum(), user.getPageSize());
		page = userMapper.list(page, user);
		return ResponseResult.success(page);
	}

}
