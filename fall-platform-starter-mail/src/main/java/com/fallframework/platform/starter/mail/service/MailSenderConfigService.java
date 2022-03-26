package com.fallframework.platform.starter.mail.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.mail.entity.MailSenderConfig;
import com.fallframework.platform.starter.mail.model.MailSenderConfigRequest;

public interface MailSenderConfigService extends IService<MailSenderConfig> {

	ResponseResult<Page<MailSenderConfig>> list(MailSenderConfigRequest request);

}
