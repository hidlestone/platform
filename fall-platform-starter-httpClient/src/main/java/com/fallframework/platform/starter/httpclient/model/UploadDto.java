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
public class UploadDto implements Serializable {

	private static final long serialVersionUID = 8929146063605351340L;

	/**
	 * 上传请求的URL
	 */
	private String url;

	/**
	 * 请求头
	 */
	private Map<String, String> headers;

	/**
	 * 参数
	 */
	private Map<String, String> params;

	/**
	 * 上传的文件的文件名
	 */
	private String fileName;

	/**
	 * 上传文件的路径
	 */
	private String path;

}
