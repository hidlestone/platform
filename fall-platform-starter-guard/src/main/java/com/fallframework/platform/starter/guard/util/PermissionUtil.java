package com.fallframework.platform.starter.guard.util;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.core.context.ClientToken;
import com.fallframework.platform.starter.core.context.UserAuthInfo;
import com.fallframework.platform.starter.core.util.EncryptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 权限工具类
 *
 * @author zhuangpf
 */
public class PermissionUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(PermissionUtil.class);

	@Autowired
	private static RedisUtil redisUtil;

	// 平台客户端 token 缓存 key
	public static final String CONSOLE_CLIENT_TOKEN = "CONSOLE:CLIENT_TOKEN:";

	// 用户权限类型缓存key
	// 用户所有资源URL
	public static final String USER_RESOURCE_CACHEKEY = "SYSTEM:PERMISSION:USERRESOURCE:";
	public static final String USER_PERMISSION_CACHEKEY = "SYSTEM:PERMISSION:USERPERMISSION:";
	public static final String USER_MODULE_CACHEKEY = "SYSTEM:PERMISSION:USERMODULE:";
	// 授权权限值如：wordplay_mvc_test
	public static final String AUTH_PERMISSION_CACHEKEY = "SYSTEM:PERMISSION:AUTHPERMISSION";
	// 授权资源值即资源URL：/wordplay/mvc/test
	public static final String AUTH_RESOURCE_CACHEKEY = "SYSTEM:PERMISSION:AUTHRESOURCE";
	// 匿名权限值
	public static final String ANON_PERMISSION_CACHEKEY = "SYSTEM:PERMISSION:ANONPERMISSION";
	// 匿名资源值
	public static final String ANON_RESOURCE_CACHEKEY = "SYSTEM:PERMISSION:ANONRESOURCE";
	// 所有权限值(授权+匿名)
	public static final String ALL_PERMISSION_CACHEKEY = "SYSTEM:PERMISSION:ALLPERMISSION";
	// 所有资源值(授权+匿名)
	public static final String ALL_RESOURCE_CACHEKEY = "SYSTEM:PERMISSION:ALLRESOURCE";

	public static final String RESOURCE_AND_MODULE = "SYSTEM:PERMISSION:RESOURCEANDMODULE";


	/**
	 * 根据 token 获取用户授权信息
	 */
	public static UserAuthInfo getUserAuthInfo(String token) {
		if (token.startsWith("Basic")) {
			String toekenTmp = token.replace("Basic ", "");
			String[] decrypted = EncryptionUtil.decryptBASE64(toekenTmp, "UTF-8").split(":");
			String appCode = decrypted[0];
			Map<String, Object> appMap = (Map) redisUtil.hget("SYSTEM:APP", appCode);
			UserAuthInfo userAuthInfo = new UserAuthInfo();
			userAuthInfo.setClientCode("000");
			userAuthInfo.setAppId(appCode);
			userAuthInfo.setToken(token);
			userAuthInfo.setUserCode(appCode);
			userAuthInfo.setUserId(0L);
			userAuthInfo.setUserName(appMap.get("appName").toString());
			return userAuthInfo;
		}
		String appId = null;
		try {
			appId = JWTUtil.getClaim(token, "appId");
		} catch (Exception e) {
			LOGGER.error("JWT get appid exception.");
		}
		if (StringUtils.isNotEmpty(appId)) {
			ClientToken clientToken = (ClientToken) redisUtil.get(CONSOLE_CLIENT_TOKEN + appId);
			if (null == clientToken) {
				return null;
			}
			UserAuthInfo userAuthInfo = new UserAuthInfo();
			userAuthInfo.setClientCode("000");
			userAuthInfo.setAppId(appId);
			userAuthInfo.setToken(token);
			userAuthInfo.setUserCode(appId);
			userAuthInfo.setUserId(0L);
			userAuthInfo.setUserName(clientToken.getAppName());
			return userAuthInfo;
		}
		return (UserAuthInfo) redisUtil.get(JWTUtil.getCacheKey(token));
	}

	public static boolean isAnonResource(String url) {
		return redisUtil.isSetMember(ANON_RESOURCE_CACHEKEY, url);
	}

	/**
	 * 校验权限
	 */
	public static ResponseResult<Object> checkPermission(String token, String url) {
		if (StringUtils.isBlank(token)) {
			return ResponseResult.fail("token is blank");
		}
		UserAuthInfo authInfo = getUserAuthInfo(token);
		if (null == authInfo) {
			return ResponseResult.fail("token is invalid");
		}
		if (!token.equals(authInfo.getToken())) {
			return ResponseResult.fail("token is mismatch");
		}
		return ResponseResult.success();
	}
}
