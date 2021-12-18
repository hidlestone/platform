package com.fallframework.platform.starter.file.qiniu.config;

import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

/**
 * 用于配置七牛多媒体所需的配置类
 *
 * @author zhuangpf
 */
@org.springframework.context.annotation.Configuration
public class QiniuConfig {

	@Bean
	public UploadManager uploadManager(QiniuPropertyResource qiniuPropertyResource) {
		Configuration cfg = null;
		String area = qiniuPropertyResource.getArea();
		area = StringUtils.isEmpty(area) ? "huadong" : area;
		if ("huadong".equals(area)) {
			//目前默认使用华东
			cfg = new Configuration(new Zone.Builder()
					.upHttp("http://upload.qiniup.com")
					.upHttps("https://upload.qiniup.com")
					.upBackupHttp("http://upload.qiniup.com")
					.upBackupHttps("https://upload.qiniup.com")
					.iovipHttp("http://iovip.qbox.me")
					.iovipHttps("https://iovip.qbox.me")
					.rsHttp("http://rs.qiniu.com")
					.rsHttps("https://rs.qbox.me")
					.rsfHttp("http://rsf.qiniu.com")
					.rsfHttps("https://rsf.qbox.me")
					.apiHttp("http://api.qiniu.com")
					.apiHttps("https://api.qiniu.com").build());
		} else if ("huanan".equals(area)) {
			//使用华南 upload-z2.qiniup.com
			cfg = new Configuration(new Zone.Builder()
					.upHttp("http://upload-z2.qiniup.com")
					.upHttps("https://upload-z2.qiniup.com")
					.upBackupHttp("http://upload-z2.qiniup.com")
					.upBackupHttps("https://upload-z2.qiniup.com")
					.iovipHttp("http://iovip.qbox.me")
					.iovipHttps("https://iovip.qbox.me")
					.rsHttp("http://rs.qiniu.com")
					.rsHttps("https://rs.qbox.me")
					.rsfHttp("http://rsf.qiniu.com")
					.rsfHttps("https://rsf.qbox.me")
					.apiHttp("http://api.qiniu.com")
					.apiHttps("https://api.qiniu.com").build());
		}
		// 设置请求协议为http
//		cfg.useHttpsDomains = false;
		//这里可以加其它地区的
		return new UploadManager(cfg);
	}

}
