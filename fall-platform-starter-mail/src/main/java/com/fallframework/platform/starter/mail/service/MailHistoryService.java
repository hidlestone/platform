package com.fallframework.platform.starter.mail.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.mail.entity.MailHistory;

public interface MailHistoryService extends IService<MailHistory> {

	Leaf<MailHistory> list(MailHistory mailHistory);

}
