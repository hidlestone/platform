package com.fallframework.platform.starter.file.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.file.entity.FileGroup;
import com.fallframework.platform.starter.file.model.FileGroupRequest;

public interface FileGroupService extends IService<FileGroup> {

	ResponseResult<Page<FileGroup>> list(FileGroupRequest request);

	ResponseResult saveGroupAndInfoList(FileGroupRequest request);
	
}
