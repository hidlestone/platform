package com.fallframework.platform.starter.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.data.mp.model.Leaf;
import com.fallframework.platform.starter.file.entity.FileInfo;

public interface FileInfoService extends IService<FileInfo> {

	Leaf<FileInfo> list(FileInfo fileInfo);

}
