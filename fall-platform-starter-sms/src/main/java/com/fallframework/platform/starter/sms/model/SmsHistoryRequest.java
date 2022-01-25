package com.fallframework.platform.starter.sms.model;

import com.fallframework.platform.starter.api.model.StatusEnum;
import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class SmsHistoryRequest extends BasePageRequest {

	private static final long serialVersionUID = -7346671272198829370L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 短信模板ID
	 */
	private Long templateId;

	/**
	 * 邮件配置ID
	 */
	private Long configId;

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
	private String receiveUserName;

	/**
	 * 接收号码
	 */
	private String receivePhoneNumber;

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
	private Byte tryCount;

	/**
	 * 失败原因
	 */
	private String msg;

	/**
	 * 最后一次发送时间
	 */
	private Date lastSendTime;

	/**
	 * 0-失败，1-成功
	 */
	private StatusEnum status;
	
	/**
	 * 最后一次发送时间
	 */
	private Date startLastSendTime;

	/**
	 * 最后一次发送时间
	 */
	private Date endLastSendTime;
}
