package com.fallframework.platform.starter.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.data.mp.model.Leaf;
import com.fallframework.platform.starter.file.entity.FileGroup;

public interface FileGroupService extends IService<FileGroup> {

	Leaf<FileGroup> list(FileGroup fileGroup);

	Long saveGroupAndInfoList(FileGroup fileGroup);

}
