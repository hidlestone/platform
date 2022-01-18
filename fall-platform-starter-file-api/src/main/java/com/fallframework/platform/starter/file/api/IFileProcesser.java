package com.fallframework.platform.starter.file.api;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.file.entity.FileInfo;

import java.util.List;

/**
 * 文件处理接口
 *
 * @author zhuangpf
 */
public interface IFileProcesser {

	/**
	 * 单文件上传
	 */
	ResponseResult uploadFile(FileInfo fileInfo);

	/**
	 * 多个文件上传
	 */
	ResponseResult uploadFileList(List<FileInfo> fileInfoList);

	/**
	 * 单文件下载
	 */
	ResponseResult downloadFile(FileInfo fileInfo);

	/**
	 * 多个文件下载
	 */
	ResponseResult downloadFileList(List<FileInfo> fileInfoList);
	
}
