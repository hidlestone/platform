package com.fallframework.platform.starter.i18n.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.i18n.entity.I18nResource;
import java.util.List;
import com.fallframework.platform.starter.i18n.model.I18nResourceRequest;
import org.apache.ibatis.annotations.Param;

public interface I18nResourceMapper extends BaseMapper<I18nResource> {

	Page<I18nResource> list(Page<I18nResource> page, I18nResourceRequest request);
	
}