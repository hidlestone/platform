package com.fallframework.platform.starter.core.util;

import com.fallframework.platform.starter.utils.base.AESUtil;
import com.fallframework.platform.starter.utils.base.EncryptionUtil;
import org.junit.jupiter.api.Test;

/**
 * 配置文件中密码AES加密测试
 *
 * @author zhuangpf
 */
public class AESUtilTest {

	@Test
	public void testEncode() throws Exception {
		// 574F5244504C4159504C4154464F524D
		System.out.println(EncryptionUtil.byte2Hex("WORDPLAYPLATFORM".getBytes()));

		String aesEncrypt = AESUtil.aesEncrypt("root", "574F5244504C4159504C4154464F524D");
		System.out.println(aesEncrypt);

		String orign = AESUtil.aesDecrypt(aesEncrypt, "574F5244504C4159504C4154464F524D");
		System.out.println(orign);
		// 2ysedXL3BeLL9FydcTLbAw==
		// root
	}

	@Test
	public void test02() throws Exception {
		String encry = EncryptionUtil.encryptAES("root", "574F5244504C4159504C4154464F524D");
		System.out.println(encry);

		String s = EncryptionUtil.decryptAES(encry, "574F5244504C4159504C4154464F524D");
		System.out.println(s);
		// nkoMtaBZJNRkxBEJ9cMaqA==
		// root
	}

	@Test
	public void test03() throws Exception {
		String encry = EncryptionUtil.encryptAES("WordPlay_%123456!", "574F5244504C4159504C4154464F524D");
		System.out.println(encry);

		String s = EncryptionUtil.decryptAES(encry, "574F5244504C4159504C4154464F524D");
		System.out.println(s);
		// 5PA53hfXa8w+eh4yBEqiUeRC0gwqTLtINRMzL51MkVQ===
		// WordPlay_%123456!
	}
}
