package com.fallframework.platform.starter.wxwork.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 企微配置
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "wechat.corp")
public class WxCpProperties {

	/**
	 * 企业微信的corpId
	 */
	private String corpId;

	/**
	 * 通讯录配置
	 */
	private ContactConfig contactConfig;

	/**
	 * 客户联系配置
	 */
	private ExternalContactConfig externalContactConfig;

	/**
	 * 应用配置
	 */
	private List<AppConfig> appConfigs;

	/**
	 * 通讯录配置
	 */
	@Getter
	@Setter
	public static class ContactConfig {

		/**
		 * token
		 */
		private String token;

		/**
		 * 通讯录秘钥
		 */
		private String secret;

		/**
		 * aes密钥
		 */
		private String encodingAESKey;
	}

	/**
	 * 客户联系
	 */
	@Getter
	@Setter
	public static class ExternalContactConfig {

		/**
		 * token
		 */
		private String token;

		/**
		 * 外部联系人秘钥
		 */
		private String secret;

		/**
		 * aes密钥
		 */
		private String encodingAESKey;
	}

	/**
	 * 自建应用的配置
	 */
	@Getter
	@Setter
	public static class AppConfig {

		/**
		 * 设置企业微信应用的AgentId
		 */
		private Integer agentId;

		/**
		 * 设置企业微信应用的Secret
		 */
		private String secret;

		/**
		 * 设置企业微信应用的token
		 */
		private String token;

		/**
		 * 设置企业微信应用的EncodingAESKey
		 */
		private String encodingAESKey;

	}

}
