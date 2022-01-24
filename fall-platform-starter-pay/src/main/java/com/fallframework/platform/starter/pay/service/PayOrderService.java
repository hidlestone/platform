package com.fallframework.platform.starter.pay.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.pay.entity.PayOrder;
import com.fallframework.platform.starter.pay.model.PayTypeInVo;

public interface PayOrderService extends IService<PayOrder> {

	ResponseResult insert(PayOrder payOrder);

	ResponseResult delete(Long code);

	ResponseResult update(PayOrder payOrder);

	ResponseResult<PayOrder> get(String code);

	ResponseResult<Page<PayOrder>> payOrderList(PayTypeInVo inVo);

}
