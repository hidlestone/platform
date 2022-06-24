package com.fallframework.platform.starter.wechatwork.service.token;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.httpclient.util.HttpClientUtil;
import com.fallframework.platform.starter.wechatwork.constant.WechatWorkStarterConstant;
import com.fallframework.platform.starter.wechatwork.dto.GetAccessTokenDto;
import com.fallframework.platform.starter.wechatwork.model.TokenResponse;
import org.apache.http.HttpResponse;
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
		String url = WechatWorkStarterConstant.URL_GETTOKEN.replace("ID", dto.getCorpId()).replace("SECRET", dto.getSecret());
		HttpResponse httpResponse = HttpClientUtil.get(url);
		System.out.println(httpResponse);
		return null;
	}

}
