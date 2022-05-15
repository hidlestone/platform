package com.fallframework.platform.starter.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.file.entity.FileInfo;

public interface FileInfoMapper extends BaseMapper<FileInfo> {

	Page<FileInfo> list(Page<FileInfo> page, FileInfo fileInfo);

}