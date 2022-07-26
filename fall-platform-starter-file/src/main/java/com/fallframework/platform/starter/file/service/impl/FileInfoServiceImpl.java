package com.fallframework.platform.starter.file.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.data.mp.model.Leaf;
import com.fallframework.platform.starter.data.mp.util.LeafPageUtil;
import com.fallframework.platform.starter.file.entity.FileInfo;
import com.fallframework.platform.starter.file.mapper.FileInfoMapper;
import com.fallframework.platform.starter.file.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {

	@Autowired
	private FileInfoMapper fileInfoMapper;

	@Override
	public Leaf<FileInfo> list(FileInfo fileInfo) {
		Page<FileInfo> page = new Page<>(fileInfo.getPageNum(), fileInfo.getPageSize());
		page = fileInfoMapper.list(page, fileInfo);
		return LeafPageUtil.pageToLeaf(page);
	}

}
