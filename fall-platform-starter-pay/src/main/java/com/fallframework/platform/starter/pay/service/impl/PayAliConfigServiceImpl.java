package com.fallframework.platform.starter.pay.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.pay.entity.PayAliConfig;
import com.fallframework.platform.starter.pay.mapper.PayAliConfigMapper;
import com.fallframework.platform.starter.pay.model.PayAliConfigInVo;
import com.fallframework.platform.starter.pay.service.PayAliConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayAliConfigServiceImpl extends ServiceImpl<PayAliConfigMapper, PayAliConfig> implements PayAliConfigService {

	@Autowired
	private PayAliConfigMapper payAliConfigMapper;

	@Override
	public ResponseResult insert(PayAliConfig config) {
		int i = payAliConfigMapper.insert(config);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult delete(Long id) {
		int i = payAliConfigMapper.deleteById(id);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult update(PayAliConfig config) {
		int i = payAliConfigMapper.updateById(config);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult get(Long id) {
		PayAliConfig payAliConfig = payAliConfigMapper.selectById(id);
		return ResponseResult.success(payAliConfig);
	}

	@Override
	public ResponseResult<Page<PayAliConfig>> wechatPayConfigList(PayAliConfigInVo inVo) {
		return null;
	}
}
