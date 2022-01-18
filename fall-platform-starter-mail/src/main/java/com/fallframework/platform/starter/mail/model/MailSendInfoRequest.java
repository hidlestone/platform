package com.fallframework.platform.starter.mail.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;
import com.fallframework.platform.starter.mail.entity.MailSenderConfig;
import com.fallframework.platform.starter.mail.entity.MailTemplate;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class MailSendInfoRequest extends BaseEntityRequest {

	private static final long serialVersionUID = 4148012618236603712L;

	private MailTemplate mailTemplate;

	private MailSenderConfig mailSenderConfig;

	/**
	 * 接收用户ID
	 */
	private Long userId;

	/**
	 * 接收用户名称
	 */
	private Long userName;

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
