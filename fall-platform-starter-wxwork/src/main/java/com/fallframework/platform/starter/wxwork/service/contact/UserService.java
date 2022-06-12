package com.fallframework.platform.starter.wxwork.service.contact;

import com.alibaba.fastjson.JSON;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.wxwork.model.TokenResponse;
import com.fallframework.platform.starter.wxwork.service.token.TokenService;
import com.google.gson.Gson;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * @author zhuangpf
 */
@Service
public class UserService {

	private static final String URL_CREATE = "https://qyapi.weixin.qq.com/cgi-bin/user/create";
	@Autowired
	private TokenService tokenService;

	/**
	 * 创建用户
	 *
	 * @param userMap 用户信息
	 * @return 是否创建成功
	 */
	public ResponseResult create(Map<String, Object> userMap) throws IOException {
		// 获取accesstoken
		String ACCESS_TOKEN = null;
		

		String url = URL_CREATE + "?" + "access_token=" + ACCESS_TOKEN;
		// 请求
		RequestBody requestBody = FormBody.create(
				MediaType.parse("application/json"), JSON.toJSONString(userMap));
		Response response = new OkHttpClient().newCall(new Request.Builder().url(url).post(requestBody).build()).execute();
		TokenResponse tokenResponse = new Gson().fromJson(response.body().string(), TokenResponse.class);
		if (response.code() != 200) {
			return ResponseResult.fail(JSON.toJSONString(tokenResponse));
		}
		if (0 != tokenResponse.getErrcode()) {
			return ResponseResult.fail(JSON.toJSONString(tokenResponse));
		}
		return ResponseResult.success();
	}

}
