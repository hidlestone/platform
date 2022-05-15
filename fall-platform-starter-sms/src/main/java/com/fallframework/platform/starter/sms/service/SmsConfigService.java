package com.fallframework.platform.starter.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.sms.entity.SmsConfig;

public interface SmsConfigService extends IService<SmsConfig> {

	ResponseResult<Page<SmsConfig>> list(SmsConfig smsConfig);

}
