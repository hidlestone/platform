package com.fallframework.platform.starter.rbac.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.rbac.entity.User;

public interface UserService extends IService<User> {

	ResponseResult<Page<User>> list(User user);

}
