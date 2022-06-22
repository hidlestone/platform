package com.fallframework.platform.starter.okhttp.callback;

import okhttp3.Response;

import java.io.IOException;

public abstract class StringCallback extends Callback<String> {

	@Override
	public String parseNetworkResponse(Response response, int id) throws IOException {
		System.out.println(response.body().string());
		return response.body().string();
	}

}
