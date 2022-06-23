package com.fallframework.platform.starter.wechatwork.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.wechatwork.entity.DeptUser;
import com.fallframework.platform.starter.wechatwork.mapper.DeptUserMapper;
import com.fallframework.platform.starter.wechatwork.service.DeptUserService;
import org.springframework.stereotype.Service;

@Service
public class DeptUserServiceImpl extends ServiceImpl<DeptUserMapper, DeptUser> implements DeptUserService {

}
