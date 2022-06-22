package com.fallframework.platform.starter.okhttp.builder;

import com.fallframework.platform.starter.okhttp.util.OkHttpUtil;
import com.fallframework.platform.starter.okhttp.request.OtherRequest;
import com.fallframework.platform.starter.okhttp.request.RequestCall;

public class HeadBuilder extends GetBuilder {

	@Override
	public RequestCall build() {
		return new OtherRequest(null, null, OkHttpUtil.METHOD.HEAD, url, tag, params, headers, id).build();
	}
	
}
