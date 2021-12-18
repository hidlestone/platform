package com.fallframework.platform.starter.pay.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.core.entity.response.ResponseResult;
import com.fallframework.platform.starter.pay.entity.PayType;
import com.fallframework.platform.starter.pay.model.PayTypeInVo;

public interface PayTypeService extends IService<PayType> {

	ResponseResult insert(PayType payType);

	ResponseResult delete(Long code);

	ResponseResult update(PayType payType);

	ResponseResult<PayType> get(String code);

	ResponseResult<Page<PayType>> payTypeList(PayTypeInVo inVo);
}
