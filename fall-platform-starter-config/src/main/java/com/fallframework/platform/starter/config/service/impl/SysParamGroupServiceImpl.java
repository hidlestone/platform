package com.fallframework.platform.starter.config.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.config.entity.SysParamGroup;
import com.fallframework.platform.starter.config.entity.SysParamItem;
import com.fallframework.platform.starter.config.mapper.SysParamGroupMapper;
import com.fallframework.platform.starter.config.mapper.SysParamItemMapper;
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
		QueryWrapper<SysParamGroup> wrapper01 = new QueryWrapper<>();
		wrapper01.eq("code", code);
		SysParamGroup sysParamGroup = sysParamGroupMapper.selectOne(wrapper01);
		if (null == sysParamGroup) {
			return ResponseResult.success();
		}
		// 明细
		QueryWrapper<SysParamItem> wrapper02 = new QueryWrapper<>();
		wrapper02.eq("group_code", code);
		List<SysParamItem> sysParamItems = sysParamItemMapper.selectList(wrapper02);
		sysParamGroup.setSysParamItems(sysParamItems);
		return ResponseResult.success(sysParamGroup);
	}

	@Override
	public ResponseResult<Page<SysParamGroup>> list(SysParamGroup sysParamGroup) {
		Page<SysParamGroup> page = new Page<>(sysParamGroup.getPageNum(), sysParamGroup.getPageSize());
		page = sysParamGroupMapper.list(page, sysParamGroup);
		return ResponseResult.success(page);
	}

}
