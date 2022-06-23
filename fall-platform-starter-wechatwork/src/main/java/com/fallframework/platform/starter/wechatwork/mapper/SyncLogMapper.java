package com.fallframework.platform.starter.wechatwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fallframework.platform.starter.wechatwork.entity.SyncLog;

public interface SyncLogMapper extends BaseMapper<SyncLog> {

	Page<SyncLog> list(Page<SyncLog> page, SyncLog syncLog);
	
}