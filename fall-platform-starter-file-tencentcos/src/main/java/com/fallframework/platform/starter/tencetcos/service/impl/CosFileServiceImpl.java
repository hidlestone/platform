package com.fallframework.platform.starter.tencetcos.service.impl;

import com.fallframework.platform.starter.tencetcos.config.CosConfig;
import com.fallframework.platform.starter.tencetcos.model.CosUploadDto;
import com.fallframework.platform.starter.tencetcos.service.CosFileService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.soap.Addressing;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author zhuangpf
 */
public class CosFileServiceImpl implements CosFileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CosFileServiceImpl.class);

	@Addressing
	private CosConfig cosConfig;

	@Override
	public String fileUpload(CosUploadDto dto) {
		MultipartFile file = dto.getFile();
		// 1 初始化用户身份信息（secretId, secretKey）。
		// SECRETID和SECRETKEY请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
		String secretId = cosConfig.getSecretId();
		String secretKey = cosConfig.getSecretKey();

		COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
		// 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
		// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
		Region region = new Region(cosConfig.getRegionName());
		ClientConfig clientConfig = new ClientConfig(region);

		// 这里建议设置使用 https 协议
		// 从 5.6.54 版本开始，默认使用了 https
		clientConfig.setHttpProtocol(HttpProtocol.https);

		// 3 生成 cos 客户端。
		COSClient cosClient = new COSClient(cred, clientConfig);

		// 指定文件将要存放的存储桶
		String bucketName = cosConfig.getBucketName();

		// 文件名称 为了保证文件名称唯一，此处使用uuid来设置文件名称
		String filename = UUID.randomUUID().toString().replaceAll("-", "") + "_" + file.getOriginalFilename();

		// 目录的生成：当前日期
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(new Date());
		if (null != dto && !StringUtils.isEmpty(dto.getDir())) {
			date = dto.getDir() + date;
		}

		// 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
		// 此处目录规则为 当前日期/文件名称
		String key = date + "/" + filename;
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, multipartFileToFile(file));
		PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
		LOGGER.info("putObjectResult:" + putObjectResult);

		// 删除本地上传资源
		delteTempFile(multipartFileToFile(file));

		Date expiration = new Date(new Date().getTime() + 5 * 60 * 10000);

		GeneratePresignedUrlRequest req =
				new GeneratePresignedUrlRequest(bucketName, key, HttpMethodName.GET);
		// 设置签名过期时间(可选), 若未进行设置, 则默认使用 ClientConfig 中的签名过期时间(1小时)
		// 可以设置任意一个未来的时间，推荐是设置 10 分钟到 3 天的过期时间
		req.setExpiration(expiration);

		URL url = cosClient.generatePresignedUrl(req);
		//关闭客户端
		cosClient.shutdown();

		//拼接文件地址
		StringBuffer stringBuffer = new StringBuffer()
				.append(url.getProtocol())
				.append("://")
				.append(url.getHost())
				.append(url.getPath());

		return stringBuffer.toString();
	}

	/**
	 * 此方法将multipartFile转为file
	 **/
	private File multipartFileToFile(MultipartFile file) {
		File toFile = null;
		if (file.equals("") || file.getSize() <= 0) {
			file = null;
		} else {
			try {
				InputStream ins = null;
				ins = file.getInputStream();
				toFile = new File(file.getOriginalFilename());
				inputStreamToFile(ins, toFile);
				ins.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return toFile;
	}

	/**
	 * 获取流文件
	 */
	private static void inputStreamToFile(InputStream ins, File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除本地临时文件
	 */
	public static void delteTempFile(File file) {
		if (file != null) {
			File fileTmp = new File(file.toURI());
			fileTmp.delete();
		}
	}

}
