package com.fallframework.platform.starter.okhttp.request;

import com.fallframework.platform.starter.okhttp.util.OkHttpUtil;
import com.fallframework.platform.starter.okhttp.callback.Callback;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 对OkHttpRequest的封装，对外提供更多的接口：cancel(),readTimeOut()...
 */
public class RequestCall {
	
	private OkHttpRequest okHttpRequest;
	private Request request;
	private Call call;

	private long readTimeOut;
	private long writeTimeOut;
	private long connTimeOut;

	private OkHttpClient clone;

	public RequestCall(OkHttpRequest request) {
		this.okHttpRequest = request;
	}

	public RequestCall readTimeOut(long readTimeOut) {
		this.readTimeOut = readTimeOut;
		return this;
	}

	public RequestCall writeTimeOut(long writeTimeOut) {
		this.writeTimeOut = writeTimeOut;
		return this;
	}

	public RequestCall connTimeOut(long connTimeOut) {
		this.connTimeOut = connTimeOut;
		return this;
	}

	public Call buildCall(Callback callback) {
		request = generateRequest(callback);

		if (readTimeOut > 0 || writeTimeOut > 0 || connTimeOut > 0) {
			readTimeOut = readTimeOut > 0 ? readTimeOut : OkHttpUtil.DEFAULT_MILLISECONDS;
			writeTimeOut = writeTimeOut > 0 ? writeTimeOut : OkHttpUtil.DEFAULT_MILLISECONDS;
			connTimeOut = connTimeOut > 0 ? connTimeOut : OkHttpUtil.DEFAULT_MILLISECONDS;

			clone = OkHttpUtil.getInstance().getOkHttpClient().newBuilder()
					.readTimeout(readTimeOut, TimeUnit.MILLISECONDS)
					.writeTimeout(writeTimeOut, TimeUnit.MILLISECONDS)
					.connectTimeout(connTimeOut, TimeUnit.MILLISECONDS)
					.build();

			call = clone.newCall(request);
		} else {
			call = OkHttpUtil.getInstance().getOkHttpClient().newCall(request);
		}
		return call;
	}

	private Request generateRequest(Callback callback) {
		return okHttpRequest.generateRequest(callback);
	}

	public void execute(Callback callback) throws IOException {
		buildCall(callback);

		if (callback != null) {
			callback.onBefore(request, getOkHttpRequest().getId());
		}

		OkHttpUtil.getInstance().execute(this, callback);
	}

	public Call getCall() {
		return call;
	}

	public Request getRequest() {
		return request;
	}

	public OkHttpRequest getOkHttpRequest() {
		return okHttpRequest;
	}

	public Response execute() throws IOException {
		buildCall(null);
		return call.execute();
	}

	public void cancel() {
		if (call != null) {
			call.cancel();
		}
	}

}
