package com.fallframework.platform.starter.mail.service.impl;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.mail.entity.MailHistory;
import com.fallframework.platform.starter.mail.entity.MailSenderConfig;
import com.fallframework.platform.starter.mail.entity.MailTemplate;
import com.fallframework.platform.starter.mail.model.MailSendInfoRequest;
import com.fallframework.platform.starter.mail.model.SendFlagEnum;
import com.fallframework.platform.starter.mail.service.MailHistoryService;
import com.fallframework.platform.starter.mail.service.MailSenderConfigService;
import com.fallframework.platform.starter.mail.service.MailTemplateService;
import com.fallframework.platform.starter.mail.service.PlatformMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * 平台邮件发送服务
 *
 * @author zhuangpf
 */
@Service
public class PlatformMailSenderImpl implements PlatformMailSender {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private MailHistoryService mailHistoryService;
	@Autowired
	private MailTemplateService mailTemplateService;
	@Autowired
	private MailSenderConfigService mailSenderConfigService;
	@Autowired
	private TemplateEngine templateEngine;

	/**
	 * 发送简单邮件
	 *
	 * @param request 邮件信息
	 * @return 是否发送成功
	 */
	@Override
	public ResponseResult sendSimpleEmail(MailSendInfoRequest request) {
		// 模板
		MailTemplate mailTemplate = mailTemplateService.select(request.getMailTemplateId()).getData();
		// 配置
		MailSenderConfig mailSenderConfig = mailSenderConfigService.select(request.getMailSenderConfigId()).getData();
		// 0-失败，1-成功
		byte sendFlag = 1;
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(mailSenderConfig.getUsername());
			message.setTo(request.getReceiver());
			message.setSubject(mailTemplate.getTitle());
			message.setText(mailTemplate.getContent());
			// TODO 添加附件
			if (null != mailTemplate.getFileGroupId()) {

			}
			// 获取邮件发送器
			JavaMailSender mailSender = this.getMailSender(mailSenderConfig);


			mailSender.send(message);
		} catch (Exception e) {
			sendFlag = (byte) SendFlagEnum.FAIL.ordinal();
			e.printStackTrace();
		}
		// 添加历史记录
		MailHistory mailHistory = new MailHistory();
		mailHistory.setTemplateCode(mailTemplate.getCode());
		mailHistory.setTitle(mailTemplate.getTitle());
		mailHistory.setFrom(mailSenderConfig.getUsername());
//		mailHistory.setUserId(request.getUserId());
//		mailHistory.setUserName(request.getUserName());
		mailHistory.setReceiver(request.getReceiver());
		mailHistory.setCc(request.getCc());
		mailHistory.setBcc(request.getBcc());
		mailHistory.setContent(mailTemplate.getContent());
		mailHistory.setFileGroupId(request.getFileGroupId());
		mailHistory.setTryCount(1);
		mailHistory.setLastSendTime(new Date());
		mailHistory.setSendFlag(sendFlag);
		mailHistoryService.insert(mailHistory);
		return ResponseResult.success();
	}

	/**
	 * 根据配置构建sender
	 *
	 * @param config
	 * @return
	 */
	private JavaMailSender getMailSender(MailSenderConfig config) {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		if (config.getPort() != null) {
			sender.setPort(config.getPort());
		}
		sender.setUsername(config.getUsername());
		sender.setPassword(config.getPassword());
		sender.setProtocol(config.getProtocol());
		if (config.getDefaultEncoding() != null) {
			sender.setDefaultEncoding(config.getDefaultEncoding().name());
		}
		if (!config.getProperties().isEmpty()) {
//			sender.setJavaMailProperties(JSON.parse(config.getProperties()));
		}
		return sender;
	}

	/**
	 * html格式邮件
	 *
	 * @param request 邮件信息
	 * @return 是否发送成功
	 */
	@Override
	public ResponseResult sendMimeMsgEmail(MailSendInfoRequest request) {
		// 配置&模板
		MailTemplate mailTemplate = mailTemplateService.select(request.getMailTemplateId()).getData();
		MailSenderConfig mailSenderConfig = mailSenderConfigService.select(request.getMailSenderConfigId()).getData();
		// 0-失败，1-成功
		byte sendFlag = 1;
		MimeMessage message = null;
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(mailTemplate.getFrom());
			helper.setTo(request.getReceiver()); // 接收地址
			helper.setSubject(mailTemplate.getTitle()); // 标题
			// 带HTML格式的内容
//			StringBuffer sb = new StringBuffer("<p style='color:#42b983'>使用Spring Boot发送HTML格式邮件。</p>");
			StringBuffer sb = new StringBuffer(mailTemplate.getContent());
			helper.setText(sb.toString(), true);
			// TODO 添加附件
			if (null != mailTemplate.getFileGroupId()) {

			}
			mailSender.send(message);
		} catch (Exception e) {
			sendFlag = (byte) SendFlagEnum.FAIL.ordinal();
			e.printStackTrace();
		}
		// 添加历史记录
		MailHistory mailHistory = new MailHistory();
		mailHistory.setTemplateCode(mailTemplate.getCode());
		mailHistory.setTitle(mailTemplate.getTitle());
		mailHistory.setFrom(mailSenderConfig.getUsername());
