package com.fallframework.platform.starter.shiro.model;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fallframework.platform.starter.api.model.StatusEnum;
import com.fallframework.platform.starter.rbac.entity.Role;
import com.fallframework.platform.starter.rbac.entity.User;
import com.fallframework.platform.starter.rbac.model.RolePermissionResponse;
import com.fallframework.platform.starter.rbac.service.PermissionService;
import com.fallframework.platform.starter.rbac.service.RoleService;
import com.fallframework.platform.starter.rbac.service.UserService;
import com.fallframework.platform.starter.shiro.config.JWTToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhuangpf
 */
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;

	/**
	 * 大坑，必须重写此方法，不然Shiro会报错
	 */
	/*@Override
	public boolean supports(AuthenticationToken authenticationToken) {
		return authenticationToken instanceof JWTToken;
	}*/
	
	/**
	 * 获取角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 用户
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		// 用户角色集
		List<Role> roleList = roleService.getRolesByUserId(user.getId());
		Set<String> roleSet = new HashSet<String>();
		for (Role role : roleList) {
			roleSet.add(role.getRoleCode());
		}
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.setRoles(roleSet);
		// 用户权限集
		List<RolePermissionResponse> permissionResponseList = permissionService.getPermissionListByUserId(user.getId());
		Set<String> permissionSet = new HashSet<String>();
		for (RolePermissionResponse permission : permissionResponseList) {
			permissionSet.add(permission.getResourceValue());
		}
		simpleAuthorizationInfo.setStringPermissions(permissionSet);
		return simpleAuthorizationInfo;
	}

	/**
	 * 登录验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 账号密码
		String account = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("account", account);
		wrapper.eq("password", password);
		User user = userService.getOne(wrapper);
		if (null == user) {
			// 用户名或者密码错误
			throw new UnknownAccountException("account or password is not correct.");
		}
		if (StatusEnum.DISABLE.equals(user.getStatus())) {
			throw new LockedAccountException("account is disable, contact the admin.");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}

	@Override
	protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	/**
	 * 清除缓存
	 */
	/*public void clearCache() {
		System.out.println("清除缓存数据");
		Subject subject=SecurityUtils.getSubject();
		// 调用子类去清理缓存
		super.clearCache(subject.getPrincipals());
	}*/
}
