package com.fallframework.platform.starter.pay.model;

import com.fallframework.platform.starter.core.entity.response.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignatureItem extends BaseEntityResponse {

	private static final long serialVersionUID = 2276870184472499384L;
	
	private String signContent;

	private String sign;

}
