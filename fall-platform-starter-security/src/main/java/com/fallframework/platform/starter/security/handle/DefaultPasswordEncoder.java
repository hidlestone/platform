package com.fallframework.platform.starter.security.handle;

import com.fallframework.platform.starter.core.util.EncryptionUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * security默认的密码解码器
 *
 * @author zhuangpf
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

	/**
	 * 进行MD5加密
	 *
	 * @param rawPassword 密码
	 * @return 加密结果
	 */
	@Override
	public String encode(CharSequence rawPassword) {
		return EncryptionUtil.encryptMD5(rawPassword.toString());
	}

	/**
	 * 密码对比
	 *
	 * @param rawPassword     未加密密码
	 * @param encodedPassword 已加密密码
	 * @return 是否一致
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encodedPassword.equals(EncryptionUtil.encryptMD5(rawPassword.toString()));
	}

}
