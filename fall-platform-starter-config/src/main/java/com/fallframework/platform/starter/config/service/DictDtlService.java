package com.fallframework.platform.starter.config.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.config.entity.Dict;
import com.fallframework.platform.starter.config.entity.DictDtl;

import java.util.List;

public interface DictDtlService extends IService<DictDtl> {

	/**
	 * 分页查询数据字典明细
	 *
	 * @param dictDtl 查询条件
	 * @return 分页结果
	 */
	ResponseResult<Page<DictDtl>> list(DictDtl dictDtl);

	/**
	 * 根据字典编码查询翻译后的明细
	 *
	 * @param dict 字典编码
	 * @return 字典明细
	 */
	ResponseResult<List<DictDtl>> getDictDtlsByCode(Dict dict);

}