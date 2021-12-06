package com.fallframework.platform.starter.mail.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.core.entity.response.ResponseResult;
import com.fallframework.platform.starter.mail.entity.MailSenderConfig;
import com.fallframework.platform.starter.mail.model.MailSenderConfigRequest;

public interface MailSenderConfigService extends IService<MailSenderConfig> {

	ResponseResult insert(MailSenderConfig mailSenderConfig);

	ResponseResult delete(Long id);

	ResponseResult update(MailSenderConfig mailSenderConfig);

	ResponseResult<MailSenderConfig> get(Long id);

	ResponseResult<Page<MailSenderConfig>> list(MailSenderConfigRequest request);

}
