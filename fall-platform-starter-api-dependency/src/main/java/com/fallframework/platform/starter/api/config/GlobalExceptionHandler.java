package com.fallframework.platform.starter.api.config;

import com.fallframework.platform.starter.api.response.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 *
 * @author zhuangpf
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseResult defaultErrorHandler(Exception e) {
		return ResponseResult.fail(e.getMessage());
	}

}
