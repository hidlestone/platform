package com.fallframework.platform.starter.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.config.model.SysParamGroupEnum;
import com.fallframework.platform.starter.config.service.PlatformSysParamUtil;
import com.fallframework.platform.starter.core.constant.CoreContextConstant;
import com.fallframework.platform.starter.rbac.entity.User;
import com.fallframework.platform.starter.rbac.model.TokenTypeEnum;
import com.fallframework.platform.starter.shiro.config.JWTToken;
import com.fallframework.platform.starter.shiro.constant.ShiroStarterConstant;
import com.fallframework.platform.starter.shiro.util.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * jwt过滤器，实现token的动态刷新
 *
 * @author zhuangpf
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JWTFilter.class);

	/**
	 * refreshtoken刷新的时间间隔
	 */
	private final Long SHIRO_REFRESH_TOKEN_TIME_INTERVAL;

	private RedisUtil redisUtil;
	private JWTUtil jwtUtil;
	private PlatformSysParamUtil platformSysParamUtil;

	public JWTFilter(PlatformSysParamUtil platformSysParamUtil, RedisUtil redisUtil, JWTUtil jwtUtil) {
		this.platformSysParamUtil = platformSysParamUtil;
		this.redisUtil = redisUtil;
		this.jwtUtil = jwtUtil;
		Map<String, String> sysItemMap = platformSysParamUtil.getSysParamGroupItemMap(SysParamGroupEnum.SHIRO.toString()).getData();
		SHIRO_REFRESH_TOKEN_TIME_INTERVAL = Long.valueOf(platformSysParamUtil.mapGet(sysItemMap, "SHIRO_REFRESH_TOKEN_TIME_INTERVAL"));
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		// 查看当前Header中是否携带Authorization属性(Token)，有的话就进行登录认证授权
		if (this.isLoginAttempt(request, response)) {
			try {
				// 进行shiro的登录，shiroRealm
				this.executeLogin(request, response);
			} catch (Exception e) {
				// 认证出现错误，获取错误信息
				ResponseResult responseResult = ResponseResult.success();
				// 获取应用异常(该Cause是导致抛出此throwable(异常)的throwable(异常))
				Throwable throwable = e.getCause();
				if (throwable instanceof SignatureVerificationException) {
					// 该异常为JWT的AccessToken认证失败(Token或者密钥不正确)
					String msg = "token or cipher is not correct. (" + throwable.getMessage() + ")";
					responseResult = ResponseResult.fail(msg);
				} else if (throwable instanceof TokenExpiredException) {
					// 该异常为JWT的AccessToken已过期，判断RefreshToken未过期就进行AccessToken刷新
					responseResult = refreshToken(request, response);
					if (responseResult.isSuccess()) {
						return true;
					}
				} else {
					// 应用异常不为空
					if (throwable != null) {
						// 获取应用异常msg
						String msg = throwable.getMessage();
						responseResult = ResponseResult.fail(msg);
					}
				}
				this.resetResponse(response, responseResult);
				return false;
			}
		} else {
			this.resetResponse(response, ResponseResult.fail("request header accesstoken or refreshtoken is not exist."));
			return false;
		}
		return true;
	}

	/**
	 * 此时accesstoken已经过期<br/>
	 * 进行判断refreshtoken是否过期，未过期就返回新的accesstoken，(根据条件)刷新refreshtoken且继续正常访问
	 */
	private ResponseResult refreshToken(ServletRequest request, ServletResponse response) {
		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		String accessToken = httpRequest.getHeader(CoreContextConstant.ACCESSTOKEN);
		String refreshToken = httpRequest.getHeader(CoreContextConstant.REFRESHTOKEN);
		ResponseResult responseResult = jwtUtil.accessRefreshTokeValidate(accessToken, refreshToken);
		if (!responseResult.isSuccess()) {
			return responseResult;
		}
		String idStr = jwtUtil.getClaim(refreshToken, "id");
		// 缓存中的refreshToken
		String refreshTokenCache = (String) redisUtil.get(ShiroStarterConstant.CACHE_KEY_SHIRO_REFRESHTOKEN + idStr);
		if (StringUtils.isBlank(refreshTokenCache)) {
			return ResponseResult.fail("token has expired");
		}
		if (!refreshToken.equals(refreshTokenCache)) {
			return ResponseResult.fail("invalid refreshtoken");
		}
		Date expireDate = jwtUtil.getExpireDate(refreshToken);
		// 获取当前最新时间戳
		Long currentTimeMillis = System.currentTimeMillis();
		// refreshtoken 过期时间减去当前时间小于等于刷新时间，则刷新 refreshtoken
		User user = jwtUtil.parseToken(refreshToken);
		if (expireDate.getTime() - currentTimeMillis <= SHIRO_REFRESH_TOKEN_TIME_INTERVAL * 1000) {
			String refreshtokenTmp = jwtUtil.createToken(user, TokenTypeEnum.REFRESHTOKEN);
			redisUtil.set(ShiroStarterConstant.CACHE_KEY_SHIRO_REFRESHTOKEN + idStr, refreshtokenTmp);
		}
		// 重新生成accesstoken
		String accesstokenTmp = jwtUtil.createToken(user, TokenTypeEnum.ACCESSTOKEN);
		redisUtil.set(ShiroStarterConstant.CACHE_KEY_SHIRO_ACCESSTOKEN + idStr, accesstokenTmp);
		// 提交给UserRealm进行认证，如果错误他会抛出异常并被捕获，如果没有抛出异常则代表登入成功，返回true
		JWTToken jwtToken = new JWTToken(accesstokenTmp);
		this.getSubject(request, response).login(jwtToken);
		// 最后将刷新的AccessToken存放在Response的Header中的Authorization字段返回
		HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
		httpServletResponse.setHeader(ShiroStarterConstant.CACHE_KEY_SHIRO_ACCESSTOKEN, accesstokenTmp);
		httpServletResponse.setHeader("Access-Control-Expose-Headers", ShiroStarterConstant.CACHE_KEY_SHIRO_ACCESSTOKEN);
		return ResponseResult.success();
	}


	/**
	 * 进行AccessToken登录认证授权
	 */
	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		// 获取当前Header中Authorization的AccessToken(Shiro中getAuthzHeader方法已经实现)
		JWTToken jwtToken = new JWTToken(this.getAuthzHeader(request));
		// 提交给UserRealm进行认证，如果错误他会抛出异常并被捕获
		this.getSubject(request, response).login(jwtToken);
		// 如果没有抛出异常则代表登入成功，返回true
		return true;
	}

	/**
	 * 检测Header里面是否包含Authorization字段，有则进行Token登录认证授权
	 */
	@Override
	protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
		// 需要是BASIC开头的token
		/*return super.isLoginAttempt(request, response);*/
		// 拿到当前Header中Authorization的AccessToken(Shiro中getAuthzHeader方法已经实现)
		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		String accessToken = httpRequest.getHeader(CoreContextConstant.ACCESSTOKEN);
		String refreshToken = httpRequest.getHeader(CoreContextConstant.REFRESHTOKEN);
		if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(refreshToken)) {
			return false;
		}
		return true;
	}

	/**
	 * 重新设置response响应信息
	 */
	private void resetResponse(ServletResponse response, ResponseResult responseResult) {
		HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
		httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		httpServletResponse.setCharacterEncoding("UTF-8");
		httpServletResponse.setContentType("application/json; charset=utf-8");
		try {
			PrintWriter out = httpServletResponse.getWriter();
			String resp = JSON.toJSONString(responseResult);
			out.append(resp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
