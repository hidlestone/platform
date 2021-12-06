package com.fallframework.platform.starter.mail.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.data.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "s_mail_sender_config")
public class MailSenderConfig extends BaseEntity {

	private static final long serialVersionUID = 799272096665923741L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 发送者
	 */
	@TableField(value = "from")
	private String from;

	/**
	 * 如：smtp.163.com
	 */
	@TableField(value = "host")
	private String host;

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
	 * 配置
	 */
	@TableField(value = "properties")
	private String properties;

}