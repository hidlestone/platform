package com.fallframework.platform.starter.httpclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * httpclient 基础配置
 *
 * @author zhuangpf
 */
@ConfigurationProperties(prefix = "platform.starter.httpClient")
public class HttpClientConfig {

	/**
	 * 编码
	 */
	private String encoding = "UTF-8";

	/**
	 * 接超时时间，单位毫秒。
	 */
	private Integer connectTimeout = 60000;

	/**
	 * 从connect Manager获取Connection 超时时间，单位毫秒。
	 */
	private Integer connectionRequestTimeout = 60000;

	/**
	 * 请求获取数据的超时时间，单位毫秒。
	 * 访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
	 */
	private Integer socketTimeout = 60000;

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public Integer getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public Integer getConnectionRequestTimeout() {
		return connectionRequestTimeout;
	}

	public void setConnectionRequestTimeout(Integer connectionRequestTimeout) {
		this.connectionRequestTimeout = connectionRequestTimeout;
	}

	public Integer getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(Integer socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

}
