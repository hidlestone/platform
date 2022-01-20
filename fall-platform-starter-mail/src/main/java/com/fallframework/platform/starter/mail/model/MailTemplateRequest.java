package com.fallframework.platform.starter.mail.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class MailTemplateRequest extends BasePageRequest {

	private static final long serialVersionUID = -5437704235902120160L;

	/**
	 * 邮件模板配置编码
	 */
	private String code;

	/**
	 * 邮件模板配置描述
	 */
	private String desc;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 发送者
	 */
	private String from;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 文件组ID
	 */
	private Long fileGroupId;

	/**
	 * 重试次数
	 */
	private Byte retryCount;

}
