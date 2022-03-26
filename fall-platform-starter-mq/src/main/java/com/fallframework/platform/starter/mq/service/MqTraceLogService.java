package com.fallframework.platform.starter.mq.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.mq.entity.MqTraceLog;
import com.fallframework.platform.starter.mq.model.MqTraceLogRequest;

public interface MqTraceLogService extends IService<MqTraceLog> {

	ResponseResult<Page<MqTraceLog>> list(MqTraceLogRequest request);

}
