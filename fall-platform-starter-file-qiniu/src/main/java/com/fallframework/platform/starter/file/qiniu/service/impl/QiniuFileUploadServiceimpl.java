package com.fallframework.platform.starter.file.qiniu.service.impl;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.file.qiniu.config.QiniuPropertyResource;
import com.fallframework.platform.starter.file.qiniu.service.QiniuFileUploadService;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;

/**
 * 客户端上传 & 服务端上传
 *
 * @author zhuangpf
 */
@Service
public class QiniuFileUploadServiceimpl implements QiniuFileUploadService {

	private static Logger LOGGER = LoggerFactory.getLogger(QiniuFileUploadServiceimpl.class);

	@Autowired
	private QiniuPropertyResource qiniuPropertyResource;

	/**
	 * 获取简单上传的凭证<br/>
	 * 客户端（移动端或者Web端）上传文件的时候，需要从客户自己的业务服务器获取上传凭证，而这些上传凭证是通过服务端的SDK来生成的，然后通过客户自己的业务API分发给客户端使用。
	 */
	@Override
	public String getUploadToken() {
		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		return auth.uploadToken(qiniuPropertyResource.getBucket());
	}

	/**
	 * 覆盖上传的凭证<br/>
	 * 需要想进行覆盖的文件名称，这个文件名称同时可是客户端上传代码中指定的文件名，两者必须一致。
	 */
	public String getCoverUploadToken(String key) {
		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		String upToken = auth.uploadToken(qiniuPropertyResource.getBucket(), key);
		return upToken;
	}

	/**
	 * 自定义上传回复的凭证<br/>
	 * 有时候我们希望能自定义这个返回的JSON格式的内容，可以通过设置returnBody参数来实现，在returnBody中，我们可以使用七牛支持的魔法变量和自定义变量。<br/>
	 * 则文件上传到七牛之后，收到的回复内容如下：<br/>
	 * {"key":"qiniu.jpg","hash":"Ftgm-CkWePC9fzMBTRNmPMhGBcSV","bucket":"if-bc","fsize":39335}
	 */
	public String getCustomUploadToken() {
		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		StringMap putPolicy = new StringMap();
		putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
		long expireSeconds = 3600;
		return auth.uploadToken(qiniuPropertyResource.getBucket(), null, expireSeconds, putPolicy);
	}

	/**
	 * 上传后回调的凭证<br/>
	 * 常情况下，我们建议使用application/json格式来设置callbackBody，保持数据格式的统一性。实际情况下，
	 * callbackBody也支持application/x-www-form-urlencoded格式来组织内容，这个主要看业务服务器在接收到callbackBody的内容时如何解析。
	 */
	public String getCallBackUploadToken() {
		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		StringMap putPolicy = new StringMap();
		putPolicy.put("callbackUrl", "http://api.example.com/qiniu/upload/callback");
		putPolicy.put("callbackBody", "key=$(key)&hash=$(etag)&bucket=$(bucket)&fsize=$(fsize)");
		long expireSeconds = 3600;
		return auth.uploadToken(qiniuPropertyResource.getBucket(), null, expireSeconds, putPolicy);
	}

	/**
	 * 带数据处理的凭证<br/>
	 * 存储服务支持在文件上传到七牛之后，立即对其进行多种指令的数据处理，这个只需要在生成的上传凭证中指定相关的处理参数即可。
	 */
	public String getDataDealUploadToken() {
		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		StringMap putPolicy = new StringMap();
		//数据处理指令，支持多个指令
		String saveMp4Entry = String.format("%s:avthumb_test_target.mp4", qiniuPropertyResource.getBucket());
		String saveJpgEntry = String.format("%s:vframe_test_target.jpg", qiniuPropertyResource.getBucket());
		String avthumbMp4Fop = String.format("avthumb/mp4|saveas/%s", UrlSafeBase64.encodeToString(saveMp4Entry));
		String vframeJpgFop = String.format("vframe/jpg/offset/1|saveas/%s", UrlSafeBase64.encodeToString(saveJpgEntry));
		//将多个数据处理指令拼接起来
		String persistentOpfs = StringUtils.join(new String[]{
				avthumbMp4Fop, vframeJpgFop
		}, ";");
		putPolicy.put("persistentOps", persistentOpfs);
		// 数据处理队列名称，必填
		putPolicy.put("persistentPipeline", "mps-pipe1");
		// 数据处理完成结果通知地址
		putPolicy.put("persistentNotifyUrl", "http://api.example.com/qiniu/pfop/notify");
		long expireSeconds = 3600;
		return auth.uploadToken(qiniuPropertyResource.getBucket(), null, expireSeconds, putPolicy);
	}

