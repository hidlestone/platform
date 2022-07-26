package com.fallframework.platform.starter.mq.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.data.mp.model.Leaf;
import com.fallframework.platform.starter.data.mp.util.LeafPageUtil;
import com.fallframework.platform.starter.mq.entity.MqTraceLog;
import com.fallframework.platform.starter.mq.mapper.MqTraceLogMapper;
import com.fallframework.platform.starter.mq.service.MqTraceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqTraceLogServiceImpl extends ServiceImpl<MqTraceLogMapper, MqTraceLog> implements MqTraceLogService {

	@Autowired
	private MqTraceLogMapper mqTraceLogMapper;

	@Override
	public Leaf<MqTraceLog> list(MqTraceLog mqTraceLog) {
		Page<MqTraceLog> page = new Page<>(mqTraceLog.getPageNum(), mqTraceLog.getPageSize());
		page = mqTraceLogMapper.list(page, mqTraceLog);
		return LeafPageUtil.pageToLeaf(page);
	}

}
