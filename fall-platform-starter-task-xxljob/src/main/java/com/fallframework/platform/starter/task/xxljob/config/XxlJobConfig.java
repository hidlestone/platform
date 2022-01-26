package com.fallframework.platform.starter.task.xxljob.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * xxl-job 自动装配
 */
@Configuration
@EnableConfigurationProperties(XxlJobProps.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class XxlJobConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(XxlJobConfig.class);

	private final XxlJobProps xxlJobProps;

	@Bean
	public XxlJobSpringExecutor xxlJobExecutor() {
		LOGGER.info(">>>>> xxl-job config init.");
		XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
		xxlJobSpringExecutor.setAdminAddresses(xxlJobProps.getAdmin().getAddress());
		xxlJobSpringExecutor.setAccessToken(xxlJobProps.getAccessToken());
		xxlJobSpringExecutor.setAppname(xxlJobProps.getExecutor().getAppName());
		xxlJobSpringExecutor.setIp(xxlJobProps.getExecutor().getIp());
		xxlJobSpringExecutor.setPort(xxlJobProps.getExecutor().getPort());
		xxlJobSpringExecutor.setLogPath(xxlJobProps.getExecutor().getLogPath());
		xxlJobSpringExecutor.setLogRetentionDays(xxlJobProps.getExecutor().getLogRetentionDays());
		return xxlJobSpringExecutor;
	}

}
