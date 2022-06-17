package com.fallframework.platform.starter.wxwork.util;

import com.alibaba.fastjson.JSON;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.httpclient.util.HttpClientUtil;
import com.fallframework.platform.starter.wxwork.entity.WXWorkBaseEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author zhuangpf
 */
public class HttpResponseUtil {

	public static ResponseResult httpResponse2ResponseResult(HttpResponse httpResponse) {
		WXWorkBaseEntity baseEntity = null;
		try {
			if (200 != httpResponse.getStatusLine().getStatusCode()) {
				return ResponseResult.fail(String.valueOf(httpResponse.getStatusLine().getStatusCode()), httpResponse.getStatusLine().getReasonPhrase());
			}
			String content = EntityUtils.toString(httpResponse.getEntity(), HttpClientUtil.getEncoding());
			baseEntity = JSON.parseObject(content, WXWorkBaseEntity.class);
			if (0 != baseEntity.getErrcode()) {
				return ResponseResult.fail(String.valueOf(baseEntity.getErrcode()), baseEntity.getErrmsg());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseResult.fail(String.valueOf(baseEntity.getErrcode()), baseEntity.getErrmsg());
	}

}
