package com.fallframework.platform.starter.mail.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.data.mp.model.Leaf;
import com.fallframework.platform.starter.mail.entity.MailTemplate;

public interface MailTemplateService extends IService<MailTemplate> {

	Leaf<MailTemplate> list(MailTemplate mailTemplate);

}
