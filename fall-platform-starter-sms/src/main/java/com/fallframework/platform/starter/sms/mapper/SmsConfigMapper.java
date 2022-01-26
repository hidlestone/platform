package com.fallframework.platform.starter.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.sms.entity.SmsConfig;
import com.fallframework.platform.starter.sms.model.SmsConfigRequest;

public interface SmsConfigMapper extends BaseMapper<SmsConfig> {

	Page<SmsConfig> list(Page<SmsConfig> page, SmsConfigRequest request);
	
}