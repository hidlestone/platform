package com.fallframework.platform.starter.wxwork.service.contact;

import com.alibaba.fastjson.JSON;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.wxwork.config.WxCpProperties;
import com.fallframework.platform.starter.wxwork.constant.WxworkStarterConstant;
import com.fallframework.platform.starter.wxwork.dto.GetAccessTokenDto;
import com.fallframework.platform.starter.wxwork.model.AccessTokenTypeEnum;
import com.fallframework.platform.starter.wxwork.model.TokenResponse;
import com.fallframework.platform.starter.wxwork.service.token.TokenService;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * 通讯录管理-成员管理
 *
 * @author zhuangpf
 */
@Service
public class UserService {

	@Autowired
	private WxCpProperties wxCpProperties;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 创建用户
	 *
	 * @param userMap 用户信息
	 * @return 是否创建成功
	 */
	public ResponseResult create(Map<String, Object> userMap) throws IOException {
		// 从缓存中获取通讯录accesstoken
		String access_token = getAccessToken();
		String url = WxworkStarterConstant.URL_USER_CREATE.replace("ACCESS_TOKEN", access_token);
		// 请求
		RequestBody requestBody = FormBody.create(
				MediaType.parse("application/json"), JSON.toJSONString(userMap));
		Response response = new OkHttpClient().newCall(new Request.Builder().url(url).post(requestBody).build()).execute();
		TokenResponse tokenResponse = JSON.parseObject(response.body().string(), TokenResponse.class);
		if (200 != response.code()) {
			return ResponseResult.fail(JSON.toJSONString(response));
		}
		if (0 != tokenResponse.getErrcode()) {
			return ResponseResult.fail(String.valueOf(tokenResponse.getErrcode()), tokenResponse.getErrmsg());
		}
		return ResponseResult.success();
	}

	/**
	 * 读取用户
	 *
	 * @param userId 用户ID
	 * @return 用户信息
	 */
	public ResponseResult get(String userId) throws IOException {
		// 从缓存中获取通讯录accesstoken
		String access_token = getAccessToken();
		String url = WxworkStarterConstant.URL_USER_GET.replace("ACCESS_TOKEN", access_token).replace("USERID", userId);
		Response response = new OkHttpClient().newCall(new Request.Builder().url(url).get().build()).execute();
		if (200 != response.code()) {
			return ResponseResult.fail(JSON.toJSONString(response));
		}
		return ResponseResult.success(response.body());
	}

	/**
	 * 获取token
	 */
	public String getAccessToken() throws IOException {
		// 从缓存中获取通讯录accesstoken
		String access_token = (String) redisUtil.hget(WxworkStarterConstant.CACHE_KEY_ACCESS_TOKEN, AccessTokenTypeEnum.CONTACT.name());
		if (StringUtils.isEmpty(access_token)) {
			GetAccessTokenDto dto = new GetAccessTokenDto();
			dto.setCorpId(wxCpProperties.getCorpId());
			dto.setSecret(wxCpProperties.getContactConfig().getSecret());
			dto.setAccessTokenType(AccessTokenTypeEnum.CONTACT);
			access_token = tokenService.gettoken(dto).getData().getAccess_token();
		}
		return access_token;
	}

}
