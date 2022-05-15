package com.fallframework.platform.starter.config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.config.entity.SysParamItem;

public interface SysParamItemMapper extends BaseMapper<SysParamItem> {

	Page<SysParamItem> list(Page<SysParamItem> page, SysParamItem sysParamItem);
	
}