package com.fallframework.platform.starter.mail.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class MailHistoryRequest extends BasePageRequest {

	private static final long serialVersionUID = 6057533381981096555L;

	/**
	 * 邮件模板ID
	 */
	private Long templateId;

	/**
	 * 邮件配置ID
	 */
	private Long configId;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 发送者
	 */
	private String from;

	/**
	 * 接收用户ID
	 */
	private Long receiveUserId;

	/**
	 * 接收用户名称
	 */
	private Long receiveUserName;

	/**
	 * 接收邮箱
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
	 * 内容
	 */
	private String content;

	/**
	 * 文件组ID
	 */
	private Long fileGroupId;

	/**
	 * 发送次数
	 */
	private Integer tryCount;

	/**
	 * 最后一次发送时间
	 */
	private Date lastSendTime;

	/**
	 * 0-失败，1-成功
	 */
	private Byte sendFlag;

}
