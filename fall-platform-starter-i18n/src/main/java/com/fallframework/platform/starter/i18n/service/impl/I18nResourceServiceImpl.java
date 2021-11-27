package com.fallframework.platform.starter.i18n.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.core.entity.response.ResponseResult;
import com.fallframework.platform.starter.i18n.entity.I18nResource;
import com.fallframework.platform.starter.i18n.mapper.I18nResourceMapper;
import com.fallframework.platform.starter.i18n.model.I18nResourceRequest;
import com.fallframework.platform.starter.i18n.service.I18nResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class I18nResourceServiceImpl extends ServiceImpl<I18nResourceMapper, I18nResource> implements I18nResourceService {

	@Autowired
	private I18nResourceMapper i18nResourceMapper;

	@Override
	public ResponseResult insert(I18nResource i18nResource) {
		int i = i18nResourceMapper.insert(i18nResource);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult delete(Long id) {
		int i = i18nResourceMapper.deleteById(id);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult update(I18nResource i18nResource) {
		int i = i18nResourceMapper.updateById(i18nResource);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult<List<I18nResource>> findByResourceKey(String resourceKey) {
		QueryWrapper<I18nResource> wrapper = new QueryWrapper<>();
		wrapper.eq("resource_key", resourceKey);
		List<I18nResource> i18nResourceList = i18nResourceMapper.selectList(wrapper);
		return ResponseResult.success(i18nResourceList);
	}

	@Override
	public ResponseResult<Page<I18nResource>> resourceKeyList(I18nResourceRequest request) {
		Page<I18nResource> page = new Page<>(request.getPageNum(), request.getPageSize());
		QueryWrapper<I18nResource> wrapper = new QueryWrapper<>();
		wrapper.eq("lang_code", request.getLangCode());
		Page<I18nResource> resultPage = i18nResourceMapper.selectPage(page, wrapper);
		return ResponseResult.success(resultPage);
	}

}
