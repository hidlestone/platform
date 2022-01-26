package com.fallframework.platform.starter.sms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.sms.entity.SmsTemplate;
import com.fallframework.platform.starter.sms.mapper.SmsTemplateMapper;
import com.fallframework.platform.starter.sms.model.SmsTemplateReqeust;
import com.fallframework.platform.starter.sms.service.SmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsTemplateServiceImpl extends ServiceImpl<SmsTemplateMapper, SmsTemplate> implements SmsTemplateService {

	@Autowired
	private SmsTemplateMapper smsTemplateMapper;

	@Override
	public ResponseResult<Page<SmsTemplate>> list(SmsTemplateReqeust request) {
		Page<SmsTemplate> page = new Page<>(request.getPageNum(), request.getPageSize());
		page = smsTemplateMapper.list(page, request);
		return ResponseResult.success(page);
	}

}
