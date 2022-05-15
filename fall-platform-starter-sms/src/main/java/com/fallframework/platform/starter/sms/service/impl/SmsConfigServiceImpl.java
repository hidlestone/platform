package com.fallframework.platform.starter.sms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.sms.entity.SmsConfig;
import com.fallframework.platform.starter.sms.mapper.SmsConfigMapper;
import com.fallframework.platform.starter.sms.service.SmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsConfigServiceImpl extends ServiceImpl<SmsConfigMapper, SmsConfig> implements SmsConfigService {

	@Autowired
	private SmsConfigMapper smsConfigMapper;

	@Override
	public ResponseResult<Page<SmsConfig>> list(SmsConfig smsConfig) {
		Page<SmsConfig> page = new Page<>(smsConfig.getPageNum(), smsConfig.getPageSize());
		page = smsConfigMapper.list(page, smsConfig);
		return ResponseResult.success(page);
	}

}
