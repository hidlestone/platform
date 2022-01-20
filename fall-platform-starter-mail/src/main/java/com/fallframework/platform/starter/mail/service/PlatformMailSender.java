package com.fallframework.platform.starter.mail.service;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.mail.model.MailSendInfoRequest;

/**
 * 平台邮件发送服务
 */
public interface PlatformMailSender {

	/**
	 * 发送简单邮件
	 *
	 * @param request 邮件信息
	 * @return 是否发送成功
	 */
	ResponseResult sendSimpleEmail(MailSendInfoRequest request);

	/**
	 * html格式邮件
	 *
	 * @param request 邮件信息
	 * @return 是否发送成功
	 */
	ResponseResult sendMimeMsgEmail(MailSendInfoRequest request);

	/**
	 * 发送行内邮件
	 *
	 * @param request 邮件信息
	 * @return 是否发送成功
	 */
	ResponseResult sendInlineMail(MailSendInfoRequest request);

	/**
	 * 发送模板邮件
	 *
	 * @param request 邮件信息
	 * @return 是否发送成功
	 */
	ResponseResult sendTemplateEmail(MailSendInfoRequest request);

}
