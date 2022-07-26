package com.fallframework.platform.starter.mail.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.data.mp.model.Leaf;
import com.fallframework.platform.starter.mail.entity.MailSenderConfig;

public interface MailSenderConfigService extends IService<MailSenderConfig> {

	Leaf<MailSenderConfig> list(MailSenderConfig mailSenderConfig);

}
