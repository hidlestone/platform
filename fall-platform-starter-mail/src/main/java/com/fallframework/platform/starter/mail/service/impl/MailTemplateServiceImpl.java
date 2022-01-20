package com.fallframework.platform.starter.mail.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.mail.entity.MailTemplate;
import com.fallframework.platform.starter.mail.mapper.MailTemplateMapper;
import com.fallframework.platform.starter.mail.model.MailTemplateRequest;
import com.fallframework.platform.starter.mail.service.MailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailTemplateServiceImpl extends ServiceImpl<MailTemplateMapper, MailTemplate> implements MailTemplateService {

	@Autowired
	private MailTemplateMapper mailTemplateMapper;

	@Override
	public ResponseResult insert(MailTemplate mailTemplate) {
		int i = mailTemplateMapper.insert(mailTemplate);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult delete(Long id) {
		int i = mailTemplateMapper.deleteById(id);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult update(MailTemplate mailSenderConfig) {
		int i = mailTemplateMapper.updateById(mailSenderConfig);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult<MailTemplate> select(Long id) {
		MailTemplate mailTemplate = mailTemplateMapper.selectById(id);
		return ResponseResult.success(mailTemplate);
	}

	@Override
	public ResponseResult<Page<MailTemplate>> list(MailTemplateRequest request) {
		Page<MailTemplate> page = new Page<>(request.getPageNum(), request.getPageSize());
		page = mailTemplateMapper.list(page, request);
		return ResponseResult.success(page);
	}
	
}
