package com.fallframework.platform.starter.pay.alipay.model;

import com.fallframework.platform.starter.api.response.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class AliPayCommonResponse extends BaseEntityResponse {

	private static final long serialVersionUID = 4500409338923038958L;

	/**
	 * 网关返回码,详见文档
	 */
	private String code;

	/**
	 * 网关返回码描述,详见文档
	 */
	private String msg;

	/**
	 * 业务返回码，参见具体的API接口文档
	 */
	private String sub_code;

	/**
	 * 业务返回码描述，参见具体的API接口文档
	 */
	private String sub_msg;

	/**
	 * 签名,详见文档
	 */
	private String sign;

}
