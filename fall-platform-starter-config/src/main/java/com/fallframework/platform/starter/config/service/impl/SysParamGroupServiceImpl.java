package com.fallframework.platform.starter.config.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.config.entity.SysParamGroup;
import com.fallframework.platform.starter.config.entity.SysParamItem;
import com.fallframework.platform.starter.config.mapper.SysParamGroupMapper;
import com.fallframework.platform.starter.config.mapper.SysParamItemMapper;
import com.fallframework.platform.starter.config.model.SysParamGroupRequest;
import com.fallframework.platform.starter.config.model.SysParamGroupResponse;
import com.fallframework.platform.starter.config.model.SysParamItemResponse;
import com.fallframework.platform.starter.config.service.SysParamGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统参数组服务
 *
 * @author zhuangpf
 */
@Service
public class SysParamGroupServiceImpl extends ServiceImpl<SysParamGroupMapper, SysParamGroup> implements SysParamGroupService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SysParamGroupServiceImpl.class);

	@Autowired
	private SysParamGroupMapper sysParamGroupMapper;
	@Autowired
	private SysParamItemMapper sysParamItemMapper;

	@Override
	public ResponseResult<SysParamGroupResponse> select(String code) {
		SysParamGroup sysParamGroup = sysParamGroupMapper.selectById(code);
		if (null == sysParamGroup) {
			return ResponseResult.success();
		}
		SysParamGroupResponse response = new SysParamGroupResponse();
		BeanUtils.copyProperties(sysParamGroup, response);
		// 明细
		QueryWrapper<SysParamItem> wrapper = new QueryWrapper<>();
		wrapper.eq("group_code", sysParamGroup.getCode());
		List<SysParamItem> sysParamItemList = sysParamItemMapper.selectList(wrapper);
		List<SysParamItemResponse> sysParamItemResponseList = JSON.parseArray(JSON.toJSONString(sysParamItemList), SysParamItemResponse.class);
		response.setSysParamItemList(sysParamItemResponseList);
		return ResponseResult.success(response);
	}

	@Override
	public ResponseResult<Page<SysParamGroupResponse>> list(SysParamGroupRequest request) {
		Page<SysParamGroupResponse> page = new Page<>(request.getPageNum(), request.getPageSize());
		page = sysParamGroupMapper.list(page, request);
		return ResponseResult.success(page);
	}

}
