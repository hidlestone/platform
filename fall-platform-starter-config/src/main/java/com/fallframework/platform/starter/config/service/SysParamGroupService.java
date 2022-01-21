package com.fallframework.platform.starter.config.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.config.entity.SysParamGroup;
import com.fallframework.platform.starter.config.model.SysParamGroupRequest;
import com.fallframework.platform.starter.config.model.SysParamGroupResponse;

/**
 * @author zhuangpf
 */
public interface SysParamGroupService extends IService<SysParamGroup> {

	ResponseResult insert(SysParamGroup sysParamGroup);

	ResponseResult delete(String code);

	ResponseResult update(SysParamGroup sysParamGroup);

	ResponseResult<SysParamGroupResponse> select(String code);

	ResponseResult<Page<SysParamGroupResponse>> list(SysParamGroupRequest request);

}