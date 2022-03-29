package com.fallframework.platform.starter.shiro.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class AccountLoginToken extends UsernamePasswordToken {

	/**
	 * 登录方式类型
	 */
	private LoginTypeEnum loginType;

	/**
	 * 接收到的验证码
	 */
	private String verificationCode;

	/**
	 * 随机码
	 */
	private String randomCode;

	/**
	 * 构造函数
	 */
	public AccountLoginToken(String account, String password, LoginTypeEnum loginType, String verificationCode, String randomCode) {
		super(account, password);
		this.loginType = loginType;
		this.verificationCode = verificationCode;
		this.randomCode = randomCode;
	}
}
