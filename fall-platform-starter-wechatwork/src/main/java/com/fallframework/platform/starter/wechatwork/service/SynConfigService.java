package com.fallframework.platform.starter.wechatwork.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.wechatwork.entity.SynConfig;

public interface SynConfigService extends IService<SynConfig> {

	ResponseResult<Page<SynConfig>> list(SynConfig synConfig);

}
