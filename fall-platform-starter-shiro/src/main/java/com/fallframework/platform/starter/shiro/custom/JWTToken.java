package com.fallframework.platform.starter.shiro.custom;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author zhuangpf
 */
public class JWTToken implements AuthenticationToken {

	private String token;
	private String username;

	public JWTToken(String username, String token) {
		this.username = username;
		this.token = token;
	}

	@Override
	public String getPrincipal() {
		return username;
	}

	@Override
	public String getCredentials() {
		return token;
	}
}
