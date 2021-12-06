package com.fallframework.platform.starter.mail.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.mail.entity.MailSenderConfig;
import com.fallframework.platform.starter.mail.model.MailSenderConfigRequest;

public interface MailSenderConfigMapper extends BaseMapper<MailSenderConfig> {

	Page<MailSenderConfig> list(Page<MailSenderConfig> page, MailSenderConfigRequest request);
}