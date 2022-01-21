package com.fallframework.platform.starter.mq.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.mq.api.entity.MqTraceRecord;
import com.fallframework.platform.starter.mq.api.model.MqTraceRecordRequest;

public interface MqTraceRecordService extends IService<MqTraceRecord> {

	ResponseResult insert(MqTraceRecord mqTraceRecord);

	ResponseResult delete(Long id);

	ResponseResult update(MqTraceRecord mqTraceRecord);

	ResponseResult<MqTraceRecord> get(Long id);

	ResponseResult<Page<MqTraceRecord>> list(MqTraceRecordRequest request);
	
}
