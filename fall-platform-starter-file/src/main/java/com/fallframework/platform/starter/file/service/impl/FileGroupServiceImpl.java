package com.fallframework.platform.starter.file.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.file.entity.FileGroup;
import com.fallframework.platform.starter.file.entity.FileInfo;
import com.fallframework.platform.starter.file.mapper.FileGroupMapper;
import com.fallframework.platform.starter.file.service.FileGroupService;
import com.fallframework.platform.starter.file.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileGroupServiceImpl extends ServiceImpl<FileGroupMapper, FileGroup> implements FileGroupService {

	@Autowired
	private FileGroupMapper fileGroupMapper;
	@Autowired
	private FileInfoService fileInfoService;

	@Override
	public ResponseResult<Page<FileGroup>> list(FileGroup fileGroup) {
		Page<FileGroup> page = new Page<>(fileGroup.getPageNum(), fileGroup.getPageSize());
		page = fileGroupMapper.list(page, fileGroup);
		return ResponseResult.success(page);
	}

	@Override
	public ResponseResult saveGroupAndInfoList(FileGroup fileGroup) {
		save(fileGroup);
		List<FileInfo> fileInfoList = fileGroup.getFileInfos();
		fileInfoList.forEach(dtl -> dtl.setFileGroupId(fileGroup.getId()));
		fileInfoService.saveBatch(fileInfoList);
		return ResponseResult.success();
	}

}
