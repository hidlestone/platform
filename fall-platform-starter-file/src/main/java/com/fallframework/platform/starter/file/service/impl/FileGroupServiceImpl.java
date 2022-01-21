package com.fallframework.platform.starter.file.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.file.entity.FileGroup;
import com.fallframework.platform.starter.file.mapper.FileGroupMapper;
import com.fallframework.platform.starter.file.model.FileGroupRequest;
import com.fallframework.platform.starter.file.service.FileGroupService;
import org.apache.commons.lang3.StringUtils;
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
	public ResponseResult<FileGroup> select(Long id) {
		FileGroup fileGroup = fileGroupMapper.selectById(id);
		return ResponseResult.success(fileGroup);
	}

	@Override
	public ResponseResult<Page<FileGroup>> list(FileGroupRequest request) {
		Page<FileGroup> page = new Page<>(request.getPageNum(), request.getPageSize());
		QueryWrapper<FileGroup> wrapper = new QueryWrapper();
		if (StringUtils.isNotEmpty(request.getDesc())) {
			wrapper.like("`desc`", request.getDesc());
		}
		if (null != request.getStatus()) {
			wrapper.like("`status`", request.getStatus());
		}
		page = fileGroupMapper.selectPage(page, wrapper);
		return ResponseResult.success(page);
	}

}
