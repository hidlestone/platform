package com.fallframework.platform.starter.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.sms.entity.SmsTemplate;

public interface SmsTemplateService extends IService<SmsTemplate> {

	Leaf<SmsTemplate> list(SmsTemplate smsTemplate);

}
