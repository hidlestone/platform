package com.fallframework.platform.starter.security.filter;

import com.alibaba.fastjson.JSON;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.rbac.model.RolePermissionResponse;
import com.fallframework.platform.starter.rbac.service.PermissionService;
import com.fallframework.platform.starter.security.constant.SecurityStarterConstant;
import com.fallframework.platform.starter.security.entity.User;
import com.fallframework.platform.starter.security.util.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 授权过滤器
 *
 * @author zhuangpf
 */
public class TokenAuthFilter extends BasicAuthenticationFilter {

	private RedisUtil redisUtil;
	private PermissionService permissionService;

	public TokenAuthFilter(AuthenticationManager authenticationManager, RedisUtil redisUtil, PermissionService permissionService) {
		super(authenticationManager);
		this.redisUtil = redisUtil;
		this.permissionService = permissionService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 从header获取token
		String token = request.getHeader(CoreContextConstant.TOKEN);
		if (StringUtils.isBlank(token)) {
			ResponseResult responseResult = ResponseResult.fail("invalid token");
//			response.getWriter().write(JSON.toJSONString(responseResult));
			throw new RuntimeException(JSON.toJSONString(responseResult));
		}
		// 从token中获取用户id
		String idStr = JWTUtil.getClaim(token, "id");
		if (StringUtils.isBlank(idStr)) {
			ResponseResult responseResult = ResponseResult.fail("invalid token");
			throw new RuntimeException(JSON.toJSONString(responseResult));
		}
		// 从缓存中获取token进行比较
		String authtoken = (String) redisUtil.hget(SecurityStarterConstant.CACHE_KEY_USER_TOKEN, idStr);
		if (!authtoken.equals(token)) {
			ResponseResult responseResult = ResponseResult.fail("invalid token");
			throw new RuntimeException(JSON.toJSONString(responseResult));
		}
		// 获取当前认证成功的用户的权限信息
		UsernamePasswordAuthenticationToken authentication = this.getAuthentication(token, idStr);
		// 放入上下文中
		if (null != authentication) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);
	}

	/**
	 * 从token获取用户名，从redis获取对应权限列表,
	 */
	private UsernamePasswordAuthenticationToken getAuthentication(String token, String idStr) {
		// 获取用户资源权限
		List<RolePermissionResponse> permissionList = permissionService.getPermissionListByUserId(Long.valueOf(idStr));
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (RolePermissionResponse item : permissionList) {
			authorities.add(new SimpleGrantedAuthority(item.getResourceValue()));
		}
		User user = JWTUtil.parseToken(token);
		user.setAuthorities(authorities);
		return new UsernamePasswordAuthenticationToken(user, token, authorities);
	}

}
