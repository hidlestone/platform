package com.fallframework.platform.starter.file.service;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.file.model.FileGroupUploadRequest;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhuangpf
 */
public interface FileProcessService {

	/**
	 * 上传文件
	 *
	 * @param uploadRequest 文件上传请求参数
	 * @return 是否上传成功
	 */
	ResponseResult uploadFileGroup(FileGroupUploadRequest uploadRequest);

	/**
	 * 下载文件
	 *
	 * @param fileInfoId 文件ID
	 * @param response   response
	 * @return 是否下载成功
	 */
	ResponseResult downloadFile(Long fileInfoId, HttpServletResponse response);

}
