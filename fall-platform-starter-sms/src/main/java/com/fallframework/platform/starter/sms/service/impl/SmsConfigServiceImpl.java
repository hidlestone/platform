package com.fallframework.platform.starter.sms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.sms.entity.SmsConfig;
import com.fallframework.platform.starter.sms.mapper.SmsConfigMapper;
import com.fallframework.platform.starter.sms.model.SmsConfigRequest;
import com.fallframework.platform.starter.sms.service.SmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsConfigServiceImpl extends ServiceImpl<SmsConfigMapper, SmsConfig> implements SmsConfigService {

	@Autowired
	private SmsConfigMapper smsConfigMapper;

	@Override
	public ResponseResult<Page<SmsConfig>> list(SmsConfigRequest request) {
		Page<SmsConfig> page = new Page<>(request.getPageNum(), request.getPageSize());
		page = smsConfigMapper.list(page, request);
		return ResponseResult.success(page);
	}

}
