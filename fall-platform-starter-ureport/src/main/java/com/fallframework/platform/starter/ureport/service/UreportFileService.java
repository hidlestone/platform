package com.fallframework.platform.starter.ureport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.ureport.entity.UreportFile;

import java.util.List;

public interface UreportFileService extends IService<UreportFile> {

	List<UreportFile> getUreportFileInfo();

}
