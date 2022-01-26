package com.fallframework.platform.starter.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.sms.entity.SmsTemplate;
import com.fallframework.platform.starter.sms.model.SmsTemplateReqeust;

public interface SmsTemplateMapper extends BaseMapper<SmsTemplate> {
	
	Page<SmsTemplate> list(Page<SmsTemplate> page, SmsTemplateReqeust request);
	
}