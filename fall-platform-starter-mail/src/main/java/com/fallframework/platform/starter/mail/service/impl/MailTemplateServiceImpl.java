package com.fallframework.platform.starter.mail.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.mail.entity.MailTemplate;
import com.fallframework.platform.starter.mail.mapper.MailTemplateMapper;
import com.fallframework.platform.starter.mail.service.MailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailTemplateServiceImpl extends ServiceImpl<MailTemplateMapper, MailTemplate> implements MailTemplateService {

	@Autowired
	private MailTemplateMapper mailTemplateMapper;

	@Override
	public ResponseResult<Page<MailTemplate>> list(MailTemplate mailTemplate) {
		Page<MailTemplate> page = new Page<>(mailTemplate.getPageNum(), mailTemplate.getPageSize());
		page = mailTemplateMapper.list(page, mailTemplate);
		return ResponseResult.success(page);
	}

}
