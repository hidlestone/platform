package com.fallframework.platform.starter.file.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
	public ResponseResult insert(FileInfo fileInfo) {
		int i = fileInfoMapper.insert(fileInfo);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult delete(Long id) {
		int i = fileInfoMapper.deleteById(id);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult update(FileInfo fileInfo) {
		int i = fileInfoMapper.updateById(fileInfo);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult get(Long id) {
		FileInfo fileInfo = fileInfoMapper.selectById(id);
		return ResponseResult.success(fileInfo);
	}

	@Override
	public ResponseResult<Page<FileInfo>> findListByFileGroupId(FileInfoRequest request) {
		Page<FileInfo> page = new Page<>(request.getPageNum(), request.getPageSize());
		QueryWrapper wrapper = new QueryWrapper();
		wrapper.eq("file_group_id", request.getFileGroupId());
		Page<FileInfo> resultPage = fileInfoMapper.selectPage(page, wrapper);
		return ResponseResult.success(resultPage);
	}
	
}
