package com.fallframework.platform.starter.wechatwork.util;

import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.wechatwork.config.WechatWorkProperties;
import com.fallframework.platform.starter.wechatwork.constant.WechatWorkStarterConstant;
import com.fallframework.platform.starter.wechatwork.dto.GetAccessTokenDto;
import com.fallframework.platform.starter.wechatwork.model.AccessTokenTypeEnum;
import com.fallframework.platform.starter.wechatwork.service.token.TokenService;
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
	private WechatWorkProperties wechatWorkProperties;

	/**
	 * 获取access_token
	 */
	public String getAccessToken(GetAccessTokenDto dto) {
		// 从缓存中获取通讯录accesstoken
		String access_token = (String) redisUtil.hget(WechatWorkStarterConstant.CACHE_KEY_ACCESS_TOKEN, dto.getAccessTokenType().name());
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
	 * 获取通讯录token
	 */
	public String getContactAccessToken() {
		// 从缓存中获取通讯录accesstoken
		GetAccessTokenDto dto = new GetAccessTokenDto();
		dto.setCorpId(wechatWorkProperties.getCorpId());
		dto.setSecret(wechatWorkProperties.getContactConfig().getSecret());
		dto.setAccessTokenType(AccessTokenTypeEnum.CONTACT);
		return this.getAccessToken(dto);
	}

	/**
	 * 获取客户联系token
	 */
	public String getExternalContactAccessToken() {
		// 从缓存中获取通讯录accesstoken
		GetAccessTokenDto dto = new GetAccessTokenDto();
		dto.setCorpId(wechatWorkProperties.getCorpId());
		dto.setSecret(wechatWorkProperties.getExternalContactConfig().getSecret());
		dto.setAccessTokenType(AccessTokenTypeEnum.EXTERNALCONTACT);
		return this.getAccessToken(dto);
	}

}
