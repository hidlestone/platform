package com.fallframework.platform.starter.config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.config.entity.SysParamGroup;

import java.util.List;

public interface SysParamGroupMapper extends BaseMapper<SysParamGroup> {

	List<SysParamGroup> getAllSysParamGroup();

	Page<SysParamGroup> list(Page<SysParamGroup> page, SysParamGroup sysParamGroup);
}