package com.fallframework.platform.starter.mq.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.mq.api.entity.MqTraceRecord;
import com.fallframework.platform.starter.mq.api.mapper.MqTraceRecordMapper;
import com.fallframework.platform.starter.mq.api.model.MqTraceRecordRequest;
import com.fallframework.platform.starter.mq.api.service.MqTraceRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqTraceRecordServiceImpl extends ServiceImpl<MqTraceRecordMapper, MqTraceRecord> implements MqTraceRecordService {

	@Autowired
	private MqTraceRecordMapper traceRecordMapper;

	@Override
	public ResponseResult insert(MqTraceRecord mqTraceRecord) {
		int i = traceRecordMapper.insert(mqTraceRecord);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult delete(Long id) {
		int i = traceRecordMapper.deleteById(id);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult update(MqTraceRecord mqTraceRecord) {
		int i = traceRecordMapper.updateById(mqTraceRecord);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult<MqTraceRecord> get(Long id) {
		MqTraceRecord mqTraceRecord = traceRecordMapper.selectById(id);
		return ResponseResult.success(mqTraceRecord);
	}

	@Override
	public ResponseResult<Page<MqTraceRecord>> list(MqTraceRecordRequest request) {
		Page<MqTraceRecord> page = new Page<>(request.getPageNum(), request.getPageSize());
		QueryWrapper<MqTraceRecord> wrapper = new QueryWrapper<>();
		if (StringUtils.isNotEmpty(request.getStage())) {
			wrapper.like("stage", request.getStage());
		}
		if (StringUtils.isNotEmpty(request.getExchange())) {
			wrapper.like("exchange", request.getExchange());
		}
		if (StringUtils.isNotEmpty(request.getRouteKey())) {
			wrapper.like("route_key", request.getRouteKey());
		}
		Page<MqTraceRecord> resultPage = traceRecordMapper.selectPage(page, wrapper);
		return ResponseResult.success(resultPage);
	}
}
