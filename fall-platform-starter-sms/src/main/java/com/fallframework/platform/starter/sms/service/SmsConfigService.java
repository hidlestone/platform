package com.fallframework.platform.starter.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.sms.entity.SmsConfig;

public interface SmsConfigService extends IService<SmsConfig> {

	Leaf<SmsConfig> list(SmsConfig smsConfig);

}
