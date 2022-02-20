package com.fallframework.platform.starter.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fallframework.platform.starter.security.entity.User;

import java.util.Date;
import java.util.Map;

/**
 * JWT工具类
 *
 * @author zhuangpf
 */
public class JWTUtil {

	/**
	 * 默认token发布者
	 */
	private static final String DEFAULT_ISSUER = "fall-platform";
	/**
	 * JWT加密key
	 */
	private static final String JWT_SECRET_KEY = "fall-platform";
	/**
	 * JWT过期时间，默认值：24 小时
	 */
	private static final Long JWT_TTL = 24 * 60 * 60 * 1000L;

	/**
	 * 为用户构建token
	 *
	 * @param user 登录用户
	 * @return token
	 */
	public static String createToken(User user) {
		// 使用HMAC256算法
		Algorithm algo = Algorithm.HMAC256(JWT_SECRET_KEY);
		// 过期时间
		Date expireDate = new Date(System.currentTimeMillis() + JWT_TTL);
		String token = JWT.create().withIssuer(DEFAULT_ISSUER)
				// 构建claim信息
				.withClaim("id", String.valueOf(user.getId()))
				.withClaim("account", user.getAccount())
				.withClaim("username", user.getUsername())
//				.withClaim("clientCode", clientCode).withClaim("extraInfo", extraInfo)
				// 设置过期时间
				.withExpiresAt(expireDate).sign(algo);
		return token;
	}

	/**
	 * 解析token为用户
	 *
	 * @param token token
	 * @return 用户
	 */
	public static User parseToken(String token) {
		Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY);
		JWTVerifier verifier = JWT.require(algorithm).withIssuer(DEFAULT_ISSUER).build();
		DecodedJWT jwt = verifier.verify(token);
		String payload = jwt.getPayload();
		Map<String, Claim> claimMap = jwt.getClaims();
		User user = new User();
		user.setId(Long.valueOf(claimMap.get("id").asString()));
		user.setAccount(claimMap.get("account").asString());
		user.setUsername(claimMap.get("username").asString());
		return user;
	}

	/**
	 * 获取token过期日期
	 *
	 * @param token token
	 * @return 过期时间
	 */
	public static Date getExpireDate(String token) {
		Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY);
		JWTVerifier verifier = JWT.require(algorithm).withIssuer(DEFAULT_ISSUER).build();
		DecodedJWT jwt = verifier.verify(token);
		return jwt.getExpiresAt();
	}

	/**
	 * 获取payload信息
	 *
	 * @param token    token
	 * @param claimKey key
	 * @return 获取key信息
	 */
	public static String getClaim(String token, String claimKey) {
		Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY);
		JWTVerifier verifier = JWT.require(algorithm).withIssuer(DEFAULT_ISSUER).build();
		DecodedJWT jwt = verifier.verify(token);
		Claim claim = jwt.getClaim(claimKey);
		return claim.asString();
	}

}
