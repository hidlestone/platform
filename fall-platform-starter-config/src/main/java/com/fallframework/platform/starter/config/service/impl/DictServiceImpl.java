package com.fallframework.platform.starter.config.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.config.entity.Dict;
import com.fallframework.platform.starter.config.mapper.DictMapper;
import com.fallframework.platform.starter.config.service.DictDtlService;
import com.fallframework.platform.starter.config.service.DictService;
import com.fallframework.platform.starter.i18n.entity.I18nResource;
import com.fallframework.platform.starter.i18n.service.I18nResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
		// 多语言词条
		I18nResource dictI18nResource = dict.getI18nResource();
		List<I18nResource> i18nResourceList = dict.getDictDtls().stream().map(e -> e.getI18nResource()).collect(Collectors.toList());
		i18nResourceList.add(dictI18nResource);
		i18nResourceService.saveBatch(i18nResourceList);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult list(Dict dict) {
		Page<Dict> page = new Page<>(dict.getPageNum(), dict.getPageSize());
		page = dictMapper.list(page, dict);
		return ResponseResult.success(page);
	}

}
