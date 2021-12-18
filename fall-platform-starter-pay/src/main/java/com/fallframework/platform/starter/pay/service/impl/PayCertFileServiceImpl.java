package com.fallframework.platform.starter.pay.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.core.entity.response.ResponseResult;
import com.fallframework.platform.starter.pay.entity.PayCertFile;
import com.fallframework.platform.starter.pay.mapper.PayCertFileMapper;
import com.fallframework.platform.starter.pay.model.PayCertFileInVo;
import com.fallframework.platform.starter.pay.service.PayCertFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayCertFileServiceImpl extends ServiceImpl<PayCertFileMapper, PayCertFile> implements PayCertFileService {

	@Autowired
	private PayCertFileMapper certFileMapper;

	@Override
	public ResponseResult insert(PayCertFile payCertFile) {
		int i = certFileMapper.insert(payCertFile);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult delete(Long id) {
		int i = certFileMapper.deleteById(id);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult update(PayCertFile payCertFile) {
		int i = certFileMapper.updateById(payCertFile);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult<PayCertFile> get(Long id) {
		PayCertFile payCertFile = certFileMapper.selectById(id);
		return ResponseResult.success(payCertFile);
	}

	@Override
	public ResponseResult<Page<PayCertFile>> payCertFileList(PayCertFileInVo inVo) {
		return null;
	}
}
