package com.fallframework.platform.starter.i18n.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.i18n.constant.I18nStarterConstant;
import com.fallframework.platform.starter.i18n.entity.I18nResource;
import com.fallframework.platform.starter.i18n.mapper.I18nResourceMapper;
import com.fallframework.platform.starter.i18n.model.I18nResourceRequest;
import com.fallframework.platform.starter.i18n.service.I18nResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class I18nResourceServiceImpl extends ServiceImpl<I18nResourceMapper, I18nResource> implements I18nResourceService {

	@Autowired
	private I18nResourceMapper i18nResourceMapper;
	@Autowired
	private RedisUtil redisUtil;

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
	public ResponseResult<I18nResource> select(Long id) {
		I18nResource i18nResource = i18nResourceMapper.selectById(id);
		return ResponseResult.success(i18nResource);
	}

	@Override
	public ResponseResult<Page<I18nResource>> list(I18nResourceRequest request) {
		Page<I18nResource> page = new Page<>(request.getPageNum(), request.getPageSize());
		page = i18nResourceMapper.list(page, request);
		return ResponseResult.success(page);
	}

	@Override
	public ResponseResult<List<I18nResource>> selectByResourceKey(String resourceKey) {
		QueryWrapper<I18nResource> wrapper = new QueryWrapper<>();
		wrapper.eq("resource_key", resourceKey);
		List<I18nResource> i18nResourceList = i18nResourceMapper.selectList(wrapper);
		return ResponseResult.success(i18nResourceList);
	}

	/**
	 * 刷新多语言词条缓存
	 */
	@Override
	public ResponseResult refreshI18nResourceCache() {
		// 所有
		List<I18nResource> i18nResourceList = i18nResourceMapper.selectList(null);
		if (CollectionUtil.isNotEmpty(i18nResourceList)) {
			// 按照语言编码分组
			Map<String, List<I18nResource>> langCodeResMap = i18nResourceList.stream().collect(Collectors.groupingBy(it -> it.getLangCode()));
			if (CollectionUtil.isNotEmpty(langCodeResMap)) {
				for (Map.Entry<String, List<I18nResource>> entry : langCodeResMap.entrySet()) {
					// 删除语言词条缓存：i18n:resource:en
					redisUtil.del(I18nStarterConstant.I18N_CACHE_KEY + entry.getKey());
					for (I18nResource i18nResource : entry.getValue()) {
						redisUtil.hset(I18nStarterConstant.I18N_CACHE_KEY + entry.getKey(), i18nResource.getResourceKey(), i18nResource.getResourceValue());
					}
				}
			}
		}
		return ResponseResult.success();
	}

}