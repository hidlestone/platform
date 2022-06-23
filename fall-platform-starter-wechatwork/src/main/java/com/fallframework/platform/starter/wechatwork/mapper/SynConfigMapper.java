package com.fallframework.platform.starter.wechatwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.wechatwork.entity.SynConfig;

public interface SynConfigMapper extends BaseMapper<SynConfig> {

	Page<SynConfig> list(Page<SynConfig> page, SynConfig synConfig);
	
}