package com.fallframework.platform.starter.core.util;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.security.spec.InvalidKeySpecException;

/**
 * 生成加密的用户信息测试
 *
 * @author zhuangpf
 */
public class GenUserEncyTest {

	public static final String DEFAULT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCCV88D7F/aUTvB9F0ZHhGk++9MPHblXc8zIrj2\r\nHXWBH/xgNzqZUPhinzgKFmJQE5EZydQbv8BW0iPoHjYPe0Zo/MHzJkvniaMhkYej2ii1tuQm411e\r\n9KLCR19mZqc1b96O3PGRcM5t0GZxt+ZzDX17+S02l58KkiNrELfLtJDa7QIDAQAB";
	public static final String DEFAULT_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIJXzwPsX9pRO8H0XRkeEaT770w8\r\nduVdzzMiuPYddYEf/GA3OplQ+GKfOAoWYlATkRnJ1Bu/wFbSI+geNg97Rmj8wfMmS+eJoyGRh6Pa\r\nKLW25CbjXV70osJHX2ZmpzVv3o7c8ZFwzm3QZnG35nMNfXv5LTaXnwqSI2sQt8u0kNrtAgMBAAEC\r\ngYA2h7np8Eghs/6GJE+WpIfJOEVi5aDwUJDQ2eP7w1NXlCQntLNlVQS1bdsqDTdQPP9lhJSij1RO\r\nflfbUlhXncFUrbQE6DExArlKtGfffzTUh/FM4antpvigcMQaV/wnQO4g9vfK6sN1bk6fpPRAafGB\r\n7WrIBFf+GhIB5+c8DXdyWQJBAMThHlhghdl3Kh/sxqif+0sJAJjg/2K5mJQtlnbn3PmEDUV4dlqt\r\nDbqJKk9ZFc4asj1wy/ohRaaepsHA/ijfMtMCQQCpe8gCw/y4KU0ybQVGtQsyfdQd8OxczLpkB+9h\r\n6jDFLoL2m/1aOv56s+XqhfTlb0NsGqGi9m+BUcADqRBkPaM/AkEAiNewL91nK3AYZ3g73JjWIaFw\r\nONKqsAQweU32d09R2FxgnRjNqtj94dnU/rH85fTITsxH+uOFdfpwDdwGzIHW4QJBAILTBYT8++q8\r\nFtbByLzXqxkCf+XMvuazMX1dkq6mM5dBW944csxflWfpzaPGW09vH0AnHnXIfDH6hJlOu1RlL8kC\r\nQEvY9FTDxQN/xBahRTWwBQYRKJRCsloS+HOxvPdXsyV9pzrQ7epLYKvDQQxJm4PfP5rkE+wblJ3z\r\n/JoECAkTM1k=";

	@Test
	public void test01() throws UnsupportedEncodingException, InvalidKeySpecException {
		String encryTxt = EncryptionUtil.encryptBASE64(EncryptionUtil.encryptRSA("C00012", DEFAULT_PUBLIC_KEY));
		System.out.println(encryTxt);

		byte[] bytes = EncryptionUtil.decryptBASE64(encryTxt.getBytes("UTF-8"));
		System.out.println("byte:" + bytes.length);
		String s = new String(bytes, "UTF-8");
		System.out.println(s);

		String plainText = EncryptionUtil.decryptRSA(s, DEFAULT_PRIVATE_KEY);
		System.out.println(plainText);

		byte[] decrypted2 = EncryptionUtil.decryptRSA(s.getBytes("UTF-8"),
				new EncryptionUtil.RSAKeyFactory().generatePrivateKeyByBase64Text(DEFAULT_PRIVATE_KEY));
		String plainText2 = new String(decrypted2, "UTF-8");
		System.out.println(plainText2);

	}


	@Test
	public void test02() throws UnsupportedEncodingException, InvalidKeySpecException {
		// 账号加密解密方式
		String encryTxt = EncryptionUtil.encryptRSA("C00012", DEFAULT_PUBLIC_KEY);
		System.out.println(encryTxt);
		byte[] bytes = EncryptionUtil.decryptRSA(EncryptionUtil.decryptBASE64(encryTxt.getBytes("UTF-8")),
				new EncryptionUtil.RSAKeyFactory().generatePrivateKeyByBase64Text(DEFAULT_PRIVATE_KEY));
		String s = new String(bytes, "UTF-8");
		System.out.println(s);
		
		String password = "8F7BC87F6B67402F29A87B1DD360D7981D06036F9A9B1603AF7E3C78F4DEF7F8";
		String psw = PasswordUtils.encrptSHA256(password);
		System.out.println(psw);
	}

	
	
}
