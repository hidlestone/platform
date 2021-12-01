package com.fallframework.platform.starter.mail.model;

import com.fallframework.platform.starter.core.entity.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class MailTemplateRequest extends BasePageRequest {

	private static final long serialVersionUID = -5437704235902120160L;

	private String code;

	private String desc;
}
