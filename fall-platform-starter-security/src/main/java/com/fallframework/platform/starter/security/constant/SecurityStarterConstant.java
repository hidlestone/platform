package com.fallframework.platform.starter.security.constant;

/**
 * 常量
 *
 * @author zhuangpf
 */
public class SecurityStarterConstant {

	/**
	 * redis中用户token缓存key：user:token:{id}
	 */
	public static final String CACHE_KEY_USER_TOKEN = "user:token";

	/**
	 * 权限缓存key：role:permission:{id}
	 */
	public static final String CACHE_KEY_ROLE_PERMISSION = "role:permission";

	/**
	 * 匿名用户
	 */
	public static final String ANONYMOUS_USER = "anonymousUser";

	/**
	 * 管理员
	 */
	public static final String ADMIN = "admin";

}
