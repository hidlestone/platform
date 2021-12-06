package com.fallframework.platform.starter.mail.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.mail.entity.MailTemplate;
import com.fallframework.platform.starter.mail.model.MailTemplateRequest;

public interface MailTemplateMapper extends BaseMapper<MailTemplate> {

	Page<MailTemplate> list(Page<MailTemplate> page, MailTemplateRequest request);
    
}