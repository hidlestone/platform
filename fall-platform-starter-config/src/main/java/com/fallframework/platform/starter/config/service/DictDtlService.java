package com.fallframework.platform.starter.config.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.data.mp.model.Leaf;
import com.fallframework.platform.starter.config.entity.DictDtl;

import java.util.List;

public interface DictDtlService extends IService<DictDtl> {

	/**
	 * 根据字典编码查询翻译后的明细
	 *
	 * @param dictCode 字典编码
	 * @return 字典明细
	 */
	List<DictDtl> getDictDtlsByDictCode(String dictCode);

	/**
	 * 分页查询数据字典明细
	 *
	 * @param dictDtl 查询条件
	 * @return 分页结果
	 */
	Leaf<DictDtl> list(DictDtl dictDtl);

}
