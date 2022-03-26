package com.fallframework.platform.starter.rbac.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.rbac.entity.User;
import com.fallframework.platform.starter.rbac.mapper.UserMapper;
import com.fallframework.platform.starter.rbac.model.UserQueryRequest;
import com.fallframework.platform.starter.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public ResponseResult<Page<User>> list(UserQueryRequest request) {
		Page<User> page = new Page<>(request.getPageNum(), request.getPageSize());
		page = userMapper.list(page, request);
		return ResponseResult.success(page);
	}

}
