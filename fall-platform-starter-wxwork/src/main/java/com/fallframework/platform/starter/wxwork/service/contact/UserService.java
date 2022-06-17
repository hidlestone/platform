package com.fallframework.platform.starter.wxwork.service.contact;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.httpclient.util.HttpClientUtil;
import com.fallframework.platform.starter.wxwork.constant.WxworkStarterConstant;
import com.fallframework.platform.starter.wxwork.entity.User;
import com.fallframework.platform.starter.wxwork.util.AccessTokenUtil;
import com.fallframework.platform.starter.wxwork.util.HttpResponseUtil;
import org.apache.http.HttpResponse;
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
	private AccessTokenUtil accessTokenUtil;

	/**
	 * 创建用户
	 *
	 * @param userMap 用户信息
	 * @return 是否创建成功
	 */
	public ResponseResult create(Map<String, Object> userMap) throws IOException {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_USER_CREATE.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, userMap);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 读取用户
	 *
	 * @param userId 用户ID
	 * @return 用户信息
	 */
	public ResponseResult<User> get(String userId) throws IOException {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_USER_GET.replace("ACCESS_TOKEN", access_token).replace("USERID", userId);
		HttpResponse httpResponse = HttpClientUtil.get(url);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 更新成员
	 */
	public ResponseResult update(Map<String, Object> userMap) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_USER_UPDATE.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, userMap);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 删除成员
	 */
	public ResponseResult delete(String userid) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_USER_DELETE.replace("ACCESS_TOKEN", access_token).replace("USERID", userid);
		HttpResponse httpResponse = HttpClientUtil.get(url);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 批量删除成员
	 */
	public ResponseResult batchdelete(Map<String, Object> params) {
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_USER_DELETE.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, params);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 获取部门成员
	 */
	public ResponseResult simplelist(String department_id, String fetch_child) {
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_USER_SIMPLELIST.replace("ACCESS_TOKEN", access_token).replace("DEPARTMENT_ID", department_id).replace("FETCH_CHILD", fetch_child);
		HttpResponse httpResponse = HttpClientUtil.get(url);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 获取部门成员详情
	 */
	public ResponseResult list(String department_id, String fetch_child) {
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_USER_LIST.replace("ACCESS_TOKEN", access_token).replace("DEPARTMENT_ID", department_id).replace("FETCH_CHILD", fetch_child);
		HttpResponse httpResponse = HttpClientUtil.get(url);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * userid与openid互换
	 */
	public ResponseResult convertToOpenid(Map<String, Object> params) {
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_USER_CONVERT_TO_OPENID.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, params);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 二次验证
	 */
	public ResponseResult authsucc(String userid) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_USER_AUTHSUCC.replace("ACCESS_TOKEN", access_token).replace("USERID", userid);
		HttpResponse httpResponse = HttpClientUtil.get(url);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 邀请成员
	 */
	public ResponseResult invite(Map<String, Object> params) {
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_BATCH_INVITE.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, params);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 获取加入企业二维码
	 */
	public ResponseResult getJoinQrcode(String size_type) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_CORP_GET_JOIN_QRCODE.replace("ACCESS_TOKEN", access_token).replace("SIZE_TYPE", size_type);
		HttpResponse httpResponse = HttpClientUtil.get(url);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	public ResponseResult getUserid(Map<String, Object> params) {
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_USER_GETUSERID.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, params);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

}
