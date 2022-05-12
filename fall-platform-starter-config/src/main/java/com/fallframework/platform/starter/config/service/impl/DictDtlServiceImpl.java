package com.fallframework.platform.starter.config.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.config.entity.DictDtl;
import com.fallframework.platform.starter.config.mapper.DictDtlMapper;
import com.fallframework.platform.starter.config.service.DictDtlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictDtlServiceImpl extends ServiceImpl<DictDtlMapper, DictDtl> implements DictDtlService {

	@Autowired
	private DictDtlMapper dictDtlMapper;

	@Override
	public ResponseResult<Page<DictDtl>> list(DictDtl dictDtl) {
		Page<DictDtl> page = new Page<>(dictDtl.getPageNum(), dictDtl.getPageSize());
		page = dictDtlMapper.list(page, dictDtl);
		return ResponseResult.success(page);
	}

}
