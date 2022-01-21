package com.fallframework.platform.starter.mail.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class MailSenderConfigRequest extends BasePageRequest {

	private static final long serialVersionUID = 3865319847062053537L;

	/**
	 * 如：smtp.163.com
	 */
	private String host;

	/**
	 * 端口
	 */
	private Integer port;

	/**
	 * 账号
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 协议
	 */
	private String protocol = "smtp";

	/**
	 * 默认编码
	 */
	private String defaultEncoding = "UTF-8";

	/**
	 * 其他的参数配置(JSON格式)
	 */
	private String properties;

}
