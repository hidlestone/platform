package com.fallframework.platform.starter.mail.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.mail.entity.MailHistory;
import com.fallframework.platform.starter.mail.model.MailHistoryRequest;

public interface MailHistoryMapper extends BaseMapper<MailHistory> {

	Page<MailHistory> list(Page<MailHistory> page, MailHistoryRequest request);

}