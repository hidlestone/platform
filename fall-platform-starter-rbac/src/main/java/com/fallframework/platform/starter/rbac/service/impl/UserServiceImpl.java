package com.fallframework.platform.starter.rbac.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.data.mp.model.Leaf;
import com.fallframework.platform.starter.data.mp.util.LeafPageUtil;
import com.fallframework.platform.starter.rbac.entity.User;
import com.fallframework.platform.starter.rbac.mapper.UserMapper;
import com.fallframework.platform.starter.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public Leaf<User> list(User user) {
		Page<User> page = new Page<>(user.getPageNum(), user.getPageSize());
		page = userMapper.list(page, user);
		return LeafPageUtil.pageToLeaf(page);
	}

}
