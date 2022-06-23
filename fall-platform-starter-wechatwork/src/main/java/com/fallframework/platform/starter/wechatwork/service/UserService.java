package com.fallframework.platform.starter.wechatwork.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.wechatwork.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService extends IService<User> {

	ResponseResult<Page<User>> list(User user);

	ResponseResult<List<User>> getUsersByDeptId(@RequestParam Long deptId);

}
