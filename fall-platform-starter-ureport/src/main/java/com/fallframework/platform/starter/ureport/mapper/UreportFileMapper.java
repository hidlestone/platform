package com.fallframework.platform.starter.ureport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fallframework.platform.starter.ureport.entity.UreportFile;

import java.util.List;

public interface UreportFileMapper extends BaseMapper<UreportFile> {

	List<UreportFile> getUreportFileInfo();

}