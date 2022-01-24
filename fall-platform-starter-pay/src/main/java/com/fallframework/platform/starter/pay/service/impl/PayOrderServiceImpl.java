package com.fallframework.platform.starter.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.pay.entity.PayOrder;
import com.fallframework.platform.starter.pay.mapper.PayOrderMapper;
import com.fallframework.platform.starter.pay.model.PayTypeInVo;
import com.fallframework.platform.starter.pay.service.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrder> implements PayOrderService {

	@Autowired
	private PayOrderMapper payOrderMapper;

	@Override
	public ResponseResult insert(PayOrder payOrder) {
		int i = payOrderMapper.insert(payOrder);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult delete(Long id) {
		int i = payOrderMapper.deleteById(id);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult update(PayOrder payOrder) {
		int i = payOrderMapper.updateById(payOrder);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult<PayOrder> get(String id) {
		PayOrder payOrder = payOrderMapper.selectById(id);
		return ResponseResult.success(payOrder);
	}

	@Override
	public ResponseResult<Page<PayOrder>> payOrderList(PayTypeInVo inVo) {
		Page<PayOrder> page = new Page<>(inVo.getPageNum(), inVo.getPageSize());
		QueryWrapper<PayOrder> wrapper = new QueryWrapper<>();
		return ResponseResult.success();
	}
}
