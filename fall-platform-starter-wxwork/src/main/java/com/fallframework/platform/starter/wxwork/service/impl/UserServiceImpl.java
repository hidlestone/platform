package com.fallframework.platform.starter.wxwork.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.wxwork.entity.User;
import com.fallframework.platform.starter.wxwork.mapper.UserMapper;
import com.fallframework.platform.starter.wxwork.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
