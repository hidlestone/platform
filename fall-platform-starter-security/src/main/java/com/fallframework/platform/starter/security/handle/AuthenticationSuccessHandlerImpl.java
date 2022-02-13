package com.fallframework.platform.starter.security.handle;

import com.alibaba.fastjson.JSON;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.security.constant.SecurityStarterConstant;
import com.fallframework.platform.starter.security.entity.User;
import com.fallframework.platform.starter.security.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhuangpf
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	@Autowired
	private RedisUtil redisUtil;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		ResponseResult responseResult = ResponseResult.success("login success");
		// 认证成功之后，得到认证成功后的用户信息
		User user = (User) authentication.getPrincipal();
//		String token = (String) redisUtil.hget(SecurityStarterConstant.CACHE_KEY_USER_TOKEN, String.valueOf(user.getId()));
		// 重新生成token
		String token = JWTUtil.createToken(user);
		// 存入缓存
		redisUtil.hset(SecurityStarterConstant.CACHE_KEY_USER_TOKEN, String.valueOf(user.getId()), token);
		responseResult.setData(token);
		httpServletResponse.getWriter().write(JSON.toJSONString(responseResult));
	}

}
