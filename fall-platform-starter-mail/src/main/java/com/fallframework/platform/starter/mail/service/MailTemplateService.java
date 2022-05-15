package com.fallframework.platform.starter.mail.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.mail.entity.MailTemplate;

public interface MailTemplateService extends IService<MailTemplate> {

	ResponseResult<Page<MailTemplate>> list(MailTemplate mailTemplate);

}
