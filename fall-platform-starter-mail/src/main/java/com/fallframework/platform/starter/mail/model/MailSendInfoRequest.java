package com.fallframework.platform.starter.mail.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class MailSendInfoRequest extends BaseEntityRequest {

	private static final long serialVersionUID = 4148012618236603712L;

	/**
	 * 邮件模板ID
	 */
	private Long mailTemplateId;

	/**
	 * 邮件配置ID
	 */
	private Long mailSenderConfigId;

	/**
	 * 接收者
	 */
	private String receiver;

	/**
	 * 抄送者
	 */
	private String cc;

	/**
	 * 密送者
	 */
	private String bcc;

	/**
	 * 文件组ID
	 */
	private Long fileGroupId;

}
