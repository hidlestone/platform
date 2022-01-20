package com.fallframework.platform.starter.mail.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.data.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 将原本配置在yml的spring.mail，保存在数据库中
 *
 * @link org.springframework.boot.autoconfigure.mail.MailProperties
 */
@Getter
@Setter
@TableName(value = "s_mail_sender_config")
public class MailSenderConfig extends BaseEntity {

	private static final long serialVersionUID = 799272096665923741L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 如：smtp.163.com
	 */
	@TableField(value = "host")
	private String host;

	/**
	 * 端口
	 */
	@TableField(value = "port")
	private Integer port;

	/**
	 * 账号
	 */
	@TableField(value = "username")
	private String username;

	/**
	 * 密码
	 */
	@TableField(value = "password")
	private String password;

	/**
	 * 其他的参数配置(JSON格式)
	 */
	@TableField(value = "properties")
	private String properties;

	/**
	 * Protocol used by the SMTP server.
	 */
	private String protocol = "smtp";

	/**
	 * Default MimeMessage encoding.
	 */
	private Charset defaultEncoding = StandardCharsets.UTF_8;

}