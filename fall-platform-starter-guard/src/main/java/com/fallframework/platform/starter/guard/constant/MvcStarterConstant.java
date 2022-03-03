package com.fallframework.platform.starter.guard.constant;

/**
 * @author zhuangpf
 */
public class MvcStarterConstant {

	public static final String GET_UAER_AUTH_INFO_URL = "/getuserauthinfo";
	public static final String HAS_PERMISSION_URL = "/haspermission";

	public static final String SLASH = "/";

	// 缓存key：sign:nonce:
	public static final String SIGN_NONCE = "sign:nonce:";

	// nonce默认超时时间60(s)
	public static final Integer INIT_NONCE_TIMEOUT = 60000;
}
