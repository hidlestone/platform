package com.fallframework.platform.starter.ureport.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.ureport.entity.UreportFile;
import com.fallframework.platform.starter.ureport.mapper.UreportFileMapper;
import com.fallframework.platform.starter.ureport.service.UreportFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UreportFileServiceImpl extends ServiceImpl<UreportFileMapper, UreportFile> implements UreportFileService {

	@Autowired
	private UreportFileMapper ureportFileMapper;

	@Override
	public List<UreportFile> getUreportFileInfo() {
		return ureportFileMapper.getUreportFileInfo();
	}
}
