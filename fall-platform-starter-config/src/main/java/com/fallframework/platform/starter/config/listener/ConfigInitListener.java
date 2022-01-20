package com.fallframework.platform.starter.config.listener;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.config.service.PlatformSysParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 系统参数初始化监听器
 *
 * @author zhuangpf
 */
@Component
public class ConfigInitListener implements ApplicationListener<ApplicationStartedEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigInitListener.class);

	@Autowired
	private PlatformSysParamUtil platformSysParamUtil;

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		ResponseResult responseResult = platformSysParamUtil.refreshSysParamCache();
		LOGGER.info(responseResult.toString());
		LOGGER.info("init system config to cache.");
	}
}
