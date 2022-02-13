package com.fallframework.platform.starter.security.config;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fallframework.platform.starter.rbac.entity.Role;
import com.fallframework.platform.starter.rbac.service.RoleService;
import com.fallframework.platform.starter.rbac.service.UserService;
import com.fallframework.platform.starter.security.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuangpf
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 用户
		QueryWrapper<com.fallframework.platform.starter.rbac.entity.User> wrapper = new QueryWrapper<>();
		wrapper.eq("account", username);
		com.fallframework.platform.starter.rbac.entity.User one = userService.getOne(wrapper);
		if (null == one) {
			throw new UsernameNotFoundException("user is not exist.");
		}
		User user = new User();
		BeanUtils.copyProperties(one, user);
		// 角色
		List<Role> roleList = roleService.getRolesByUserId(Long.valueOf(user.getId()));
		user.setRoles(roleList);
		return user;
	}

}
