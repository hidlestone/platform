package com.fallframework.platform.starter.security.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fallframework.platform.starter.rbac.model.RolePermissionResponse;
import com.fallframework.platform.starter.rbac.service.PermissionService;
import com.fallframework.platform.starter.rbac.service.UserService;
import com.fallframework.platform.starter.security.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuangpf
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;

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
		// 根据用户ID查询资源权限
		List<RolePermissionResponse> permissionList = permissionService.getPermissionListByUserId(user.getId());
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (RolePermissionResponse item : permissionList) {
			authorities.add(new SimpleGrantedAuthority(item.getResourceValue()));
		}
		user.setAuthorities(authorities);
		return user;
	}

}
