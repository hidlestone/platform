package com.fallframework.platform.starter.mail.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.data.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName(value = "s_mail_template")
public class MailTemplate extends BaseEntity {

	private static final long serialVersionUID = 705462373226504501L;
	
	/**
	 * 邮件模板配置编码
	 */
	@TableId(value = "code", type = IdType.INPUT)
	private String code;

	/**
	 * 邮件模板配置描述
	 */
	@TableField(value = "desc")
	private String desc;

	/**
	 * 标题
	 */
	@TableField(value = "title")
	private String title;

	/**
	 * 发送者
	 */
	@TableField(value = "from")
	private String from;

	/**
	 * 内容
	 */
	@TableField(value = "content")
	private String content;

	/**
	 * 文件组ID
	 */
	@TableField(value = "file_group_id")
	private Long fileGroupId;

	/**
	 * 重试次数
	 */
	@TableField(value = "retry_count")
	private Byte retryCount;

	/**
	 * 创建用户ID
	 */
	@TableField(value = "create_user_id")
	private Long createUserId;

	/**
	 * 修改用户ID
	 */
	@TableField(value = "modify_user_id")
	private Long modifyUserId;

	/**
	 * 创建时间
	 */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/**
	 * 更改时间
	 */
	@TableField(value = "gmt_modified")
	private Date gmtModified;

}