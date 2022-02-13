package com.fallframework.platform.starter.security.config;

import cn.hutool.core.collection.CollectionUtil;
import com.fallframework.platform.starter.security.constant.SecurityStarterConstant;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * 基于资源的访问控制
 *
 * @author zhuangpf
 */
@Component
public class ResourceAuthenticationControl {

	/**
	 * 判断请求的URL是否有权限访问
	 *
	 * @param request        request
	 * @param authentication 认证对象
	 * @return 是否有权限访问
	 */
	public boolean canAccess(HttpServletRequest request, Authentication authentication) {
		Object principal = authentication.getPrincipal();
		// admin 则所有资源都可以访问
		/*if ("admin".equals(principal) || ((principal instanceof User) && "admin".equals(((User) principal).getAccount()))) {
			return true;
		}*/
		// 如果没有通过认证，则不能访问， anonymousUser是springSecurity放入的匿名用户
		if (principal == null || SecurityStarterConstant.ANONYMOUS_USER.equals(principal)) {
			return false;
		}
		//匿名访问的url，在之前配置了，所以这里不通过
		if (authentication instanceof AnonymousAuthenticationToken) {
			return false;
		}
		// 用户拥有的权限集合，也就是在UserServiceImpl放入的权限
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		AntPathRequestMatcher matcher;
		String resUrl = "";
		boolean flag = false;
		if (CollectionUtil.isNotEmpty(authorities)) {
			for (GrantedAuthority grantedAuthority : authorities) {
				resUrl = grantedAuthority.getAuthority();
				matcher = new AntPathRequestMatcher(resUrl);
				if (matcher.matches(request)) {
					// 匹配成功，返回true
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

}
