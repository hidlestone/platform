package com.fallframework.platform.starter.okhttp.builder;

import com.fallframework.platform.starter.okhttp.request.GetRequest;
import com.fallframework.platform.starter.okhttp.request.RequestCall;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class GetBuilder extends OkHttpRequestBuilder<GetBuilder> implements HasParamsable {

	@Override
	public RequestCall build() {
		if (params != null) {
			url = appendParams(url, params);
		}
		return new GetRequest(url, tag, params, headers, id).build();
	}

	protected String appendParams(String url, Map<String, String> params) {
		if (url == null || params == null || params.isEmpty()) {
			return url;
		}
		Set<String> keys = params.keySet();
		Iterator<String> iterator = keys.iterator();
		StringBuffer sbf = new StringBuffer(url);
		sbf.append("?");
		while (iterator.hasNext()) {
			String key = iterator.next();
			sbf.append(key + "=" + params.get(key));
		}
		return sbf.toString();
	}

	@Override
	public GetBuilder params(Map<String, String> params) {
		this.params = params;
		return this;
	}

	@Override
	public GetBuilder addParams(String key, String val) {
		if (this.params == null) {
			params = new LinkedHashMap<>();
		}
		params.put(key, val);
		return this;
	}
	
}
