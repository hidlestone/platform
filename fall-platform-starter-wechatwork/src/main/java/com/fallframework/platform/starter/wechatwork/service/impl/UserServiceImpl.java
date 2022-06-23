package com.fallframework.platform.starter.wechatwork.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.wechatwork.entity.User;
import com.fallframework.platform.starter.wechatwork.mapper.UserMapper;
import com.fallframework.platform.starter.wechatwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

	@Override
	public ResponseResult<List<User>> getUsersByDeptId(Long deptId) {
		List<User> userList = userMapper.getUsersByDeptId(deptId);
		return ResponseResult.success(userList);
	}

}
