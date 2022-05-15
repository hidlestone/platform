package com.fallframework.platform.starter.mq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.mq.entity.MqTraceLog;

public interface MqTraceLogMapper extends BaseMapper<MqTraceLog> {

	Page<MqTraceLog> list(Page<MqTraceLog> page, MqTraceLog mqTraceLog);

}