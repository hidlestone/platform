package com.fallframework.platform.starter.config.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.config.entity.SysParamItem;
import com.fallframework.platform.starter.config.mapper.SysParamItemMapper;
import com.fallframework.platform.starter.config.service.SysParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuangpf
 */
@Service
public class SysParamItemServiceImpl extends ServiceImpl<SysParamItemMapper, SysParamItem> implements SysParamItemService {

	@Autowired
	private SysParamItemMapper sysParamItemMapper;

	@Override
	public ResponseResult deleteByGroupCode(String groupCode) {
		QueryWrapper<SysParamItem> wrapper = new QueryWrapper<>();
		wrapper.eq("group_code", groupCode);
		sysParamItemMapper.delete(wrapper);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult<List<SysParamItem>> selectByGroupCode(String groupCode) {
		QueryWrapper<SysParamItem> wrapper = new QueryWrapper<>();
		wrapper.eq("group_code", groupCode);
		List<SysParamItem> sysParamItemList = sysParamItemMapper.selectList(wrapper);
		return ResponseResult.success(sysParamItemList);
	}

}
