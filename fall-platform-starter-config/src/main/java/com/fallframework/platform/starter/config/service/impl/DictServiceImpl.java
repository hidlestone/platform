package com.fallframework.platform.starter.config.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.config.entity.Dict;
import com.fallframework.platform.starter.config.entity.DictDtl;
import com.fallframework.platform.starter.config.mapper.DictMapper;
import com.fallframework.platform.starter.config.service.DictDtlService;
import com.fallframework.platform.starter.config.service.DictService;
import com.fallframework.platform.starter.i18n.entity.I18nResource;
import com.fallframework.platform.starter.i18n.service.I18nResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

	@Autowired
	private DictMapper dictMapper;
	@Autowired
	private DictDtlService dictDtlService;
	@Autowired
	private I18nResourceService i18nResourceService;

	@Override
	public ResponseResult saveDict(Dict dict) {
		// 字典项
		save(dict);
		dict.getDictDtls().forEach(e -> e.setDictId(dict.getId()));
		dictDtlService.saveBatch(dict.getDictDtls());
		// 字典项词条
		List<I18nResource> dictI18nResourceList = dict.getI18nResources();
		// 字典明细词条
		for (DictDtl dictDtl : dict.getDictDtls()) {
			if (CollectionUtil.isNotEmpty(dictDtl.getI18nResources())) {
				dictI18nResourceList.addAll(dictDtl.getI18nResources());
			}
		}
		i18nResourceService.saveBatch(dictI18nResourceList);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult<Page<Dict>> list(Dict dict) {
		Page<Dict> page = new Page<>(dict.getPageNum(), dict.getPageSize());
		page = dictMapper.list(page, dict);
		return ResponseResult.success(page);
	}

}
