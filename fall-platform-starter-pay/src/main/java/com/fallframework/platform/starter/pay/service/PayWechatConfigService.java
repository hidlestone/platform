package com.fallframework.platform.starter.pay.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.core.entity.response.ResponseResult;
import com.fallframework.platform.starter.pay.entity.PayWechatConfig;
import com.fallframework.platform.starter.pay.model.PayWechatConfigInVo;

public interface PayWechatConfigService extends IService<PayWechatConfig> {

	ResponseResult insert(PayWechatConfig config);

	ResponseResult delete(Long id);

	ResponseResult update(PayWechatConfig config);

	ResponseResult<PayWechatConfig> get(Long id);

	ResponseResult<Page<PayWechatConfig>> wechatPayConfigList(PayWechatConfigInVo inVo);

}

