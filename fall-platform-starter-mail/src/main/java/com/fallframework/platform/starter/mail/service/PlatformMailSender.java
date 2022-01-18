package com.fallframework.platform.starter.mail.service;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.mail.model.MailSendInfoRequest;

public interface PlatformMailSender {

	/**
	 * 简单邮件
	 */
	ResponseResult sendSimpleEmail(MailSendInfoRequest request);

	/**
	 * html 格式邮件
	 */
	ResponseResult sendMimeMsgEmail(MailSendInfoRequest request);

	/**
	 * 行内邮件
	 */
	ResponseResult sendInlineMail(MailSendInfoRequest request);

	/**
	 * 模板邮件
	 */
	ResponseResult sendTemplateEmail(MailSendInfoRequest request);
	
}
