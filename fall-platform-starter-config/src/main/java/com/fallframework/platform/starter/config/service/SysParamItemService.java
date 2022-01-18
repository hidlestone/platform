package com.fallframework.platform.starter.config.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.config.entity.SysParamItem;

import java.util.List;

/**
 * 系统参数明细项
 *
 * @author zhuangpf
 */
public interface SysParamItemService extends IService<SysParamItem> {

	ResponseResult insert(SysParamItem sysParamItem);

	ResponseResult delete(String code);

	ResponseResult update(SysParamItem sysParamItem);

	ResponseResult<SysParamItem> select(String code);

	ResponseResult<List<SysParamItem>> selectByGroupCode(String groupCode);

}
