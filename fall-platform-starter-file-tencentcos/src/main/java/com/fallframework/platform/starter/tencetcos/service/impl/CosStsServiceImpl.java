package com.fallframework.platform.starter.tencetcos.service.impl;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.tencetcos.cloud.CosStsClient;
import com.fallframework.platform.starter.tencetcos.cloud.Response;
import com.fallframework.platform.starter.tencetcos.config.COSConfig;
import com.fallframework.platform.starter.tencetcos.service.CosStsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.io.IOException;
import java.util.TreeMap;

/**
 * @author zhuangpf
 */
@Service
public class CosStsServiceImpl implements CosStsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CosStsServiceImpl.class);

	@Addressing
	private COSConfig cosConfig;

	/**
	 * 基本的临时密钥申请示例，适合对一个桶内的一批对象路径，统一授予一批操作权限
	 */
	@Override
	public ResponseResult<Response> getCredential() {
		TreeMap<String, Object> config = new TreeMap<String, Object>();
		try {
			// 云 api 密钥 SecretId
			config.put("secretId", cosConfig.getSecretId());
			// 云 api 密钥 SecretKey
			config.put("secretKey", cosConfig.getSecretKey());

			// 临时密钥有效时长，单位是秒
			config.put("durationSeconds", 1800);
			// 换成你的 bucket
			config.put("bucket", cosConfig.getBucketName());
			// 换成 bucket 所在地区
			config.put("region", cosConfig.getRegionName());

			// 可以通过 allowPrefixes 指定前缀数组, 例子： a.jpg 或者 a/* 或者 * (使用通配符*存在重大安全风险, 请谨慎评估使用)
			config.put("allowPrefixes", new String[]{
					"*"
			});

			// 密钥的权限列表。简单上传和分片需要以下的权限，其他权限列表请看 https://cloud.tencent.com/document/product/436/31923
			String[] allowActions = new String[]{
					// 简单上传
					"name/cos:PutObject",
					"name/cos:PostObject",
					// 分片上传
					"name/cos:InitiateMultipartUpload",
					"name/cos:ListMultipartUploads",
					"name/cos:ListParts",
					"name/cos:UploadPart",
					"name/cos:CompleteMultipartUpload"
			};
			config.put("allowActions", allowActions);

			Response response = CosStsClient.getCredential(config);
			System.out.println(response.credentials.tmpSecretId);
			System.out.println(response.credentials.tmpSecretKey);
			System.out.println(response.credentials.sessionToken);
			return ResponseResult.success(response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("no valid secret !");
		}
	}

}
