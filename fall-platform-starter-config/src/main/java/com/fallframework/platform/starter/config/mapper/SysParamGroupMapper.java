package com.fallframework.platform.starter.config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.config.entity.SysParamGroup;
import com.fallframework.platform.starter.config.model.SysParamGroupResponse;
import com.fallframework.platform.starter.core.entity.request.BasePageRequest;

import java.util.List;

public interface SysParamGroupMapper extends BaseMapper<SysParamGroup> {

	SysParamGroupResponse findByCode(String code);

	List<SysParamGroupResponse> groupList(Page<SysParamGroupResponse> page, BasePageRequest pageRequest);

	List<SysParamGroupResponse> findAllSysParamGroup();
}