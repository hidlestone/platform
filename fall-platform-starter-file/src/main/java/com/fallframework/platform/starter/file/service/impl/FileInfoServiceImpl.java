package com.fallframework.platform.starter.file.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.file.entity.FileInfo;
import com.fallframework.platform.starter.file.mapper.FileInfoMapper;
import com.fallframework.platform.starter.file.model.FileInfoRequest;
import com.fallframework.platform.starter.file.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {

	@Autowired
	private FileInfoMapper fileInfoMapper;

	@Override
	public ResponseResult<Page<FileInfo>> list(FileInfoRequest request) {
		Page<FileInfo> page = new Page<>(request.getPageNum(), request.getPageSize());
		page = fileInfoMapper.list(page, request);
		return ResponseResult.success(page);
	}

}
