package com.fallframework.platform.starter.sms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.data.mp.model.Leaf;
import com.fallframework.platform.starter.data.mp.util.LeafPageUtil;
import com.fallframework.platform.starter.sms.entity.SmsTemplate;
import com.fallframework.platform.starter.sms.mapper.SmsTemplateMapper;
import com.fallframework.platform.starter.sms.service.SmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsTemplateServiceImpl extends ServiceImpl<SmsTemplateMapper, SmsTemplate> implements SmsTemplateService {

	@Autowired
	private SmsTemplateMapper smsTemplateMapper;

	@Override
	public Leaf<SmsTemplate> list(SmsTemplate smsTemplate) {
		Page<SmsTemplate> page = new Page<>(smsTemplate.getPageNum(), smsTemplate.getPageSize());
		page = smsTemplateMapper.list(page, smsTemplate);
		return LeafPageUtil.pageToLeaf(page);
	}

}
