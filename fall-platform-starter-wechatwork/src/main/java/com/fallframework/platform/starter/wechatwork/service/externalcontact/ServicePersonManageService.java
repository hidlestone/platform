package com.fallframework.platform.starter.wechatwork.service.externalcontact;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.httpclient.util.HttpClientUtil;
import com.fallframework.platform.starter.wechatwork.constant.WxworkStarterConstant;
import com.fallframework.platform.starter.wechatwork.util.AccessTokenUtil;
import com.fallframework.platform.starter.wechatwork.util.HttpResponseUtil;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 客户联系-企业服务人员管理
 *
 * @author zhuangpf
 */
@Service
public class ServicePersonManageService {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	/**
	 * 获取配置了客户联系功能的成员列表
	 */
	public ResponseResult getFollowUserList() {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getExternalContactAccessToken();
		String url = WxworkStarterConstant.URL_EXTERNALCONTACT_GET_FOLLOW_USER_LIST.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.get(url);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 配置客户联系「联系我」方式
	 */
	public ResponseResult addContactWay(Map<String, Object> params) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getExternalContactAccessToken();
		String url = WxworkStarterConstant.URL_EXTERNALCONTACT_ADD_CONTACT_WAY.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, params);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 获取企业已配置的「联系我」方式
	 */
	public ResponseResult getContactWay(Map<String, Object> params) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getExternalContactAccessToken();
		String url = WxworkStarterConstant.URL_EXTERNALCONTACT_GET_CONTACT_WAY.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, params);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 获取企业已配置的「联系我」列表
	 */
	public ResponseResult listContactWay(Map<String, Object> params) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getExternalContactAccessToken();
		String url = WxworkStarterConstant.URL_EXTERNALCONTACT_LIST_CONTACT_WAY.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, params);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 更新企业已配置的「联系我」方式
	 */
	public ResponseResult updateContactWay(Map<String, Object> params) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getExternalContactAccessToken();
		String url = WxworkStarterConstant.URL_EXTERNALCONTACT_UPDATE_CONTACT_WAY.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, params);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 删除企业已配置的「联系我」方式
	 */
	public ResponseResult delContactWay(Map<String, Object> params) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getExternalContactAccessToken();
		String url = WxworkStarterConstant.URL_EXTERNALCONTACT_DEL_CONTACT_WAY.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, params);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 删除企业已配置的「联系我」方式
	 */
	public ResponseResult closeTempChat(Map<String, Object> params) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getExternalContactAccessToken();
		String url = WxworkStarterConstant.URL_EXTERNALCONTACT_CLOSE_TEMP_CHAT.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, params);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

}
