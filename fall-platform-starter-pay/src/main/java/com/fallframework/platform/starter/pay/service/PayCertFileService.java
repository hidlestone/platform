package com.fallframework.platform.starter.pay.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.pay.entity.PayCertFile;
import com.fallframework.platform.starter.pay.model.PayCertFileInVo;

public interface PayCertFileService extends IService<PayCertFile> {

	ResponseResult insert(PayCertFile payCertFile);

	ResponseResult delete(Long id);

	ResponseResult update(PayCertFile payCertFile);

	ResponseResult<PayCertFile> get(Long id);

	ResponseResult<Page<PayCertFile>> payCertFileList(PayCertFileInVo inVo);
}
