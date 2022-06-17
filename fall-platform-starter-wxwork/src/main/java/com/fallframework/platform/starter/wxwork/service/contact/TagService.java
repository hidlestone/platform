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
 * 通讯录管理-标签管理
 *
 * @author zhuangpf
 */
@Service
public class TagService {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	/**
	 * 创建标签
	 */
	public ResponseResult create(Map<String, Object> tagMap) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_TAG_CREATE.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, tagMap);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 更新标签名字
	 */
	public ResponseResult update(Map<String, Object> tagMap) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_TAG_UPDATE.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, tagMap);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 删除标签
	 */
	public ResponseResult delete(String tagid) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_TAG_DELETE.replace("ACCESS_TOKEN", access_token).replace("TAGID", tagid);
		HttpResponse httpResponse = HttpClientUtil.get(url);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 获取标签成员
	 */
	public ResponseResult get(String tagid) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_TAG_GET.replace("ACCESS_TOKEN", access_token).replace("TAGID", tagid);
		HttpResponse httpResponse = HttpClientUtil.get(url);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 增加标签成员
	 */
	public ResponseResult addTagUsers(Map<String, Object> tagMap) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_TAG_ADDTAGUSERS.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, tagMap);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 删除标签成员
	 */
	public ResponseResult delTagUsers(Map<String, Object> tagMap) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_TAG_DELTAGUSERS.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, tagMap);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 获取标签成员
	 */
	public ResponseResult list(String tagid) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_TAG_LIST.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.get(url);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

}
