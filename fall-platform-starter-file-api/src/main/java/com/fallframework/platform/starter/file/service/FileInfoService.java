package com.fallframework.platform.starter.file.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.file.entity.FileInfo;
import com.fallframework.platform.starter.file.model.FileInfoRequest;

public interface FileInfoService extends IService<FileInfo> {

	ResponseResult insert(FileInfo fileInfo);

	ResponseResult delete(Long id);

	ResponseResult update(FileInfo fileInfo);

	ResponseResult get(Long id);

	ResponseResult<Page<FileInfo>> findListByFileGroupId(FileInfoRequest request);

}
