package com.fallframework.platform.starter.security.handle;

import com.alibaba.fastjson.JSON;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未授权统一处理类<br/>
 * 是Spring Security Web一个概念模型接口，顾名思义，他所建模的概念是:“认证入口点”。
 * 它在用户请求处理过程中遇到认证异常时，被ExceptionTranslationFilter用于开启特定认证方案(authentication schema)的认证流程。
 *
 * @author zhuangpf
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationEntryPointImpl.class);

	/**
	 * 如果该资源url未授权，则返回提示：authentication failed, can not access system resource.
	 *
	 * @param request  请求
	 * @param response 响应
	 * @param e        认证异常
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
		int code = HttpStatus.UNAUTHORIZED.value();
		String msg = request.getRequestURI() + ", authentication failed, can not access system resource.";
		LOGGER.error(msg);
		ResponseResult responseResult = ResponseResult.fail(String.valueOf(code), msg);
		response.getWriter().write(JSON.toJSONString(responseResult));
	}

}
