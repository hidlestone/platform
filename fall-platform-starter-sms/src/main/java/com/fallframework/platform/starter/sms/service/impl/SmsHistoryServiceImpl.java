package com.fallframework.platform.starter.sms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.sms.entity.SmsHistory;
import com.fallframework.platform.starter.sms.mapper.SmsHistoryMapper;
import com.fallframework.platform.starter.sms.service.SmsHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsHistoryServiceImpl extends ServiceImpl<SmsHistoryMapper, SmsHistory> implements SmsHistoryService {

	@Autowired
	private SmsHistoryMapper smsHistoryMapper;

	@Override
	public ResponseResult<Page<SmsHistory>> list(SmsHistory smsHistory) {
		Page<SmsHistory> page = new Page<>(smsHistory.getPageNum(), smsHistory.getPageSize());
		page = smsHistoryMapper.list(page, smsHistory);
		return ResponseResult.success(page);
	}
}
