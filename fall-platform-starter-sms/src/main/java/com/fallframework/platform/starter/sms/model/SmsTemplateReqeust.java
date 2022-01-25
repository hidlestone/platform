package com.fallframework.platform.starter.sms.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class SmsTemplateReqeust extends BasePageRequest {

	private static final long serialVersionUID = 8139239498138870485L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 短信模板配置描述
	 */
	private String desc;

	/**
	 * 发送号码
	 */
	private String from;

	/**
	 * 0-简单，1-模板
	 */
	private ContentTypeEnum contentType;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 文件组ID
	 */
	private Long fileGroupId;

	/**
	 * 重试次数
	 */
	private Byte retryCount;

}
