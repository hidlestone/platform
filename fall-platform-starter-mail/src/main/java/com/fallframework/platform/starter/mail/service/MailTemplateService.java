package com.fallframework.platform.starter.mail.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.core.entity.response.ResponseResult;
import com.fallframework.platform.starter.mail.entity.MailTemplate;
import com.fallframework.platform.starter.mail.model.MailTemplateRequest;

public interface MailTemplateService extends IService<MailTemplate> {

	ResponseResult insert(MailTemplate mailTemplate);

	ResponseResult delete(Long id);

	ResponseResult update(MailTemplate mailSenderConfig);

	ResponseResult<MailTemplate> get(Long id);

	ResponseResult<Page<MailTemplate>> list(MailTemplateRequest request);

}
