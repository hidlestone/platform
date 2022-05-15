package com.fallframework.platform.starter.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.sms.entity.SmsTemplate;

public interface SmsTemplateService extends IService<SmsTemplate> {

	ResponseResult<Page<SmsTemplate>> list(SmsTemplate smsTemplate);

}
