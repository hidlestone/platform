package com.fallframework.platform.starter.okhttp.log;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoggerInterceptor implements Interceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerInterceptor.class);

	public static final String TAG = "OkHttpUtil";
	private String tag;
	private boolean showResponse;

	public LoggerInterceptor(String tag, boolean showResponse) {
		if (StringUtils.isEmpty(tag)) {
			tag = TAG;
		}
		this.showResponse = showResponse;
		this.tag = tag;
	}

	public LoggerInterceptor(String tag) {
		this(tag, false);
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
		logForRequest(request);
		Response response = chain.proceed(request);
		return logForResponse(response);
	}

	private Response logForResponse(Response response) {
		try {
			//===>response log
			LOGGER.info(tag, "========response'log=======");
			Response.Builder builder = response.newBuilder();
			Response clone = builder.build();
			LOGGER.info(tag, "url : " + clone.request().url());
			LOGGER.info(tag, "code : " + clone.code());
			LOGGER.info(tag, "protocol : " + clone.protocol());
			if (!StringUtils.isEmpty(clone.message()))
				LOGGER.info(tag, "message : " + clone.message());

			if (showResponse) {
				ResponseBody body = clone.body();
				if (body != null) {
					MediaType mediaType = body.contentType();
					if (mediaType != null) {
						LOGGER.info(tag, "responseBody's contentType : " + mediaType.toString());
						if (isText(mediaType)) {
							String resp = body.string();
							LOGGER.info(tag, "responseBody's content : " + resp);

							body = ResponseBody.create(mediaType, resp);
							return response.newBuilder().body(body).build();
						} else {
							LOGGER.info(tag, "responseBody's content : " + " maybe [file part] , too large too print , ignored!");
						}
					}
				}
			}

			LOGGER.info(tag, "========response'log=======end");
		} catch (Exception e) {
//            e.printStackTrace();
		}

		return response;
	}

	private void logForRequest(Request request) {
		try {
			String url = request.url().toString();
			Headers headers = request.headers();

			LOGGER.info(tag, "========request'log=======");
			LOGGER.info(tag, "method : " + request.method());
			LOGGER.info(tag, "url : " + url);
			if (headers != null && headers.size() > 0) {
				LOGGER.info(tag, "headers : " + headers.toString());
			}
			RequestBody requestBody = request.body();
			if (requestBody != null) {
				MediaType mediaType = requestBody.contentType();
				if (mediaType != null) {
					LOGGER.info(tag, "requestBody's contentType : " + mediaType.toString());
					if (isText(mediaType)) {
						LOGGER.info(tag, "requestBody's content : " + bodyToString(request));
					} else {
						LOGGER.info(tag, "requestBody's content : " + " maybe [file part] , too large too print , ignored!");
					}
				}
			}
			LOGGER.info(tag, "========request'log=======end");
		} catch (Exception e) {
//            e.printStackTrace();
		}
	}

	private boolean isText(MediaType mediaType) {
		if (mediaType.type() != null && mediaType.type().equals("text")) {
			return true;
		}
		if (mediaType.subtype() != null) {
			if (mediaType.subtype().equals("json") ||
					mediaType.subtype().equals("xml") ||
					mediaType.subtype().equals("html") ||
					mediaType.subtype().equals("webviewhtml")
					)
				return true;
		}
		return false;
	}

	private String bodyToString(final Request request) {
		try {
			final Request copy = request.newBuilder().build();
			final Buffer buffer = new Buffer();
			copy.body().writeTo(buffer);
			return buffer.readUtf8();
		} catch (final IOException e) {
			return "something error when show requestBody.";
		}
	}
	
}
