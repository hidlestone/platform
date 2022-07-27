package com.fallframework.platform.starter.i18n.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.i18n.entity.I18nResource;

import java.util.List;

public interface I18nResourceService extends IService<I18nResource> {

	Leaf<I18nResource> list(I18nResource i18nResource);

	List<I18nResource> getByResourceKey(String resourceKey);

	ResponseResult refreshI18nResourceCache();
}
