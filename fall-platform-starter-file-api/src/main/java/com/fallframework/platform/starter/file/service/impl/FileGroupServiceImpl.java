package com.fallframework.platform.starter.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.file.entity.FileGroup;
import com.fallframework.platform.starter.file.mapper.FileGroupMapper;
import com.fallframework.platform.starter.file.service.FileGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileGroupServiceImpl extends ServiceImpl<FileGroupMapper, FileGroup> implements FileGroupService {

	@Autowired
	private FileGroupMapper fileGroupMapper;

	@Override
	public ResponseResult insert(FileGroup fileGroup) {
		int i = fileGroupMapper.insert(fileGroup);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult delete(Long id) {
		int i = fileGroupMapper.deleteById(id);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult update(FileGroup fileGroup) {
		int i = fileGroupMapper.updateById(fileGroup);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult<FileGroup> get(Long id) {
		FileGroup fileGroup = fileGroupMapper.selectById(id);
		return ResponseResult.success(fileGroup);
	}
	
}
