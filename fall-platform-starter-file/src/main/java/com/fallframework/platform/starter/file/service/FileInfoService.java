package com.fallframework.platform.starter.file.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.file.entity.FileInfo;

public interface FileInfoService extends IService<FileInfo> {

	ResponseResult<Page<FileInfo>> list(FileInfo fileInfo);

}
