package com.fallframework.platform.starter.wxwork.service.token;

import com.alibaba.fastjson.JSON;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.wxwork.dto.GetAccessTokenDto;
import com.fallframework.platform.starter.wxwork.model.TokenResponse;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author zhuangpf
 */
@Service
public class TokenService {

	private final static Logger LOGGER = LoggerFactory.getLogger(TokenService.class);

	public static final String URL_GETTOKEN = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

	public ResponseResult gettoken(GetAccessTokenDto dto) throws IOException {
		String url = URL_GETTOKEN + "?" + "corpid=" + dto.getCorpId() + "&" + "corpsecret=" + dto.getSecret();
		// 请求
		Response response = new OkHttpClient().newCall(new Request.Builder().url(url).get().build()).execute();
		if (response.code() == 200) {
			TokenResponse tokenResponse = new Gson().fromJson(response.body().string(), TokenResponse.class);
			if (tokenResponse.getErrcode() == 0) {
				LOGGER.info(tokenResponse.toString());
				tokenResponse.setLoseTime(System.currentTimeMillis() + tokenResponse.getExpires_in() * 1000);
				tokenResponse.setCorpId(dto.getCorpId());
				tokenResponse.setAccessTokenType(dto.getAccessTokenType());
				LOGGER.info("tokenResponse : " + JSON.toJSONString(tokenResponse));
				// TODO : 将access_token存储到缓存中

				return ResponseResult.success();
			}
		}
		return ResponseResult.success();
	}

}
