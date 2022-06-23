package com.fallframework.platform.starter.wechatwork.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.wechatwork.entity.SyncLog;
import com.fallframework.platform.starter.wechatwork.mapper.SyncLogMapper;
import com.fallframework.platform.starter.wechatwork.service.SyncLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SyncLogServiceImpl extends ServiceImpl<SyncLogMapper, SyncLog> implements SyncLogService {

	@Autowired
	private SyncLogMapper syncLogMapper;

	@Override
	public ResponseResult<Page<SyncLog>> list(SyncLog syncLog) {
		Page<SyncLog> page = new Page<>(syncLog.getPageNum(), syncLog.getPageSize());
		page = syncLogMapper.list(page, syncLog);
		return ResponseResult.success(page);
	}

}
