package com.fallframework.platform.starter.core.context;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ClientToken implements Serializable {

	private static final long serialVersionUID = 425172311463467148L;

	private String appCode;
	private String appName;
	private String token;
	// 过期时间
	private Long expireTime;
	// 权限
	private List<String> permissions;

	public ClientToken(String appCode, String appName, String token, Long expireTime, List<String> permissions) {
		this.appCode = appCode;
		this.appName = appName;
		this.token = token;
		this.expireTime = expireTime;
		this.permissions = permissions;
	}

}
