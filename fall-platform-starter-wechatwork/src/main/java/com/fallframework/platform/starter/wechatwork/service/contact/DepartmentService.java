package com.fallframework.platform.starter.wechatwork.service.contact;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.httpclient.util.HttpClientUtil;
import com.fallframework.platform.starter.wechatwork.constant.WechatWorkStarterConstant;
import com.fallframework.platform.starter.wechatwork.util.AccessTokenUtil;
import com.fallframework.platform.starter.wechatwork.util.HttpResponseUtil;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 通讯录管理-部门管理
 *
 * @author zhuangpf
 */
@Service
public class DepartmentService {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	/**
	 * 创建部门
	 */
	public ResponseResult create(Map<String, Object> deptMap) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WechatWorkStarterConstant.URL_DEPARTMENT_CREATE.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, deptMap);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 创建部门
	 */
	public ResponseResult update(Map<String, Object> deptMap) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WechatWorkStarterConstant.URL_DEPARTMENT_UPDATE.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, deptMap);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 删除部门
	 */
	public ResponseResult delete(String id) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WechatWorkStarterConstant.URL_DEPARTMENT_DELETE.replace("ACCESS_TOKEN", access_token).replace("ID", id);
		HttpResponse httpResponse = HttpClientUtil.get(url);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 获取部门列表
	 */
	public ResponseResult list(String id) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WechatWorkStarterConstant.URL_DEPARTMENT_LIST.replace("ACCESS_TOKEN", access_token).replace("ID", id);
		HttpResponse httpResponse = HttpClientUtil.get(url);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 获取子部门ID列表
	 */
	public ResponseResult simpleList(String id) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WechatWorkStarterConstant.URL_DEPARTMENT_SIMPLELIST.replace("ACCESS_TOKEN", access_token).replace("ID", id);
		HttpResponse httpResponse = HttpClientUtil.get(url);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 获取单个部门详情
	 */
	public ResponseResult get(String id) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WechatWorkStarterConstant.URL_DEPARTMENT_GET.replace("ACCESS_TOKEN", access_token).replace("ID", id);
		HttpResponse httpResponse = HttpClientUtil.get(url);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

}
