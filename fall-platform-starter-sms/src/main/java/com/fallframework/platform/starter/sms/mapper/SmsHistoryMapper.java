package com.fallframework.platform.starter.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.sms.entity.SmsHistory;
import com.fallframework.platform.starter.sms.model.SmsHistoryRequest;

public interface SmsHistoryMapper extends BaseMapper<SmsHistory> {
	
	Page<SmsHistory> list(Page<SmsHistory> page, SmsHistoryRequest request);
	
}