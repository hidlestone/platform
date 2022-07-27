package com.fallframework.platform.starter.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.rbac.entity.User;

public interface UserService extends IService<User> {

	Leaf<User> list(User user);

}
