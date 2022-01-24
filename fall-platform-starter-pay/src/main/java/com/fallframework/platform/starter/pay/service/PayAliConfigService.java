package com.fallframework.platform.starter.pay.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.pay.entity.PayAliConfig;
import com.fallframework.platform.starter.pay.model.PayAliConfigInVo;

public interface PayAliConfigService extends IService<PayAliConfig> {

	ResponseResult insert(PayAliConfig config);

	ResponseResult delete(Long id);

	ResponseResult update(PayAliConfig config);

	ResponseResult<PayAliConfig> get(Long id);

	ResponseResult<Page<PayAliConfig>> wechatPayConfigList(PayAliConfigInVo inVo);

}
