package com.fallframework.platform.starter.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.file.entity.FileGroup;

public interface FileGroupMapper extends BaseMapper<FileGroup> {
	
	Page<FileGroup> list(Page<FileGroup> page, FileGroup fileGroup);
	
}