package com.fallframework.platform.starter.rbac.constant;

/**
 * @author zhuangpf
 */
public class RbacStarterConstant {

	/**
	 * 双token动态刷新策略<br/>
	 * 缓存key：访问token，user:accesstoken:{id}
	 */
	public static final String CACHE_KEY_ACCESSTOKEN = "user:accesstoken:";

	/**
	 * 缓存key：刷新token，user:refreshtoken:{id}
	 */
	public static final String CACHE_KEY_REFRESHTOKEN = "user:refreshtoken:";

	/**
	 * 权限缓存key：role:permission:{id}
	 */
	public static final String CACHE_KEY_ROLE_PERMISSION = "role:permission";

}