	/**
	 * 文件上传
	 */
	public ResponseResult<DefaultPutRet> uploadLocalFile() {
		// 构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.region0());
		UploadManager uploadManager = new UploadManager(cfg);
		//...生成上传凭证，然后准备上传
		String accessKey = "your access key";
		String secretKey = "your secret key";
		String bucket = "your bucket name";
		//如果是Windows情况下，格式是 D:\\qiniu\\test.png
		String localFilePath = "/home/qiniu/test.png";
		//默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = null;

		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		String upToken = auth.uploadToken(bucket);
		DefaultPutRet putRet = null;
		try {
			Response response = uploadManager.put(localFilePath, key, upToken);
			//解析上传成功的结果
			putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			System.out.println(putRet.key);
			System.out.println(putRet.hash);
		} catch (QiniuException ex) {
			Response r = ex.response;
			System.err.println(r.toString());
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException ex2) {
				//ignore
			}
		}
		return ResponseResult.success(putRet);
	}

	/**
	 * 字节数组上传<br/>
	 * 可以支持将内存中的字节数组上传到空间中。
	 */
	public ResponseResult<DefaultPutRet> uploadByteArray() {
		Configuration cfg = new Configuration(Region.region0());
		UploadManager uploadManager = new UploadManager(cfg);
		// 默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = null;
		DefaultPutRet putRet = null;
		try {
			byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
			Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
			String upToken = auth.uploadToken(qiniuPropertyResource.getBucket());
			try {
				Response response = uploadManager.put(uploadBytes, key, upToken);
				//解析上传成功的结果
				putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
				System.out.println(putRet.key);
				System.out.println(putRet.hash);
			} catch (QiniuException ex) {
				Response r = ex.response;
				System.err.println(r.toString());
				try {
					System.err.println(r.bodyString());
				} catch (QiniuException ex2) {
					//ignore
				}
			}
		} catch (UnsupportedEncodingException ex) {
			//ignore
		}
		return ResponseResult.success(putRet);
	}

	/**
	 * 数据流上传<br/>
	 * InputStream对象的上传，适用于所有的InputStream子类。这里的ByteInputStream只用于演示目的，实际用法根据情况而定。
	 */
	public ResponseResult<DefaultPutRet> uploadDataStream() {
		Configuration cfg = new Configuration(Region.region0());
		UploadManager uploadManager = new UploadManager(cfg);
		//默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = null;
		DefaultPutRet putRet = null;
		try {
			byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
			ByteArrayInputStream byteInputStream = new ByteArrayInputStream(uploadBytes);
			Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
			String upToken = auth.uploadToken(qiniuPropertyResource.getBucket());
			try {
				Response response = uploadManager.put(byteInputStream, key, upToken, null, null);
				//解析上传成功的结果
				putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
				System.out.println(putRet.key);
				System.out.println(putRet.hash);
			} catch (QiniuException ex) {
				Response r = ex.response;
				System.err.println(r.toString());
				try {
					System.err.println(r.bodyString());
				} catch (QiniuException ex2) {
					//ignore
				}
			}
		} catch (UnsupportedEncodingException ex) {
			//ignore
		}
		return ResponseResult.success(putRet);
	}

	/**
	 * 断点续上传<br/>
	 * 断点续传是在分片上传的基础上实现。SDK 内置两种上传方式：表单上传和分片上传。
	 * 表单上传使用一个 HTTP POST 请求完成文件的上传，因此比较适合较小的文件。相比而言，分片上传比较适合上传比较大的文件（例如数百 MB 或更大）。
	 */
	public ResponseResult<DefaultPutRet> uploadFromBreakpoint() {
		// 构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.region0());
		cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
		cfg.resumableUploadMaxConcurrentTaskCount = 2;  // 设置分片上传并发，1：采用同步上传；大于1：采用并发上传

		// 如果是Windows情况下，格式是 D:\\qiniu\\test.png
		String localFilePath = "/home/qiniu/test.mp4";
		// 默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = null;
		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		String upToken = auth.uploadToken(qiniuPropertyResource.getBucket());
		String localTempDir = Paths.get(System.getenv("java.io.tmpdir"), qiniuPropertyResource.getBucket()).toString();
		DefaultPutRet putRet = null;
		try {
			//设置断点续传文件进度保存目录
			FileRecorder fileRecorder = new FileRecorder(localTempDir);
			UploadManager uploadManager = new UploadManager(cfg, fileRecorder);
			try {
				Response response = uploadManager.put(localFilePath, key, upToken);
				//解析上传成功的结果
				putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
				System.out.println(putRet.key);
				System.out.println(putRet.hash);
			} catch (QiniuException ex) {
				Response r = ex.response;
				System.err.println(r.toString());
				try {
					System.err.println(r.bodyString());
				} catch (QiniuException ex2) {
					//ignore
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return ResponseResult.success(putRet);
	}

	/**
	 * 自定义参数上传
	 */
	public ResponseResult<Response> uploadCustomData() {
		// 构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.region0());
		UploadManager uploadManager = new UploadManager(cfg);
		// 如果是Windows情况下，格式是 D:\\qiniu\\test.png
		String localFilePath = "/home/qiniu/test.mp4";
		// 默认不指定key的情况下，以文件内容的hash值作为文件名
		// 设置上传后的文件名称
		String key = "qiniu_test.jpg";
		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());

		//上传自定义参数，自定义参数名称需要以 x:开头
		StringMap params = new StringMap();
		params.put("x:fname", "123.jpg");
		params.put("x:age", 20);
		//上传策略
		StringMap policy = new StringMap();
		//自定义上传后返回内容，返回自定义参数，需要设置 x:参数名称，注意下面
		policy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"fname\":\"$(x:fname)\",\"age\",$(x:age)}");
		//生成上传token
		String upToken = auth.uploadToken(qiniuPropertyResource.getBucket(), key, 3600, policy);
		Response response = null;
		try {
			response = uploadManager.put(localFilePath, key, upToken, params, null, false);
			System.out.println(response.bodyString());
		} catch (QiniuException ex) {
			Response r = ex.response;
			System.err.println(r.toString());
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException ex2) {
				//ignore
			}
		}
		return ResponseResult.success(response);
	}

	/*
	 * 解析自定义恢复内容
	 * 有些情况下，七牛返回给上传端的内容不是默认的hash和key形式，这种情况下，可能出现在自定义returnBody或者自定义了callbackBody的情况下，
	 * 前者一般是服务端直传的场景，而后者则是接受上传回调的场景，这两种场景之下，都涉及到需要将自定义的回复内容解析为Java的类对象，
	 * 一般建议在交互过程中，都采用JSON的方式，这样处理起来方法比较一致，而且JSON的方法最通用。
	 *
	 * 遇到诸如自定义returnBody的情况：
	 * putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
	 * 或者是自定义了callbackBody的情况：
	 * putPolicy.put("callbackBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
	 *
	 * 需要自己定义了对应的类，例如：
	 * class MyPutRet {
	 *     public String key;
	 *     public String hash;
	 *     public String bucket;
	 *     public long fsize;
	 * }
	 * */

	/**
	 * 业务服务器验证存储服务回调<br/>
	 * 在上传策略里面设置了上传回调相关参数的时候，七牛在文件上传到服务器之后，会主动地向callbackUrl发送POST请求的回调，
	 * 回调的内容为callbackBody模版所定义的内容，如果这个模版里面引用了魔法变量或者自定义变量，那么这些变量会被自动填充对应的值，然后在发送给业务服务器。
	 * <p>
	 * 业务服务器在收到来自七牛的回调请求的时候，可以根据请求头部的Authorization字段来进行验证，查看该请求是否是来自七牛的未经篡改的请求。具体可以参考七牛的回调鉴权。
	 */
	public ResponseResult validCallback() {
		// 回调地址
		String callbackUrl = "http://api.example.com/qiniu/callback";
		// 定义回调内容的组织格式，与上传策略中的callbackBodyType要保持一致
		// String callbackBodyType = "application/x-www-form-urlencoded"; //回调鉴权的签名包括请求内容callbackBody
		String callbackBodyType = "application/json";//回调鉴权的签名不包括请求内容

		/**
		 * 这两个参数根据实际所使用的HTTP框架进行获取
		 */
		//通过获取请求的HTTP头部Authorization字段获得
		String callbackAuthHeader = "xxx";
		//通过读取回调POST请求体获得，不要设置为null
		byte[] callbackBody = null;
		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		//检查是否为合法的回调请求
		boolean validCallback = auth.isValidCallback(callbackAuthHeader, callbackUrl, callbackBody, callbackBodyType);
		if (validCallback) {
			//继续处理其他业务逻辑
		} else {
			//这是哪里的请求，被劫持，篡改了吧？
		}
		return ResponseResult.success();
	}
	
}
