package com.fallframework.platform.starter.mail.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 邮件发送请求参数
 *
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
	 * 接收邮件
	 */
	private String receiveMail;

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

	/**
	 * 接收用户ID
	 */
	private Long receiveUserId;

	/**
	 * 接收用户名称
	 */
	private String receiveUserName;
}
