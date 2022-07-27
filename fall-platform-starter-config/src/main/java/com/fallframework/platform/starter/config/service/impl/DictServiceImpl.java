package com.fallframework.platform.starter.config.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.config.entity.Dict;
import com.fallframework.platform.starter.config.mapper.DictMapper;
import com.fallframework.platform.starter.config.service.DictDtlService;
import com.fallframework.platform.starter.config.service.DictService;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.data.mp.util.LeafPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

	@Autowired
	private DictMapper dictMapper;
	@Autowired
	private DictDtlService dictDtlService;

	@Override
	public ResponseResult saveDict(Dict dict) {
		// 保存字典项
		save(dict);
		dict.getDictDtls().forEach(e -> e.setDictId(dict.getId()));
		// 保存字典明细
		dictDtlService.saveBatch(dict.getDictDtls());
		return ResponseResult.success();
	}

	@Override
	public Leaf<Dict> list(Dict dict) {
		Page<Dict> page = new Page<>(dict.getPageNum(), dict.getPageSize());
		page = dictMapper.list(page, dict);
		return LeafPageUtil.pageToLeaf(page);
	}

	@Override
	public List<Dict> getAllDicts() {
		List<Dict> dicts = dictMapper.selectList(null);
		return dicts;
	}

}
