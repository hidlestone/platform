package com.fallframework.platform.starter.config.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.config.entity.DictDtl;

public interface DictDtlService extends IService<DictDtl> {

	ResponseResult<Page<DictDtl>> list(DictDtl dictDtl);
	
}
