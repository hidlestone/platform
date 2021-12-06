package com.fallframework.platform.starter.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.file.entity.FileInfo;
import com.fallframework.platform.starter.file.mapper.FileInfoMapper;
import com.fallframework.platform.starter.file.service.FileInfoService;
import org.springframework.stereotype.Service;

@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {

}
