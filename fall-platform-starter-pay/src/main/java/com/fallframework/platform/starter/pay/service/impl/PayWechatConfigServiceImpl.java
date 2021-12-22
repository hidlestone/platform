package com.fallframework.platform.starter.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.core.entity.response.ResponseResult;
import com.fallframework.platform.starter.pay.entity.PayWechatConfig;
import com.fallframework.platform.starter.pay.mapper.PayWechatConfigMapper;
import com.fallframework.platform.starter.pay.model.PayWechatConfigInVo;
import com.fallframework.platform.starter.pay.service.PayWechatConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayWechatConfigServiceImpl extends ServiceImpl<PayWechatConfigMapper, PayWechatConfig> implements PayWechatConfigService {

	@Autowired
	private PayWechatConfigMapper configMapper;

	@Override
	public ResponseResult insert(PayWechatConfig config) {
		int i = configMapper.insert(config);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult delete(Long id) {
		int i = configMapper.deleteById(id);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult update(PayWechatConfig config) {
		int i = configMapper.updateById(config);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult<PayWechatConfig> get(Long id) {
		PayWechatConfig payWechatConfig = configMapper.selectById(id);
		return ResponseResult.success(payWechatConfig);
	}

	@Override
	public ResponseResult<Page<PayWechatConfig>> wechatPayConfigList(PayWechatConfigInVo inVo) {
		Page<PayWechatConfig> page = new Page<>(inVo.getPageNum(), inVo.getPageSize());
		QueryWrapper<PayWechatConfig> wrapper = new QueryWrapper<>();
		return ResponseResult.success();
	}
}

