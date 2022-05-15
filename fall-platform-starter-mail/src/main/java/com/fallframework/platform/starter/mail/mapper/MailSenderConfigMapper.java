package com.fallframework.platform.starter.mail.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.mail.entity.MailSenderConfig;

public interface MailSenderConfigMapper extends BaseMapper<MailSenderConfig> {

	Page<MailSenderConfig> list(Page<MailSenderConfig> page, MailSenderConfig mailSenderConfig);
}