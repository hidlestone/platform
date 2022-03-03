package com.fallframework.platform.starter.guard.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 请求切面<br>
 * 已弃用，不在此做处理。
 *
 * @author zhuangpf
 */
//@Aspect
//@Component
@Deprecated
public class ReqeustAspect {

	private Logger LOGGER = LoggerFactory.getLogger(ReqeustAspect.class);

	/**
	 * 配置切入点表达式
	 */
//	@Pointcut("execution(public * com.fallframework.platform.starter.mvc.controller.*.*(..))")
	public void pointCut_() {
	}

	/**
	 * 前置处理
	 */
	@Before("pointCut_()")
	public void doBefore(JoinPoint joinPoint) {
		// 获取request，记录请求内容
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		LOGGER.info("URL : " + request.getRequestURL().toString());
		LOGGER.info("HTTP_METHOD : " + request.getMethod());
		LOGGER.info("IP : " + request.getRemoteAddr());
		LOGGER.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		LOGGER.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
	}

}
