package com.fallframework.platform.starter.httpclient.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class DownloadDto implements Serializable {

	/**
	 * 下载源URL
	 */
	private String sourceUrl;

	/**
	 * 请求方法
	 */
	private HttpMethodEnum httpMethod;

	/**
	 * 下载到的目标路径
	 */
	private String destPath;

	/**
	 * 请求头
	 */
	private Map<String, String> headers;

	/**
	 * 参数
	 */
	private Map<String, Object> params;

}
