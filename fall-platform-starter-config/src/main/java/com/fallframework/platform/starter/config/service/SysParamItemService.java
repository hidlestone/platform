package com.fallframework.platform.starter.config.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.config.entity.SysParamItem;

public interface SysParamItemService extends IService<SysParamItem> {

	ResponseResult<SysParamItem> get(String code);

	ResponseResult<Page<SysParamItem>> list(SysParamItem sysParamItem);

}
