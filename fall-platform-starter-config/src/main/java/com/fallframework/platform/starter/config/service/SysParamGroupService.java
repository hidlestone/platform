package com.fallframework.platform.starter.config.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.config.entity.SysParamGroup;

/**
 * @author zhuangpf
 */
public interface SysParamGroupService extends IService<SysParamGroup> {

	ResponseResult<SysParamGroup> get(String code);

	ResponseResult<Page<SysParamGroup>> list(SysParamGroup sysParamGroup);

}
