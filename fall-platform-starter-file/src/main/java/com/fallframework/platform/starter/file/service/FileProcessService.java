package com.fallframework.platform.starter.file.service;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.file.model.FileGroupUploadDto;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhuangpf
 */
public interface FileProcessService {

	/**
	 * 上传文件
	 *
	 * @param dto 文件上传请求参数
	 * @return
	 */
	ResponseResult<Long> uploadFileGroup(FileGroupUploadDto dto);

	/**
	 * 下载文件
	 *
	 * @param fileInfoId 文件ID
	 * @param response   response
	 * @return
	 */
	ResponseResult downloadFile(Long fileInfoId, HttpServletResponse response);

}
