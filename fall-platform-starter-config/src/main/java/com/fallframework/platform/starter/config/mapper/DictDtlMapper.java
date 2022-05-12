package com.fallframework.platform.starter.config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.config.entity.DictDtl;

import java.util.List;

public interface DictDtlMapper extends BaseMapper<DictDtl> {

	Page<DictDtl> list(Page<DictDtl> page, DictDtl dictDtl);

	List<DictDtl> getDictDtlsByCode(String code);
	
}