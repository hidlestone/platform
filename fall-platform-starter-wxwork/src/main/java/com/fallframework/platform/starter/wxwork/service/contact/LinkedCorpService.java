package com.fallframework.platform.starter.wxwork.service.contact;

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
 * 通讯录管理-互联企业
 *
 * @author zhuangpf
 */
@Service
public class LinkedCorpService {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	/**
	 * 获取应用的可见范围
	 */
	public ResponseResult getPermList(Map<String, Object> params) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_LINKEDCORP_AGENT_GET_PERM_LIST.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, params);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 获取互联企业成员详细信息
	 */
	public ResponseResult getUser(Map<String, Object> params) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_LINKEDCORP_USER_GET.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, params);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 获取互联企业部门成员
	 */
	public ResponseResult userSimpleList(Map<String, Object> params) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_LINKEDCORP_USER_SIMPLELIST.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, params);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 获取互联企业部门成员详情
	 */
	public ResponseResult userList(Map<String, Object> params) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_LINKEDCORP_USER_LIST.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, params);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 获取互联企业部门成员详情
	 */
	public ResponseResult departmentList(Map<String, Object> params) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_LINKEDCORP_DEPARTMENT_LIST.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, params);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}


}
