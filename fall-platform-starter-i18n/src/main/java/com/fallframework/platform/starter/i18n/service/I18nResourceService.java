package com.fallframework.platform.starter.i18n.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.i18n.entity.I18nResource;
import com.fallframework.platform.starter.i18n.model.I18nResourceRequest;

import java.util.List;

public interface I18nResourceService extends IService<I18nResource> {

	ResponseResult insert(I18nResource i18nResource);

	ResponseResult delete(Long id);

	ResponseResult update(I18nResource i18nResource);

	ResponseResult<I18nResource> select(Long id);

	ResponseResult<Page<I18nResource>> list(I18nResourceRequest request);

	ResponseResult<List<I18nResource>> selectByResourceKey(String resourceKey);

	ResponseResult refreshI18nResourceCache();
}