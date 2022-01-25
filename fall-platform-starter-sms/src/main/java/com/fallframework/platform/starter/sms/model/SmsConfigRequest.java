package com.fallframework.platform.starter.sms.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class SmsConfigRequest extends BasePageRequest {

	private static final long serialVersionUID = -3587548184906708456L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 使用的短信产品类型
	 */
	private String productType;

	/**
	 * 区域ID
	 */
	private String regionId;

	/**
	 * accessKeyId
	 */
	private String accessKeyId;

	/**
	 * accessKeySecret
	 */
	private String accessKeySecret;

	/**
	 * 其他的参数配置(JSON格式)
	 */
	private String properties;

}
