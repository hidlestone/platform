package com.fallframework.platform.starter.security.handle;

import com.alibaba.fastjson.JSON;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.security.constant.SecurityStarterConstant;
import com.fallframework.platform.starter.security.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 成功登出
 *
 * @author zhuangpf
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogoutSuccessHandlerImpl.class);
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		LOGGER.info(String.format("IP : %s, user : %s, at %s logout.",
				request.getRemoteHost(), authentication.getName(), LocalDateTime.now()));
		User user = (User) authentication.getPrincipal();
		// 删除token
		redisUtil.hdel(SecurityStarterConstant.CACHE_KEY_USER_TOKEN, user.getId());
		ResponseResult responseResult = ResponseResult.success("logout success.");
		response.getWriter().write(JSON.toJSONString(responseResult));
	}

}
