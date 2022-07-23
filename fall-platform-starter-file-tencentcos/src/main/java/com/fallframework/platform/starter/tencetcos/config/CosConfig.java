package com.fallframework.platform.starter.tencetcos.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 腾讯云COS配置
 * https://github.com/tencentyun/qcloud-cos-sts-sdk/tree/master/java
 *
 * @author zhuangpf
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "platform.starter.tencetcos")
public class CosConfig {

	private String appId;
	// 云 API 密钥 Id
	private String secretId;
	// 云 API 密钥 key
	private String secretKey;
	// 要申请的临时密钥最长有效时间，单位秒，默认 1800，最大可设置 7200
	private Integer durationSeconds;
	// 存储桶所属地域，如 ap-guangzhou
	private String regionName;
	// 存储桶名称：bucketName-appid, 如 example-125000000
	private String bucketName;
	// 资源的前缀，可以根据自己网站的用户登录态判断允许上传的具体路径，例子： a.jpg 或者 a/* 或者 * (使用通配符*存在重大安全风险, 请谨慎评估使用)
	private String[] allowPrefixes;
	// 授予 COS API 权限集合, 如简单上传操作：name/cos:PutObject
	private String[] allowActions;
	// 策略：由 allowActions、bucket、region、allowPrefix字段组成的描述授权的具体信息
	private String policy;

}
