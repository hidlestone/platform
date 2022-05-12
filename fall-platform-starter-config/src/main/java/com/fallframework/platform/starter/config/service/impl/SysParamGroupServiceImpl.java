package com.fallframework.platform.starter.config.service.impl;

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
import com.fallframework.platform.starter.config.service.SysParamGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	public ResponseResult<SysParamGroup> get(String code) {
		SysParamGroup sysParamGroup = sysParamGroupMapper.selectById(code);
		if (null == sysParamGroup) {
			return ResponseResult.success();
		}
		// 明细
		QueryWrapper<SysParamItem> wrapper = new QueryWrapper<>();
		wrapper.eq("group_code", sysParamGroup.getCode());
		List<SysParamItem> sysParamItems = sysParamItemMapper.selectList(wrapper);
		sysParamGroup.setSysParamItems(sysParamItems);
		return ResponseResult.success(sysParamGroup);
	}

	@Override
	public ResponseResult<Page<SysParamGroup>> list(SysParamGroup request) {
		Page<SysParamGroup> page = new Page<>(request.getPageNum(), request.getPageSize());
		page = sysParamGroupMapper.list(page, request);
		return ResponseResult.success(page);
	}

}
