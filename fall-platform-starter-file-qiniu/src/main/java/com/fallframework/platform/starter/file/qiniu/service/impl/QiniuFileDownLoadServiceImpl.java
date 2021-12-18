package com.fallframework.platform.starter.file.qiniu.service.impl;

import com.fallframework.platform.starter.file.qiniu.service.QiniuFileDownLoadService;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.DownloadUrl;
import com.qiniu.util.Auth;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 文件下载分为公开空间的文件下载和私有空间的文件下载。
 *
 * @author zhuangpf
 */
public class QiniuFileDownLoadServiceImpl implements QiniuFileDownLoadService {

	/**
	 * 手动拼接方式<br/>
	 * 对于公开空间，其访问的链接主要是将空间绑定的域名拼接上空间里面的文件名即可访问，标准情况下需要在拼接链接之前，
	 * 将文件名进行urlencode以兼容不同的字符。如果有其他访问处理需求，在文件名之后继续拼接即可。
	 */
	public String downPublicSpaceByJoint() throws UnsupportedEncodingException {
		String fileName = "公司/存储/qiniu.jpg";
		String domainOfBucket = "http://devtools.qiniu.com";
		String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
		String finalUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
		System.out.println(finalUrl);
		return finalUrl;
	}

	/**
	 * sdk 自动生成方式<br/>
	 * sdk 封装了下载 URL 的生成，只需要输入下载所需参数即可。已支持多媒体处理命令等。
	 */
	public String downPublicSpaceBySdk() throws QiniuException {
		// domain   下载 domain, eg: qiniu.com【必须】
		// useHttps 是否使用 https【必须】
		// key      下载资源在七牛云存储的 key【必须】
		String domain = "";
		Boolean useHttps = true;
		String key = "";
		String attname = "";
		String fop = "";
		String style = "", styleSeparator = "", styleParam = "";
		DownloadUrl url = new DownloadUrl(domain, useHttps, key);
		url.setAttname(attname) // 配置 attname
				.setFop(fop) // 配置 fop
				.setStyle(style, styleSeparator, styleParam); // 配置 style
		String urlString = url.buildURL();
		System.out.println(urlString);
		return urlString;
	}

	/**
	 * 手动拼接方式<br/>
	 * 对于私有空间，首先需要按照公开空间的文件访问方式构建对应的公开空间访问链接，然后再对这个链接进行私有授权签名。
	 */
	public String downPrivateSpaceByJoint() throws UnsupportedEncodingException {
		String fileName = "公司/存储/qiniu.jpg";
		String domainOfBucket = "http://devtools.qiniu.com";
		String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
		String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
		String accessKey = "your access key";
		String secretKey = "your secret key";
		Auth auth = Auth.create(accessKey, secretKey);
		long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
		String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
		System.out.println(finalUrl);
		return finalUrl;
	}

	/**
	 * sdk 自动生成方式<br/>
	 * sdk 封装了下载 URL 的生成，只需要输入下载所需参数即可。已支持多媒体处理命令，过期时间 e 和 签名 token 等。
	 */
	public String downPrivteSpaceBySdk() throws QiniuException {
		// domain   下载 domain, eg: qiniu.com【必须】
		// useHttps 是否使用 https【必须】
		// key      下载资源在七牛云存储的 key【必须】
		String domain = "";
		Boolean useHttps = true;
		String key = "";
		String attname = "";
		String fop = "";
		String style = "", styleSeparator = "", styleParam = "";
		DownloadUrl url = new DownloadUrl(domain, useHttps, key);
		url.setAttname(attname) // 配置 attname
				.setFop(fop) // 配置 fop
				.setStyle(style, styleSeparator, styleParam); // 配置 style
		// 带有效期
		long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
		Auth auth = Auth.create("your access key", "your secret key");
		long deadline = 0L;
		String urlString = url.buildURL(auth, deadline);
		System.out.println(urlString);
		return urlString;
	}


}
