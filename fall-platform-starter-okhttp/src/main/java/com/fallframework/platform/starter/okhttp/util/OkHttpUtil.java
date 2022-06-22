package com.fallframework.platform.starter.okhttp.util;

import com.fallframework.platform.starter.okhttp.builder.GetBuilder;
import com.fallframework.platform.starter.okhttp.builder.HeadBuilder;
import com.fallframework.platform.starter.okhttp.builder.OtherRequestBuilder;
import com.fallframework.platform.starter.okhttp.builder.PostFileBuilder;
import com.fallframework.platform.starter.okhttp.builder.PostFormBuilder;
import com.fallframework.platform.starter.okhttp.builder.PostStringBuilder;
import com.fallframework.platform.starter.okhttp.callback.Callback;
import com.fallframework.platform.starter.okhttp.request.RequestCall;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author zhuangpf
 */
public class OkHttpUtil {

	public static final long DEFAULT_MILLISECONDS = 10_000L;
	// 单例
	private volatile static OkHttpUtil mInstance;
	private OkHttpClient mOkHttpClient;

	public OkHttpUtil(OkHttpClient okHttpClient) {
		if (okHttpClient == null) {
			mOkHttpClient = new OkHttpClient();
		} else {
			mOkHttpClient = okHttpClient;
		}
	}

	public static OkHttpUtil initClient(OkHttpClient okHttpClient) {
		if (mInstance == null) {
			synchronized (OkHttpUtil.class) {
				if (mInstance == null) {
					mInstance = new OkHttpUtil(okHttpClient);
				}
			}
		}
		return mInstance;
	}

	public static OkHttpUtil getInstance() {
		return initClient(null);
	}

	public Executor getDelivery() {
		return defaultCallbackExecutor();
	}

	public OkHttpClient getOkHttpClient() {
		return mOkHttpClient;
	}

	public static GetBuilder get() {
		return new GetBuilder();
	}

	public static PostStringBuilder postString() {
		return new PostStringBuilder();
	}

	public static PostFileBuilder postFile() {
		return new PostFileBuilder();
	}

	public static PostFormBuilder post() {
		return new PostFormBuilder();
	}

	public static OtherRequestBuilder put() {
		return new OtherRequestBuilder(METHOD.PUT);
	}

	public static HeadBuilder head() {
		return new HeadBuilder();
	}

	public static OtherRequestBuilder delete() {
		return new OtherRequestBuilder(METHOD.DELETE);
	}

	public static OtherRequestBuilder patch() {
		return new OtherRequestBuilder(METHOD.PATCH);
	}

	public void execute(RequestCall requestCall, Callback callback) throws IOException {
		if (callback == null)
			callback = Callback.CALLBACK_DEFAULT;
		final Callback finalCallback = callback;
		final int id = requestCall.getOkHttpRequest().getId();

		requestCall.getCall().enqueue(new okhttp3.Callback() {
			@Override
			public void onFailure(Call call, final IOException e) {
				sendFailResultCallback(call, e, finalCallback, id);
			}

			@Override
			public void onResponse(final Call call, final Response response) {
				try {
					if (call.isCanceled()) {
						sendFailResultCallback(call, new IOException("Canceled!"), finalCallback, id);
						return;
					}

					if (!finalCallback.validateReponse(response, id)) {
						sendFailResultCallback(call, new IOException("request failed , reponse's code is : " + response.code()), finalCallback, id);
						return;
					}

					Object o = finalCallback.parseNetworkResponse(response, id);
					sendSuccessResultCallback(o, finalCallback, id);
				} catch (Exception e) {
					sendFailResultCallback(call, e, finalCallback, id);
				} finally {
					if (response.body() != null)
						response.body().close();
				}

			}
		});
	}

	public void sendFailResultCallback(final Call call, final Exception e, final Callback callback, final int id) {
		if (callback == null) return;

		execute(new Runnable() {
			@Override
			public void run() {
				callback.onError(call, e, id);
				callback.onAfter(id);
			}
		});
	}

	public void sendSuccessResultCallback(final Object object, final Callback callback, final int id) {
		if (callback == null) return;
		execute(new Runnable() {
			@Override
			public void run() {
				callback.onResponse(object, id);
				callback.onAfter(id);
			}
		});
	}

	public void cancelTag(Object tag) {
		for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
			if (tag.equals(call.request().tag())) {
				call.cancel();
			}
		}
		for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
			if (tag.equals(call.request().tag())) {
				call.cancel();
			}
		}
	}

	public Executor defaultCallbackExecutor() {
		return Executors.newCachedThreadPool();
	}

	public void execute(Runnable runnable) {
		defaultCallbackExecutor().execute(runnable);
	}

	public static class METHOD {
		public static final String HEAD = "HEAD";
		public static final String DELETE = "DELETE";
		public static final String PUT = "PUT";
		public static final String PATCH = "PATCH";
	}

}
