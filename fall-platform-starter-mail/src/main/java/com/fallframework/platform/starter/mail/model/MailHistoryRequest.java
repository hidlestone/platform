package com.fallframework.platform.starter.mail.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class MailHistoryRequest extends BasePageRequest {

	private static final long serialVersionUID = 6057533381981096555L;

	private Long userName;

	private String receiver;

	private String title;

	private Byte sendFlag;

}
