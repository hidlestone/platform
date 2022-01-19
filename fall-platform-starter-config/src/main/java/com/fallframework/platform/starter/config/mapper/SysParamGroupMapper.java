package com.fallframework.platform.starter.config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.api.request.BasePageRequest;
import com.fallframework.platform.starter.config.entity.SysParamGroup;
import com.fallframework.platform.starter.config.model.SysParamGroupRequest;
import com.fallframework.platform.starter.config.model.SysParamGroupResponse;

import java.util.List;

public interface SysParamGroupMapper extends BaseMapper<SysParamGroup> {
	
	List<SysParamGroupResponse> findAllSysParamGroup();

	Page<SysParamGroupResponse> list(Page<SysParamGroupResponse> page, SysParamGroupRequest request);
}