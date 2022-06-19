package com.fallframework.platform.starter.wxwork.service.externalcontact;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.httpclient.util.HttpClientUtil;
import com.fallframework.platform.starter.wxwork.constant.WxworkStarterConstant;
import com.fallframework.platform.starter.wxwork.util.AccessTokenUtil;
import com.fallframework.platform.starter.wxwork.util.HttpResponseUtil;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 客户联系-客户管理
 *
 * @author zhuangpf
 */
@Service
public class ClientManageService {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	/**
	 * 获取客户列表
	 */
	public ResponseResult list(String userid) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getExternalContactAccessToken();
		String url = WxworkStarterConstant.URL_EXTERNALCONTACT_LIST.replace("ACCESS_TOKEN", access_token).replace("USERID", userid);
		HttpResponse httpResponse = HttpClientUtil.get(url);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 获取客户详情
	 */
	public ResponseResult get(String userid) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getExternalContactAccessToken();
		String url = WxworkStarterConstant.URL_EXTERNALCONTACT_GET.replace("ACCESS_TOKEN", access_token).replace("USERID", userid);
		HttpResponse httpResponse = HttpClientUtil.get(url);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 批量获取客户详情
	 */
	public ResponseResult getByUser(Map<String, Object> params) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getExternalContactAccessToken();
		String url = WxworkStarterConstant.URL_EXTERNALCONTACT_GET_BY_USER.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, params);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}
	
	

}
