package com.fallframework.platform.starter.config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.config.entity.Dict;

public interface DictMapper extends BaseMapper<Dict> {

	Page<Dict> list(Page<Dict> page, Dict dict);
	
}