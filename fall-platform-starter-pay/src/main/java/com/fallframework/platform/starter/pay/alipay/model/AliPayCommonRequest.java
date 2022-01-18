package com.fallframework.platform.starter.pay.alipay.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 公共请求参数
 *
 * @author zhuangpf
 */
@Getter
@Setter
public class AliPayCommonRequest extends BaseEntityRequest {

	private static final long serialVersionUID = -6427866406682876632L;

	/**
	 * 支付宝分配给开发者的应用ID
	 */
	private String app_id;

	/**
	 * 接口名称
	 */
	private String method;

	/**
	 * 仅支持JSON
	 */
	private String format = "JSON";

	/**
	 * 请求使用的编码格式，如utf-8,gbk,gb2312等
	 */
	private String charset;

	/**
	 * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
	 */
	private String sign_type;

	/**
	 * 商户请求参数的签名串，详见签名
	 */
	private String sign;

	/**
	 * 发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
	 */
	private String timestamp;

	/**
	 * 调用的接口版本，固定为：1.0
	 */
	private String version;

	/**
	 * 支付宝服务器主动通知商户服务器里指定的页面http/https路径。
	 * http://api.test.alipay.net/atinterface/receive_notify.htm
	 */
	private String notify_url;

	/**
	 * 详见应用授权概述
	 */
	private String app_auth_token;

	/**
	 * 请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档
	 */
	private String biz_content;
	
}
