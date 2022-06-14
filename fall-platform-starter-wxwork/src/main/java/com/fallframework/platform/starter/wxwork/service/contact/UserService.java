package com.fallframework.platform.starter.wxwork.service.contact;

import com.alibaba.fastjson.JSON;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.wxwork.config.WxCpProperties;
import com.fallframework.platform.starter.wxwork.constant.WxworkStarterConstant;
import com.fallframework.platform.starter.wxwork.dto.GetAccessTokenDto;
import com.fallframework.platform.starter.wxwork.entity.User;
import com.fallframework.platform.starter.wxwork.model.AccessTokenTypeEnum;
import com.fallframework.platform.starter.wxwork.model.TokenResponse;
import com.fallframework.platform.starter.wxwork.service.token.TokenService;
import com.fallframework.platform.starter.wxwork.util.AccessTokenUtil;
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
	private AccessTokenUtil accessTokenUtil;
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
		if (200 != response.code()) {
			return ResponseResult.fail(String.valueOf(response.code()), response.message());
		}
		TokenResponse tokenResponse = JSON.parseObject(response.body().string(), TokenResponse.class);
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
	public ResponseResult<User> get(String userId) throws IOException {
		// 从缓存中获取通讯录accesstoken
		String access_token = getAccessToken();
		String url = WxworkStarterConstant.URL_USER_GET.replace("ACCESS_TOKEN", access_token).replace("USERID", userId);
		Response response = new OkHttpClient().newCall(new Request.Builder().url(url).get().build()).execute();
		if (200 != response.code()) {
			return ResponseResult.fail(String.valueOf(response.code()), response.message());
		}
		User user = JSON.parseObject(response.body().string(), User.class);
		if (0 != user.getErrcode()) {
			return ResponseResult.fail(String.valueOf(user.getErrcode()), user.getErrmsg());
		}
		return ResponseResult.success(user);
	}

	/**
	 * 获取token
	 */
	public String getAccessToken() throws IOException {
		// 从缓存中获取通讯录accesstoken
		GetAccessTokenDto dto = new GetAccessTokenDto();
		dto.setCorpId(wxCpProperties.getCorpId());
		dto.setSecret(wxCpProperties.getContactConfig().getSecret());
		dto.setAccessTokenType(AccessTokenTypeEnum.CONTACT);
		return accessTokenUtil.getAccessToken(dto);
	}

}
