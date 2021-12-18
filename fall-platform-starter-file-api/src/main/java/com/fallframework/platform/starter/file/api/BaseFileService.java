package com.fallframework.platform.starter.file.api;

/**
 * 文件服务，图片服务接口定义
 */
public interface BaseFileService {

	/**
	 * 获取上传所需的 token
	 */
	String getUploadToken();

	
	
}
