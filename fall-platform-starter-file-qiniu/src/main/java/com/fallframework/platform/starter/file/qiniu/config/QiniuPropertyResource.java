package com.fallframework.platform.starter.file.qiniu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 用于读取OSS的配置信息
 *
 * @author zhuangpf
 */
@Configuration
@ConfigurationProperties(prefix = "qiniu") // 参数的统一前缀
@PropertySource(value = "classpath:config/qiniu.properties") // 从哪个文件读取
@Data
public class QiniuPropertyResource {

	private String accessKey;
	private String secretKey;

	private String bucket;

	/**
	 * 格式如：http://dxmqiniuimage.laidy.com.cn
	 */
	private String cdnDomain;

	/**
	 * bucket所在大区
	 */
	private String area;
}
