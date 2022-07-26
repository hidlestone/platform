package com.fallframework.platform.starter.config.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.data.mp.model.Leaf;
import com.fallframework.platform.starter.config.entity.SysParamItem;
import com.fallframework.platform.starter.config.mapper.SysParamItemMapper;
import com.fallframework.platform.starter.config.service.SysParamItemService;
import com.fallframework.platform.starter.data.mp.util.LeafPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysParamItemServiceImpl extends ServiceImpl<SysParamItemMapper, SysParamItem> implements SysParamItemService {

	@Autowired
	private SysParamItemMapper sysParamItemMapper;

	@Override
	public SysParamItem get(String code) {
		QueryWrapper<SysParamItem> wrapper = new QueryWrapper<>();
		wrapper.eq("code", code);
		SysParamItem sysParamItem = sysParamItemMapper.selectOne(wrapper);
		return sysParamItem;
	}

	@Override
	public Leaf<SysParamItem> list(SysParamItem sysParamItem) {
		Page<SysParamItem> page = new Page<>(sysParamItem.getPageNum(), sysParamItem.getPageSize());
		page = sysParamItemMapper.list(page, sysParamItem);
		return LeafPageUtil.pageToLeaf(page);
	}

}
