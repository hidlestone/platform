package com.fallframework.platform.starter.wechatwork.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.wechatwork.entity.SynConfig;
import com.fallframework.platform.starter.wechatwork.mapper.SynConfigMapper;
import com.fallframework.platform.starter.wechatwork.service.SynConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SynConfigServiceImpl extends ServiceImpl<SynConfigMapper, SynConfig> implements SynConfigService {

	@Autowired
	private SynConfigMapper synConfigMapper;

	@Override
	public ResponseResult<Page<SynConfig>> list(SynConfig synConfig) {
		Page<SynConfig> page = new Page<>(synConfig.getPageNum(), synConfig.getPageSize());
		page = synConfigMapper.list(page, synConfig);
		return ResponseResult.success(page);
	}

}
