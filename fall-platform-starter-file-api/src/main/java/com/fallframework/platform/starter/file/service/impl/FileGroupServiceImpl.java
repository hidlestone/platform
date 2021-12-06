package com.fallframework.platform.starter.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.file.entity.FileGroup;
import com.fallframework.platform.starter.file.mapper.FileGroupMapper;
import com.fallframework.platform.starter.file.service.FileGroupService;
import org.springframework.stereotype.Service;

@Service
public class FileGroupServiceImpl extends ServiceImpl<FileGroupMapper, FileGroup> implements FileGroupService {

}
