package com.fallframework.platform.starter.wxwork.util;

import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.wxwork.constant.WxworkStarterConstant;
import com.fallframework.platform.starter.wxwork.dto.GetAccessTokenDto;
import com.fallframework.platform.starter.wxwork.service.token.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author zhuangpf
 */
@Service
public class AccessTokenUtil {

	@Autowired
	private TokenService tokenService;
	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 获取access_token
	 */
	public String getAccessToken(GetAccessTokenDto dto) throws IOException {
		// 从缓存中获取通讯录accesstoken
		String access_token = (String) redisUtil.hget(WxworkStarterConstant.CACHE_KEY_ACCESS_TOKEN, dto.getAccessTokenType().name());
		if (StringUtils.isEmpty(access_token)) {
			access_token = tokenService.getToken(dto).getData().getAccess_token();
		}
		return access_token;
	}

}
