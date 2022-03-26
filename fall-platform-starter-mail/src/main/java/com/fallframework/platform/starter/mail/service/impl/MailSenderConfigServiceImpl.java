package com.fallframework.platform.starter.mail.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.mail.entity.MailSenderConfig;
import com.fallframework.platform.starter.mail.mapper.MailSenderConfigMapper;
import com.fallframework.platform.starter.mail.model.MailSenderConfigRequest;
import com.fallframework.platform.starter.mail.service.MailSenderConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailSenderConfigServiceImpl extends ServiceImpl<MailSenderConfigMapper, MailSenderConfig> implements MailSenderConfigService {

	@Autowired
	private MailSenderConfigMapper mailSenderConfigMapper;

	@Override
	public ResponseResult<Page<MailSenderConfig>> list(MailSenderConfigRequest request) {
		Page<MailSenderConfig> page = new Page<>(request.getPageNum(), request.getPageSize());
		page = mailSenderConfigMapper.list(page, request);
		return ResponseResult.success(page);
	}

}
