package com.fallframework.platform.starter.mq.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.mq.entity.MqTraceLog;
import com.fallframework.platform.starter.mq.mapper.MqTraceLogMapper;
import com.fallframework.platform.starter.mq.model.MqTraceLogRequest;
import com.fallframework.platform.starter.mq.service.MqTraceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqTraceLogServiceImpl extends ServiceImpl<MqTraceLogMapper, MqTraceLog> implements MqTraceLogService {

	@Autowired
	private MqTraceLogMapper mqTraceLogMapper;

	@Override
	public ResponseResult insert(MqTraceLog mqTraceLog) {
		int i = mqTraceLogMapper.insert(mqTraceLog);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult delete(Long id) {
		int i = mqTraceLogMapper.deleteById(id);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult update(MqTraceLog mqTraceLog) {
		int i = mqTraceLogMapper.updateById(mqTraceLog);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult<MqTraceLog> select(Long id) {
		MqTraceLog mqTraceLog = mqTraceLogMapper.selectById(id);
		return ResponseResult.success(mqTraceLog);
	}

	@Override
	public ResponseResult<Page<MqTraceLog>> list(MqTraceLogRequest request) {
		Page<MqTraceLog> page = new Page<>(request.getPageNum(), request.getPageSize());
		page = mqTraceLogMapper.list(page, request);
		return ResponseResult.success(page);
	}

}
