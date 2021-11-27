package com.fallframework.platform.starter.config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fallframework.platform.starter.config.entity.SysParamItem;
import com.fallframework.platform.starter.config.model.SysParamItemResponse;

import java.util.List;

public interface SysParamItemMapper extends BaseMapper<SysParamItem> {

	List<SysParamItemResponse> findItemsByGroupCode(String groupCode);

	List<SysParamItemResponse> findItemsByGroupCodeList(List<String> groupCodeList);
}