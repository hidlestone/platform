package com.fallframework.platform.starter.mail.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.mail.entity.MailHistory;
import com.fallframework.platform.starter.mail.model.MailHistoryRequest;

public interface MailHistoryService extends IService<MailHistory> {

	ResponseResult insert(MailHistory mailHistory);

	ResponseResult delete(Long id);

	ResponseResult update(MailHistory mailHistory);

	ResponseResult<MailHistory> get(Long id);

	ResponseResult<Page<MailHistory>> list(MailHistoryRequest request);

}
