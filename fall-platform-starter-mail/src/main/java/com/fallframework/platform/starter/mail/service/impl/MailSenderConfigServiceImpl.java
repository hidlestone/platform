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
	public ResponseResult insert(MailSenderConfig mailSenderConfig) {
		int i = mailSenderConfigMapper.insert(mailSenderConfig);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult delete(Long id) {
		int i = mailSenderConfigMapper.deleteById(id);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult update(MailSenderConfig mailSenderConfig) {
		int i = mailSenderConfigMapper.updateById(mailSenderConfig);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult get(Long id) {
		MailSenderConfig mailSenderConfig = mailSenderConfigMapper.selectById(id);
		return ResponseResult.success(mailSenderConfig);
	}

	@Override
	public ResponseResult<Page<MailSenderConfig>> list(MailSenderConfigRequest request) {
		Page<MailSenderConfig> page = new Page<>(request.getPageNum(), request.getPageSize());
		page = mailSenderConfigMapper.list(page, request);
		return ResponseResult.success(page);
	}

}
