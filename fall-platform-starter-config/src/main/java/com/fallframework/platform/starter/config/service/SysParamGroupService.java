package com.fallframework.platform.starter.config.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.config.entity.SysParamGroup;
import com.fallframework.platform.starter.config.model.SysParamGroupRequest;
import com.fallframework.platform.starter.config.model.SysParamGroupResponse;
import com.fallframework.platform.starter.core.entity.request.BasePageRequest;
import com.fallframework.platform.starter.core.entity.response.ResponseResult;

import java.util.List;

/**
 * @author zhuangpf
 */
public interface SysParamGroupService extends IService<SysParamGroup> {

	ResponseResult insert(SysParamGroupRequest request);

	ResponseResult delete(String code);

	ResponseResult update(SysParamGroupRequest request);

	/**
	 * 获取系统参数组及明细项
	 */
	SysParamGroupResponse get(String code);

	/**
	 * 分页查询系统参数组列表
	 */
	List<SysParamGroupResponse> groupList(BasePageRequest pageRequest);

	/**
	 * 分页查询系统参数组及明细项列表
	 */
	List<SysParamGroupResponse> groupItemsList(BasePageRequest pageRequest);

	/**
	 * 更新系统参数缓存
	 */
	ResponseResult refreshSysParamCache();

}
