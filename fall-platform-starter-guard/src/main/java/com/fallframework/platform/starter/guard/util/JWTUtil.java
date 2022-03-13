package com.fallframework.platform.starter.guard.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fallframework.platform.starter.config.service.PlatformSysParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

/**
 * JWT工具类
 * TODO token自动刷新问题
 *
 * @author zhuangpf
 */
public class JWTUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtil.class);

	@Autowired
	private static PlatformSysParamUtil platformSysParamUtil;

	/**
	 * 默认token发布者
	 */
	private static final String defaultIssuer;
	/**
	 * JWT加密key
	 */
	private static final String jwtSecretKey;
	/**
	 * JWT过期时间(min)，默认值：15分钟
	 */
	private static final Long JWT_TTL;

	static {
		Map<String, String> jwtConfigMap = platformSysParamUtil.getSysParamGroupItemMap("JWT").getData();
		defaultIssuer = jwtConfigMap.get("DEFAULT_ISSUER");
		jwtSecretKey = jwtConfigMap.get("JWT_SECRET_KEY");
		JWT_TTL = Long.valueOf(jwtConfigMap.get("JWT_TTL"));
		LOGGER.info("JWT : " + jwtConfigMap.toString());
	}

	/**
	 * /**
	 * 为用户构建token<br>
	 * 使用如下字段构建：
	 *
	 * @param userId     用户ID
	 * @param userName   用户名
	 * @param clientCode 客户端编码
	 * @param extraInfo  额外信息
	 * @return token
	 */
	public static String buildJWTTokenForUser(String userId, String userName, String clientCode, String extraInfo) {
		// 使用HMAC256算法
		Algorithm algo = Algorithm.HMAC256(jwtSecretKey);
		// 过期时间
		Date expireDate = new Date(System.currentTimeMillis() + JWT_TTL);
		String token = JWT.create().withIssuer(defaultIssuer)
				// 构建claim信息
				.withClaim("userId", userId).withClaim("userName", userName)
				.withClaim("clientCode", clientCode).withClaim("extraInfo", extraInfo)
				// 设置过期时间
				.withExpiresAt(expireDate).sign(algo);
		return token;
	}

	/**
	 * 为客户端构建token
	 */
	public static String buildJWTTokenForClient(String appId, String jwtSecretKey) {
		Algorithm algo = Algorithm.HMAC256(jwtSecretKey);
		String token = JWT.create().withIssuer(defaultIssuer).withClaim("appId", appId).sign(algo);
		return token;
	}

	/**
	 * 获取payload信息
	 */
	public static String getClaim(String token, String claimKey) {
		Algorithm algorithm = Algorithm.HMAC256(jwtSecretKey);
		JWTVerifier verifier = JWT.require(algorithm).withIssuer(defaultIssuer).build();
		DecodedJWT jwt = verifier.verify(token);
		return jwt.getClaim(claimKey).asString();
	}

	/**
	 * 获取token过期日期
	 */
	public static Date getExpireDate(String token) {
		Algorithm algorithm = Algorithm.HMAC256(jwtSecretKey);
		JWTVerifier verifier = JWT.require(algorithm).withIssuer(defaultIssuer).build();
		DecodedJWT jwt = verifier.verify(token);
		return jwt.getExpiresAt();
	}

	/**
	 * 获取cacheKey信息
	 */
	public static String getCacheKey(String token) {
		return getClaim(token, "cacheKey");
	}
}
