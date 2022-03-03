package com.fallframework.platform.starter.ureport.config;

import com.fallframework.platform.starter.ureport.ftp.FTPClientFactory;
import com.fallframework.platform.starter.ureport.properties.FTPProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhuangpf
 */
public class ProviderConfig {

	private Logger LOGGER = LoggerFactory.getLogger(ProviderConfig.class);

	/**
	 * FTP 配置
	 */
	@Configuration
	@ConditionalOnProperty(prefix = "ftp.factory", name = "enable", havingValue = "true")
	@EnableConfigurationProperties(FTPProperties.class)
	public static class FTPConfig {

		@Bean
		public FTPClientFactory ftpClientFactory() {
			return new FTPClientFactory();
		}


		@Bean
		public FTPClientPool ftpClientPool() {
			return new FTPClientPool(ftpClientFactory());
		}

		@Bean
		public FTPClientUtils ftpClientUtils() {
			return new FTPClientUtils();
		}

		@Bean
		@ConfigurationProperties(prefix = "ureport.ftp.provider")
		public FTPProvider ftpProvider() {
			return new FTPProvider();
		}

	}
}
