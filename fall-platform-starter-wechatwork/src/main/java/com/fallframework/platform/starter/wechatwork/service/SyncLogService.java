package com.fallframework.platform.starter.wechatwork.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.wechatwork.entity.SyncLog;

public interface SyncLogService extends IService<SyncLog> {

	ResponseResult<Page<SyncLog>> list(SyncLog syncLog);

}