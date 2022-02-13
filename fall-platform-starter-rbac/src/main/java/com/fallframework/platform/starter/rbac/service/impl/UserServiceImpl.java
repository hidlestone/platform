package com.fallframework.platform.starter.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.rbac.entity.User;
import com.fallframework.platform.starter.rbac.mapper.UserMapper;
import com.fallframework.platform.starter.rbac.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
