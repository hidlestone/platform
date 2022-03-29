package com.fallframework.platform.starter.rbac.util;

import com.fallframework.platform.starter.rbac.constant.RbacStarterConstant;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhuangpf
 */
public class RequestContexUtil {

	/**
	 * 在返回的请求头中设置token信息
	 *
	 * @param accesstoken  访问token
	 * @param refreshtoken 刷新token
	 */
	public static void setTokenHeader(String accesstoken, String refreshtoken) {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		response.setHeader(RbacStarterConstant.ACCESSTOKEN, accesstoken);
		response.setHeader(RbacStarterConstant.REFRESHTOKEN, refreshtoken);
	}

}
