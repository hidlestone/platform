package com.fallframework.platform.starter.wxwork.service.token;

import com.alibaba.fastjson.JSON;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.wxwork.constant.WxworkStarterConstant;
import com.fallframework.platform.starter.wxwork.dto.GetAccessTokenDto;
import com.fallframework.platform.starter.wxwork.model.TokenResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author zhuangpf
 */
@Service
public class TokenService {

	private final static Logger LOGGER = LoggerFactory.getLogger(TokenService.class);
	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 请求获取token
	 */
	public ResponseResult<TokenResponse> getToken(GetAccessTokenDto dto) throws IOException {
		String url = WxworkStarterConstant.URL_GETTOKEN.replace("ID", dto.getCorpId()).replace("SECRET", dto.getSecret());
		// 请求
		Response response = new OkHttpClient().newCall(new Request.Builder().url(url).get().build()).execute();
		TokenResponse tokenResponse = JSON.parseObject(response.body().string(), TokenResponse.class);
		if (response.code() == 200) {
			if (tokenResponse.getErrcode() == 0) {
				LOGGER.info(JSON.toJSONString(tokenResponse));
				tokenResponse.setLoseTime(System.currentTimeMillis() + tokenResponse.getExpires_in() * 1000);
				tokenResponse.setCorpId(dto.getCorpId());
				tokenResponse.setAccessTokenType(dto.getAccessTokenType());
				LOGGER.info("tokenResponse : " + JSON.toJSONString(tokenResponse));
				// 将access_token存储到缓存中
				redisUtil.hset(
						WxworkStarterConstant.CACHE_KEY_ACCESS_TOKEN,
						dto.getAccessTokenType().name(),
						tokenResponse.getAccess_token(),
						tokenResponse.getExpires_in());
				return ResponseResult.success();
			}
		}
		return ResponseResult.success(tokenResponse);
	}

}