//		mailHistory.setUserId(request.getUserId());
//		mailHistory.setUserName(request.getUserName());
		mailHistory.setReceiver(request.getReceiver());
		mailHistory.setCc(request.getCc());
		mailHistory.setBcc(request.getBcc());
		mailHistory.setContent(mailTemplate.getContent());
		mailHistory.setFileGroupId(request.getFileGroupId());
		mailHistory.setTryCount(1);
		mailHistory.setLastSendTime(new Date());
		mailHistory.setSendFlag(sendFlag);
		mailHistoryService.insert(mailHistory);
		return ResponseResult.success();
	}

	/**
	 * 发送行内邮件
	 *
	 * @param request 邮件信息
	 * @return 是否发送成功
	 */
	@Override
	public ResponseResult sendInlineMail(MailSendInfoRequest request) {
		// 配置&模板
		MailTemplate mailTemplate = mailTemplateService.select(request.getMailTemplateId()).getData();
		MailSenderConfig mailSenderConfig = mailSenderConfigService.select(request.getMailSenderConfigId()).getData();
		// 0-失败，1-成功
		byte sendFlag = 1;
		MimeMessage message = null;
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(mailTemplate.getFrom());
			helper.setTo(request.getReceiver()); // 接收地址
			helper.setSubject(mailTemplate.getTitle()); // 标题
//			helper.setText("<html><body>博客图：<img src='cid:img'/></body></html>", true); // 内容
			helper.setText(mailTemplate.getContent(), true); // 内容
			// 传入附件
			FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/img/sunshine.png"));
			// TODO 添加附件
			if (null != mailTemplate.getFileGroupId()) {

			}
			helper.addInline("img", file);
			mailSender.send(message);
		} catch (Exception e) {
			sendFlag = (byte) SendFlagEnum.FAIL.ordinal();
			e.printStackTrace();
		}
		// 添加历史记录
		MailHistory mailHistory = new MailHistory();
		mailHistory.setTemplateCode(mailTemplate.getCode());
		mailHistory.setTitle(mailTemplate.getTitle());
		mailHistory.setFrom(mailSenderConfig.getUsername());
//		mailHistory.setUserId(request.getUserId());
//		mailHistory.setUserName(request.getUserName());
		mailHistory.setReceiver(request.getReceiver());
		mailHistory.setCc(request.getCc());
		mailHistory.setBcc(request.getBcc());
		mailHistory.setContent(mailTemplate.getContent());
		mailHistory.setFileGroupId(request.getFileGroupId());
		mailHistory.setTryCount(1);
		mailHistory.setLastSendTime(new Date());
		mailHistory.setSendFlag(sendFlag);
		mailHistoryService.insert(mailHistory);
		return ResponseResult.success();
	}

	/**
	 * 发送模板邮件
	 *
	 * @param request 邮件信息
	 * @return 是否发送成功
	 */
	@Override
	public ResponseResult sendTemplateEmail(MailSendInfoRequest request) {
		// 配置&模板
		MailTemplate mailTemplate = mailTemplateService.select(request.getMailTemplateId()).getData();
		MailSenderConfig mailSenderConfig = mailSenderConfigService.select(request.getMailSenderConfigId()).getData();
		// 0-失败，1-成功
		byte sendFlag = 1;
		MimeMessage message = null;
		// 模板内容
		String template = "";
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(mailTemplate.getFrom());
			helper.setTo(request.getReceiver()); // 接收地址
			helper.setSubject(mailTemplate.getTitle()); // 标题
			// 处理邮件模板
			Context context = new Context();
			context.setVariable("code", "");
			template = templateEngine.process("emailTemplate", context);
			helper.setText(template, true);
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 添加历史记录
		MailHistory mailHistory = new MailHistory();
		mailHistory.setTemplateCode(mailTemplate.getCode());
		mailHistory.setTitle(mailTemplate.getTitle());
		mailHistory.setFrom(mailSenderConfig.getUsername());
//		mailHistory.setUserId(request.getUserId());
//		mailHistory.setUserName(request.getUserName());
		mailHistory.setReceiver(request.getReceiver());
		mailHistory.setCc(request.getCc());
		mailHistory.setBcc(request.getBcc());
		mailHistory.setContent(template);
		mailHistory.setFileGroupId(request.getFileGroupId());
		mailHistory.setTryCount(1);
		mailHistory.setLastSendTime(new Date());
		mailHistory.setSendFlag(sendFlag);
		mailHistoryService.insert(mailHistory);
		return ResponseResult.success();
	}
}
