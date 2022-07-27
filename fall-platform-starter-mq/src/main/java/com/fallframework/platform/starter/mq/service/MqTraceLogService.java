package com.fallframework.platform.starter.mq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.mq.entity.MqTraceLog;

public interface MqTraceLogService extends IService<MqTraceLog> {

	Leaf<MqTraceLog> list(MqTraceLog mqTraceLog);

}
