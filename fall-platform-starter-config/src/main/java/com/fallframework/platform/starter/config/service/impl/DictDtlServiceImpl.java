package com.fallframework.platform.starter.config.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.config.entity.DictDtl;
import com.fallframework.platform.starter.config.mapper.DictDtlMapper;
import com.fallframework.platform.starter.config.service.DictDtlService;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.data.mp.util.LeafPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictDtlServiceImpl extends ServiceImpl<DictDtlMapper, DictDtl> implements DictDtlService {

	@Autowired
	private DictDtlMapper dictDtlMapper;

	@Override
	public List<DictDtl> getDictDtlsByDictCode(String dictCode) {
		List<DictDtl> dictDtls = dictDtlMapper.getDictDtlsByCode(dictCode);
		return dictDtls;
	}

	@Override
	public Leaf<DictDtl> list(DictDtl dictDtl) {
		Page<DictDtl> page = new Page<>(dictDtl.getPageNum(), dictDtl.getPageSize());
		page = dictDtlMapper.list(page, dictDtl);
		return LeafPageUtil.pageToLeaf(page);
	}

}
