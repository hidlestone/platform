package com.fallframework.platform.starter.mail.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class MailSenderConfigRequest extends BasePageRequest {

	private static final long serialVersionUID = 3865319847062053537L;

	private String from;

	private String host;

	private String username;
	
}
