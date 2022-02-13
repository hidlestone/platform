package com.fallframework.platform.starter.security.handle;

import com.alibaba.fastjson.JSON;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败返回的 JSON 格式数据给前端（否则为 html）
 *
 * @author zhuangpf
 */
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		// 401, login fail
		ResponseResult responseResult = ResponseResult.fail(String.valueOf(HttpStatus.UNAUTHORIZED.value()), "login fail");
		httpServletResponse.getWriter().write(JSON.toJSONString(responseResult));
	}

}
