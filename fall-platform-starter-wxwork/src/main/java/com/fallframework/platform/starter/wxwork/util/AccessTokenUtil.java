package com.fallframework.platform.starter.wxwork.util;

import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.wxwork.config.WxCpProperties;
import com.fallframework.platform.starter.wxwork.constant.WxworkStarterConstant;
import com.fallframework.platform.starter.wxwork.dto.GetAccessTokenDto;
import com.fallframework.platform.starter.wxwork.model.AccessTokenTypeEnum;
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
	@Autowired
	private WxCpProperties wxCpProperties;

	/**
	 * 获取access_token
	 */
	public String getAccessToken(GetAccessTokenDto dto) {
		// 从缓存中获取通讯录accesstoken
		String access_token = (String) redisUtil.hget(WxworkStarterConstant.CACHE_KEY_ACCESS_TOKEN, dto.getAccessTokenType().name());
		// 缓存中不存在，则重新请求获取
		try {
			if (StringUtils.isEmpty(access_token)) {
				access_token = tokenService.getToken(dto).getData().getAccess_token();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return access_token;
	}

	/**
	 * 获取token
	 */
	public String getContactAccessToken() {
		// 从缓存中获取通讯录accesstoken
		GetAccessTokenDto dto = new GetAccessTokenDto();
		dto.setCorpId(wxCpProperties.getCorpId());
		dto.setSecret(wxCpProperties.getContactConfig().getSecret());
		dto.setAccessTokenType(AccessTokenTypeEnum.CONTACT);
		return this.getAccessToken(dto);
	}

}
