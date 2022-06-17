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
 * @author zhuangpf
 */
@Service
public class SynBatchService {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	/**
	 * 增量更新成员
	 */
	public ResponseResult syncUser(Map<String, Object> params) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_BATCH_SYNCUSER.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, params);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 全量覆盖成员
	 */
	public ResponseResult replaceUser(Map<String, Object> params) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_BATCH_REPLACEUSER.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, params);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 全量覆盖部门
	 */
	public ResponseResult replaceParty(Map<String, Object> params) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_BATCH_REPLACEPARTY.replace("ACCESS_TOKEN", access_token);
		HttpResponse httpResponse = HttpClientUtil.post(url, params);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

	/**
	 * 获取异步任务结果
	 */
	public ResponseResult replaceParty(String jobid) {
		// 从缓存中获取通讯录accesstoken
		String access_token = accessTokenUtil.getContactAccessToken();
		String url = WxworkStarterConstant.URL_BATCH_GETRESULT.replace("ACCESS_TOKEN", access_token).replace("JOBID", jobid);
		HttpResponse httpResponse = HttpClientUtil.get(url);
		// 响应信息
		ResponseResult responseResult = HttpResponseUtil.httpResponse2ResponseResult(httpResponse);
		return responseResult;
	}

}
