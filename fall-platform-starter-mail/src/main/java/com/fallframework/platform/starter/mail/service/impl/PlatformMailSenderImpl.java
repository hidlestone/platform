package com.fallframework.platform.starter.mail.service.impl;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.mail.entity.MailHistory;
import com.fallframework.platform.starter.mail.entity.MailSenderConfig;
import com.fallframework.platform.starter.mail.entity.MailTemplate;
import com.fallframework.platform.starter.mail.model.MailSendInfoRequest;
import com.fallframework.platform.starter.mail.model.SendFlagEnum;
import com.fallframework.platform.starter.mail.service.MailHistoryService;
import com.fallframework.platform.starter.mail.service.PlatformMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
	private TemplateEngine templateEngine;

	@Override
	public ResponseResult sendSimpleEmail(MailSendInfoRequest request) {
		// 配置&模板
		MailSenderConfig mailSenderConfig = request.getMailSenderConfig();
		MailTemplate mailTemplate = request.getMailTemplate();
		// 0-失败，1-成功
		byte sendFlag = (byte) SendFlagEnum.SUCCESS.ordinal();
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(mailSenderConfig.getFrom());
			message.setTo(request.getReceiver());
			message.setSubject(mailTemplate.getTitle());
			message.setText(mailTemplate.getContent());
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
		mailHistory.setFrom(mailSenderConfig.getFrom());
		mailHistory.setUserId(request.getUserId());
		mailHistory.setUserName(request.getUserName());
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

	@Override
	public ResponseResult sendMimeMsgEmail(MailSendInfoRequest request) {
		// 配置&模板
		MailSenderConfig mailSenderConfig = request.getMailSenderConfig();
		MailTemplate mailTemplate = request.getMailTemplate();
		MimeMessage message = null;
		// 0-失败，1-成功
		byte sendFlag = (byte) SendFlagEnum.SUCCESS.ordinal();
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
		mailHistory.setFrom(mailSenderConfig.getFrom());
		mailHistory.setUserId(request.getUserId());
		mailHistory.setUserName(request.getUserName());
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

	@Override
	public ResponseResult sendInlineMail(MailSendInfoRequest request) {
		// 配置&模板
		MailSenderConfig mailSenderConfig = request.getMailSenderConfig();
		MailTemplate mailTemplate = request.getMailTemplate();
		MimeMessage message = null;
		// 0-失败，1-成功
		byte sendFlag = (byte) SendFlagEnum.SUCCESS.ordinal();
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
		mailHistory.setFrom(mailSenderConfig.getFrom());
		mailHistory.setUserId(request.getUserId());
		mailHistory.setUserName(request.getUserName());
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

	@Override
	public ResponseResult sendTemplateEmail(MailSendInfoRequest request) {
		// 配置&模板
		MailSenderConfig mailSenderConfig = request.getMailSenderConfig();
		MailTemplate mailTemplate = request.getMailTemplate();
		MimeMessage message = null;
		// 0-失败，1-成功
		byte sendFlag = (byte) SendFlagEnum.SUCCESS.ordinal();
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
		mailHistory.setFrom(mailSenderConfig.getFrom());
		mailHistory.setUserId(request.getUserId());
		mailHistory.setUserName(request.getUserName());
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
