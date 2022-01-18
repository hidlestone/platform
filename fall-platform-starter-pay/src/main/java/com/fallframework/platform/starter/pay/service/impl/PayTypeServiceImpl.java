package com.fallframework.platform.starter.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.pay.entity.PayType;
import com.fallframework.platform.starter.pay.mapper.PayTypeMapper;
import com.fallframework.platform.starter.pay.model.PayTypeInVo;
import com.fallframework.platform.starter.pay.service.PayTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayTypeServiceImpl extends ServiceImpl<PayTypeMapper, PayType> implements PayTypeService {

	@Autowired
	private PayTypeMapper payTypeMapper;

	@Override
	public ResponseResult insert(PayType payType) {
		int i = payTypeMapper.insert(payType);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult delete(Long code) {
		int i = payTypeMapper.deleteById(code);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult update(PayType payType) {
		int i = payTypeMapper.updateById(payType);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult<PayType> get(String code) {
		PayType payType = payTypeMapper.selectById(code);
		return ResponseResult.success(payType);
	}

	@Override
	public ResponseResult<Page<PayType>> payTypeList(PayTypeInVo inVo) {
		Page<PayType> page = new Page<>(inVo.getPageNum(), inVo.getPageSize());
		QueryWrapper<PayType> wrapper = new QueryWrapper<>();
		if (null != inVo.getStatus()) {
			wrapper.eq("status", inVo.getStatus());
		}
		Page<PayType> resultPage = payTypeMapper.selectPage(page, wrapper);
		return ResponseResult.success(resultPage);
	}
}
