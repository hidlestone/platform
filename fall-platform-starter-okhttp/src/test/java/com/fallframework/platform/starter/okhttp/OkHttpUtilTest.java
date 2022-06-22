package com.fallframework.platform.starter.okhttp;

import com.fallframework.platform.starter.okhttp.callback.StringCallback;
import com.fallframework.platform.starter.okhttp.util.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author zhuangpf
 */
@Slf4j
public class OkHttpUtilTest {

	private static final String TAG = "MainActivity";
	// 获取access_token
	private static final String URL_GETTOKEN = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET";

	@Test
	public void getHtml() throws IOException {
		String url = "http://www.baidu.com";
		OkHttpUtil
				.get()
				.url(url)
				.id(100)
				.build()
				.execute(new MyStringCallback());
	}

	public class MyStringCallback extends StringCallback {
		@Override
		public void onBefore(Request request, int id) {
			log.info("loading...");
		}

		@Override
		public void onAfter(int id) {
			log.info("Sample-okHttp");
		}

		@Override
		public void onError(Call call, Exception e, int id) {
			e.printStackTrace();
			log.info("onError:" + e.getMessage());
		}

		@Override
		public void onResponse(String response, int id) {
			log.info(TAG, "onResponse：complete");
		}

		@Override
		public void inProgress(float progress, long total, int id) {
			log.info(TAG, "inProgress:" + progress);
		}
	}

	@Test
	public void getTest() throws IOException {
		String url = "http://localhost:9527/token/getExternalContactWorkWXToken";
		Response execute = OkHttpUtil
				.get()//
				.url(url)//
				.addParams("corpId", "hyman")
				.build()//
				.execute();
		System.out.println(execute);
	}

	@Test
	public void getTest2() throws IOException {
		String url = "http://localhost:9527/token/getExternalContactWorkWXToken";
		OkHttpUtil
				.get()//
				.url(url)//
				.addParams("corpId", "hyman")
				.build()//
				.execute(new MyStringCallback());

	}

	@Test
	public void getTes3() throws IOException {
		String url = "http://localhost:9527/token/getExternalContactWorkWXToken";
		OkHttpUtil
				.get()//
				.url(url)//
				.addParams("corpId", "hyman")
				.build()//
				.execute(new StringCallback() {
					@Override
					public void onError(Call call, Exception e, int id) {
						System.out.println("onError");
					}

					@Override
					public void onResponse(String response, int id) {
						System.out.println("onResponse");
					}
				});
	}

}
