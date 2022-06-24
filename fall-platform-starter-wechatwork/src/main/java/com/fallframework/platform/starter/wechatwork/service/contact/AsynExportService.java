package com.fallframework.platform.starter.wechatwork.service.contact;

import com.fallframework.platform.starter.httpclient.util.HttpClientUtil;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuangpf
 */
@Service
public class AsynExportService {

	private static final String URL_EXPORT_USER = "https://qyapi.weixin.qq.com/cgi-bin/export/user?access_token=ACCESS_TOKEN";

	public static void main(String[] args) throws IOException {
		String encodingAeskey = "EAp3uYDnB9bVCbxUQZyARZGp5FvrUK4I95CJ23F6GaF";
		String accessToken = "79mxUyUgozr52vYt_5RX4IYY-APVDq97_NnWqWKRsuz7GYZESjEK5GLSZACP7RdMHgh-6PU1QP6WOLScbTA6H5Sbk8-B3dOlli7W_jjE2NhdNQ8FNWJh2CFtxHoygSmSQ-VTbrPChznssyOX5JSdCQ1hNVpXuNxiR5wQ8oOw6-ujFRZDsotH_Fo6fjKEIVssv72rF9VzXSKt9MKfVwIrvA";

		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("encoding_aeskey", encodingAeskey);
		dataMap.put("block_size", "1000000");
		String url = URL_EXPORT_USER.replace("ACCESS_TOKEN", accessToken);

		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		HttpResponse post = HttpClientUtil.post(url, headers, dataMap);

		System.out.println(post);
		System.out.println(EntityUtils.toString(post.getEntity(), "UTF-8"));
	}

}
