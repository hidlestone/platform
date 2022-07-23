package com.fallframework.platform.starter.tencetcos.service;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.tencetcos.cloud.Response;

/**
 * @author zhuangpf
 */
public interface CosStsService {

	ResponseResult<Response> getCredential();
}
