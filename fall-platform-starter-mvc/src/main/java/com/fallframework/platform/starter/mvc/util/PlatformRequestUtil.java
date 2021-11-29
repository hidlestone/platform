package com.fallframework.platform.starter.mvc.util;

import com.fallframework.platform.starter.config.service.PlatformSysParamUtil;
import com.fallframework.platform.starter.core.constant.CoreContextConstant;
import com.fallframework.platform.starter.core.util.EncryptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author zhuangpf
 */
public class PlatformRequestUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlatformRequestUtil.class);

	@Autowired
	private static PlatformSysParamUtil platformSysParamUtil;

	// 内部请求超时时间(ms)
	private static final int INTERNAL_ACCESS_DEVIATION;
	// 内部请求加密盐
	private static final String INTERNAL_CALL_SIGN_SALT;

	static {
		Map<String, String> sysItemMap = platformSysParamUtil.getSysParamGroupItemMap("SECURITY").getData();
		LOGGER.info("SYS PAPRAM SECURITY : " + sysItemMap.toString());
		INTERNAL_ACCESS_DEVIATION = Integer.valueOf(sysItemMap.get("INTERNAL_ACCESS_DEVIATION"));
		INTERNAL_CALL_SIGN_SALT = sysItemMap.get("INTERNAL_CALL_SIGN_SALT");
	}

	/**
	 * 判断是否是内部请求
	 */
	public static boolean isInternalCall(HttpServletRequest request) {
		// 请求携带内部请求签名是 sign
		String sign = request.getHeader(CoreContextConstant.INTERNAL_CALL_SIGN);
		if (StringUtils.isEmpty(sign)) {
			return false;
		}
		String time = request.getHeader(CoreContextConstant.INTERNAL_CALL_TIME);
		try {
			long currentTime = System.currentTimeMillis();
			long requestTime = Long.valueOf(time);
			// 内部请求超时时间
			if (currentTime - requestTime > INTERNAL_ACCESS_DEVIATION) {
				LOGGER.warn("Request devition more than " + INTERNAL_ACCESS_DEVIATION + "millisecond.");
				return false;
			}
		} catch (NumberFormatException e) {
			LOGGER.warn("Header internal-call-time is reqired for internal calls.");
			return false;
		}
		// 根据请求时间生成签名
		String generatedSign = EncryptionUtil.encrptSHA256(INTERNAL_CALL_SIGN_SALT + "@" + time);
		// 校验前端签名
		if (sign.equals(generatedSign)) {
			return true;
		} else {
			LOGGER.warn("internal call sign validation failed.");
			return false;
		}
	}

}
