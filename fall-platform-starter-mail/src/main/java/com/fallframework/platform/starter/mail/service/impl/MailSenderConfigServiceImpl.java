package com.fallframework.platform.starter.mail.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.data.mp.model.Leaf;
import com.fallframework.platform.starter.data.mp.util.LeafPageUtil;
import com.fallframework.platform.starter.mail.entity.MailSenderConfig;
import com.fallframework.platform.starter.mail.mapper.MailSenderConfigMapper;
import com.fallframework.platform.starter.mail.service.MailSenderConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailSenderConfigServiceImpl extends ServiceImpl<MailSenderConfigMapper, MailSenderConfig> implements MailSenderConfigService {

	@Autowired
	private MailSenderConfigMapper mailSenderConfigMapper;

	@Override
	public Leaf<MailSenderConfig> list(MailSenderConfig mailSenderConfig) {
		Page<MailSenderConfig> page = new Page<>(mailSenderConfig.getPageNum(), mailSenderConfig.getPageSize());
		page = mailSenderConfigMapper.list(page, mailSenderConfig);
		return LeafPageUtil.pageToLeaf(page);
	}

}
