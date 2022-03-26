package com.fallframework.platform.starter.mail.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.mail.entity.MailHistory;
import com.fallframework.platform.starter.mail.mapper.MailHistoryMapper;
import com.fallframework.platform.starter.mail.model.MailHistoryRequest;
import com.fallframework.platform.starter.mail.service.MailHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailHistoryServiceImpl extends ServiceImpl<MailHistoryMapper, MailHistory> implements MailHistoryService {

	@Autowired
	private MailHistoryMapper mailHistoryMapper;

	@Override
	public ResponseResult<Page<MailHistory>> list(MailHistoryRequest request) {
		Page<MailHistory> page = new Page<>(request.getPageNum(), request.getPageSize());
		page = mailHistoryMapper.list(page, request);
		return ResponseResult.success(page);
	}
}
