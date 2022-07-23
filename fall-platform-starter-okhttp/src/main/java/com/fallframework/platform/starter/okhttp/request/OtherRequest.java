package com.fallframework.platform.starter.okhttp.request;

import com.fallframework.platform.starter.okhttp.util.OkHttpUtil;
import com.fallframework.platform.starter.okhttp.util.Exceptions;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.http.HttpMethod;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;


public class OtherRequest extends OkHttpRequest {
	private static MediaType MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8");

	private RequestBody requestBody;
	private String method;
	private String content;

	public OtherRequest(RequestBody requestBody, String content, String method, String url, Object tag, Map<String, String> params, Map<String, String> headers, int id) {
		super(url, tag, params, headers, id);
		this.requestBody = requestBody;
		this.method = method;
		this.content = content;

	}

	@Override
	protected RequestBody buildRequestBody() {
		if (requestBody == null && StringUtils.isEmpty(content) && HttpMethod.requiresRequestBody(method)) {
			Exceptions.illegalArgument("requestBody and content can not be null in method:" + method);
		}
		if (requestBody == null && !StringUtils.isEmpty(content)) {
			requestBody = RequestBody.create(MEDIA_TYPE_PLAIN, content);
		}
		return requestBody;
	}

	@Override
	protected Request buildRequest(RequestBody requestBody) {
		if (method.equals(OkHttpUtil.METHOD.PUT)) {
			builder.put(requestBody);
		} else if (method.equals(OkHttpUtil.METHOD.DELETE)) {
			if (requestBody == null)
				builder.delete();
			else
				builder.delete(requestBody);
		} else if (method.equals(OkHttpUtil.METHOD.HEAD)) {
			builder.head();
		} else if (method.equals(OkHttpUtil.METHOD.PATCH)) {
			builder.patch(requestBody);
		}
		return builder.build();
	}

}