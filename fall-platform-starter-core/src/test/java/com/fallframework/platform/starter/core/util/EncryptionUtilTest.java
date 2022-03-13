package com.fallframework.platform.starter.core.util;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.spec.InvalidKeySpecException;

/**
 * 加密工具类测试
 *
 * @author zhuangpf
 */
public class EncryptionUtilTest {

	/**
	 * 生成 RSA 公私钥对象
	 */
	@Test
	public static void TestPubPrivateRSA(String[] args) {
		EncryptionUtil.RSAKeyFactory rsaKeyFactory = new EncryptionUtil.RSAKeyFactory();
		// 生成 RSA 得到的一个密钥对(即一个公钥和一个私钥)，将其中的一个向外界公开，称为公钥；另一个自己保留，称为私钥
		KeyPair keyPair = rsaKeyFactory.generateNewOne();
		System.out.println(keyPair.getPublic());
		System.out.println(keyPair.getPublic().getFormat());
		System.out.println("----------------");
		System.out.println(keyPair.getPrivate());
		System.out.println(keyPair.getPrivate().getFormat());
		/*
		java生成的私钥是 pkcs8 格式，公钥是 x.509 格式。
		
		Sun RSA public key, 1024 bits
		  modulus: 94220742694642987167370066290706626513083750555338558812807596512169751397535455171592925838412287745015574948221833775622319410723224208808755842475698723900101765367190954711710744571588363228224943629132180004326842564080746147990551043628994490843773857191760859294020223007841420112821310272961475391823
		  public exponent: 65537
		X.509
		----------------
		sun.security.rsa.RSAPrivateCrtKeyImpl@1300f5
		PKCS#8
		* */
	}

	/**
	 * 测试生成公钥和私钥对
	 */
	@Test
	public void test01() throws InvalidKeySpecException {
		EncryptionUtil.RSAKeyFactory rsaKeyFactory = new EncryptionUtil.RSAKeyFactory();
		// 生成 RSA 得到的一个密钥对(即一个公钥和一个私钥)，将其中的一个向外界公开，称为公钥；另一个自己保留，称为私钥
		KeyPair keyPair = rsaKeyFactory.generateNewOne();

		byte[] publicBytes = EncryptionUtil.encryptBASE64(keyPair.getPublic().getEncoded());
		System.out.println(EncryptionUtil.byte2Hex(publicBytes));
		System.out.println("长度：" + EncryptionUtil.byte2Hex(publicBytes).length());

		byte[] privateBytes = EncryptionUtil.encryptBASE64(keyPair.getPrivate().getEncoded());
		System.out.println(EncryptionUtil.byte2Hex(privateBytes));
		System.out.println("长度：" + EncryptionUtil.byte2Hex(privateBytes).length());
		/*
		公钥：440
		4D4947664D413047435371475349623344514542415155414134474E4144434269514B42675143516F524E336178504A436258516A726762326E5A4A393842576A5052497A6A524668704D370D0A38716175623033695A586C5A5152544E45573948735858554E346935354A58317146582B4637483555684B6D3063342F2F50482F756F634E48797A73414A423144785762773331654B666B470D0A774E70762F4970483333414149636B6C742F4D706A52624952364D516952727454716B4E344F73503371766B4A786F306E61364F2B655A4E4F51494441514142
		私钥：1740
		4D494943646749424144414E42676B71686B6947397730424151454641415343416D417767674A6341674541416F4742414A43684533647245386B4A7464434F75427661646B6E337746614D0D0A39456A4F4E4557476B7A76797071357654654A6C65566C42464D30526230657864645133694C6E6B6C66576F5666345873666C53457162527A6A2F3838662B36687730664C4F77416B4855500D0A465A7644665634702B516241326D2F38696B6666634141687953573338796D4E467368486F78434A4775314F7151336736772F65712B516E476A5364726F3735356B303541674D42414145430D0A675941776A70504E6A52716F6257556F627A49717A383756434B502F717655394746582B586139432B6A5778374E61575177465A3050726432704F6B323153322B696F69567A64747361576C0D0A57476B395471764B624467315376326A446B4B724733336E34687A38434774304953493066487963512F5039647A3050335A65706D524D69584A616737752B6D3434415359746E594E6C65760D0A6769645270495A366D546F2F69356A656545657038514A42414E33326E7070597144424F5935794E415A4C6C6D492F6572394D317346586C756747537633657669457769726163797853656B0D0A7166563558534C743931766C4B687649583968365848576E3845696F6C4D4E41453430435151436D7A7147715069484168434E4978444474386C3131474C4D49424971767350487567765A350D0A617A32377967344269345347593337645537456E6A6669456451483231474E4F614C785346756E4E774B735362623964416B45416D3462763052536F484E6B416754526F557562667849647A0D0A79646E487968686A416A457953676E4C78685653656D4A667451787A4C7061776851596247642F305A6D303767334D526B4474786157662B664F564D44514A414341453466747632634B2B6D0D0A70644A4D6173374C723166644635426F4F32417062414736354437434F71584B6F455A61754C78446C556C7A3465734E4E77594B6278626F4952596A6D326A67465843656851626850514A410D0A652B64644B513747746843777058696B4D4C49416E436764444E6976336B37757A4F6472662B6D2F67696D4E3135623469716C77307179425131554236396745483044444A70484F556E754D0D0A6C632B4B4658716B44413D3D
		* */
	}

	/**
	 * 公钥加密，私钥解密测试
	 */
	@Test
	public void test02() throws UnsupportedEncodingException, InvalidKeySpecException {
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdvQ6tMKzpCwHu2i6QECJTjH/PGmc4n1Cn+7XX//0G3sE0tRjVM2YCSsK6XAf0nF5P/yfdTXZPiWfzBD2SjBS2P4IYXfFg2ugBmnjrp2E5QoVn739qlpr0sCNSiRVbPNARLRPYrJA80FIAoBReFQ/Yxm7O7MXqikSW0GR0c1MOuQIDAQAB";
		String privateKey = "MIICXQIBAAKBgQCdvQ6tMKzpCwHu2i6QECJTjH/PGmc4n1Cn+7XX//0G3sE0tRjVM2YCSsK6XAf0nF5P/yfdTXZPiWfzBD2SjBS2P4IYXfFg2ugBmnjrp2E5QoVn739qlpr0sCNSiRVbPNARLRPYrJA80FIAoBReFQ/Yxm7O7MXqikSW0GR0c1MOuQIDAQABAoGAAwe9v2/+8l8mw1rnqBbXQfmh/u2H7BuLZh68yJB/YOFonsWX24ioPgUHpqLb+EQEv86sCmFRk4eVOqLrN+R/BVV2jdnVTqtwbAluxSOXdkjv+eujwhuWjSrm7+BqdQaZDkz0PFSF/SZlgP5lsPuRUGqhq0y1uI+oG8RC4vQcNQECQQDpLgmvQbiAmkPnDfXWiSn0URO+3+6np3/g2q0VjSy6gZMrmpq607z/B371ZBl69Y9F5mMhP1vDjef9DPAaB60ZAkEArSzz93LCBk6pMYZ/ZpSTzKBURJ2AiipoHFOr4uRoCrp7+GrzXnTJwOQmL4fuIarcunda11CeWzdO+qf+je0CoQJBAL0isIxNPIBjoIw7kJpRa4xWbzO1o3itrlYOSbJfblZhX6RQpQzBTl12EQ6uANTxdSL3epSvNZlq8y8YXXBfpZECQQCUTXbwZjp6pEqIuyHHe13HU1ZXHHrD1+UovQ7qU6g159Xau9yG+4T7x2ZcNhBcjLJSjXwiAExc2VStm8iHSn2BAkAkqtMW5/Xh7SSiqxnPI1jALv8znUOuu/lslZKoJ5HLeMWMMlQTGvy5EnPIlgAKt4SU7SgmcqrIcsQJoKuv5rBx";

		String input = "wordplay";
		System.out.println("--- RSA 加密解密测试 ---");
		System.out.println("明文：" + input);
		System.out.println("length：" + input.length());

		// 使用公钥加密
		String cipherText = EncryptionUtil.encryptRSA(input, publicKey);
		System.out.println("密文：" + cipherText);

		java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		// 使用私钥解密
		String plainText = EncryptionUtil.decryptRSA(cipherText, privateKey);
		System.out.println("明文：" + plainText);
	}

	/**
	 * 读取密钥对文件，生成一行字符串格式的密钥对
	 */
	@Test
	public void test03() {
		String pub = readTxt(Thread.currentThread().getContextClassLoader().getResource("rsa_public_key.pem").getPath());
		String pubKey = pemToKey(pub);
		System.out.println(pubKey);

		String priv = readTxt(Thread.currentThread().getContextClassLoader().getResource("rsa_private_key.pem.pkcs8").getPath());
		String privateKey = pemToKey(priv);
		System.out.println(privateKey);
	}

	/**
	 * 从pem格式(-----BEGIN PUBLIC KEY-----)的key获取一行key
	 *
	 * @param pem
	 * @return
	 */
	public static String pemToKey(String pem) {
		if (pem == null) return "";
		if (pem.indexOf("KEY-----") > 0) {
			pem = pem.substring(pem.indexOf("KEY-----") + "KEY-----".length());
		}
		if (pem.indexOf("-----END") > 0) {
			pem = pem.substring(0, pem.indexOf("-----END"));
		}
		return pem.replace("\n", "");
	}

	private String readTxt(String filePath) {
		StringBuilder sb = new StringBuilder();
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
				BufferedReader br = new BufferedReader(isr);
				String lineTxt = null;
				while ((lineTxt = br.readLine()) != null) {
					sb.append(lineTxt);
				}
				br.close();
			} else {
				System.out.println("文件不存在!");
			}
		} catch (Exception e) {
			System.out.println("文件读取错误!");
		}
		return sb.toString();
	}

	/**
	 * 校验公钥私钥是否匹配
	 */
	@Test
	public void test04() throws UnsupportedEncodingException, InvalidKeySpecException {
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdvQ6tMKzpCwHu2i6QECJTjH/PGmc4n1Cn+7XX//0G3sE0tRjVM2YCSsK6XAf0nF5P/yfdTXZPiWfzBD2SjBS2P4IYXfFg2ugBmnjrp2E5QoVn739qlpr0sCNSiRVbPNARLRPYrJA80FIAoBReFQ/Yxm7O7MXqikSW0GR0c1MOuQIDAQAB";
		String privateKey = "MIICXQIBAAKBgQCdvQ6tMKzpCwHu2i6QECJTjH/PGmc4n1Cn+7XX//0G3sE0tRjVM2YCSsK6XAf0nF5P/yfdTXZPiWfzBD2SjBS2P4IYXfFg2ugBmnjrp2E5QoVn739qlpr0sCNSiRVbPNARLRPYrJA80FIAoBReFQ/Yxm7O7MXqikSW0GR0c1MOuQIDAQABAoGAAwe9v2/+8l8mw1rnqBbXQfmh/u2H7BuLZh68yJB/YOFonsWX24ioPgUHpqLb+EQEv86sCmFRk4eVOqLrN+R/BVV2jdnVTqtwbAluxSOXdkjv+eujwhuWjSrm7+BqdQaZDkz0PFSF/SZlgP5lsPuRUGqhq0y1uI+oG8RC4vQcNQECQQDpLgmvQbiAmkPnDfXWiSn0URO+3+6np3/g2q0VjSy6gZMrmpq607z/B371ZBl69Y9F5mMhP1vDjef9DPAaB60ZAkEArSzz93LCBk6pMYZ/ZpSTzKBURJ2AiipoHFOr4uRoCrp7+GrzXnTJwOQmL4fuIarcunda11CeWzdO+qf+je0CoQJBAL0isIxNPIBjoIw7kJpRa4xWbzO1o3itrlYOSbJfblZhX6RQpQzBTl12EQ6uANTxdSL3epSvNZlq8y8YXXBfpZECQQCUTXbwZjp6pEqIuyHHe13HU1ZXHHrD1+UovQ7qU6g159Xau9yG+4T7x2ZcNhBcjLJSjXwiAExc2VStm8iHSn2BAkAkqtMW5/Xh7SSiqxnPI1jALv8znUOuu/lslZKoJ5HLeMWMMlQTGvy5EnPIlgAKt4SU7SgmcqrIcsQJoKuv5rBx";
		boolean b = validatePubPrv(publicKey, privateKey);
		System.out.println("是否匹配：" + b);
	}

	public boolean validatePubPrv(String publicKey, String privateKey) throws UnsupportedEncodingException, InvalidKeySpecException {
		String input = "wordplay";

		// 使用公钥加密
		String cipherText = EncryptionUtil.encryptRSA(input, publicKey);
		System.out.println("密文：" + cipherText);

		java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		// 使用私钥解密
		String plainText = EncryptionUtil.decryptRSA(cipherText, privateKey);
		System.out.println("明文：" + plainText);

		// 使用 key 对象进行解密
		byte[] bytes = EncryptionUtil.decryptRSA(EncryptionUtil.decryptBASE64(cipherText.getBytes("UTF-8")),
				new EncryptionUtil.RSAKeyFactory().generatePrivateKeyByBase64Text(privateKey));
		String s = new String(bytes, "UTF-8");
		System.out.println(s);

		return input.equals(plainText) ? true : false;
	}

	/**
	 * 使用公钥加密，每次产生的密文是不一样的
	 */
	@Test
	public void test05() throws InvalidKeySpecException, UnsupportedEncodingException {
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdvQ6tMKzpCwHu2i6QECJTjH/PGmc4n1Cn+7XX//0G3sE0tRjVM2YCSsK6XAf0nF5P/yfdTXZPiWfzBD2SjBS2P4IYXfFg2ugBmnjrp2E5QoVn739qlpr0sCNSiRVbPNARLRPYrJA80FIAoBReFQ/Yxm7O7MXqikSW0GR0c1MOuQIDAQAB";
		String privateKey = "MIICXQIBAAKBgQCdvQ6tMKzpCwHu2i6QECJTjH/PGmc4n1Cn+7XX//0G3sE0tRjVM2YCSsK6XAf0nF5P/yfdTXZPiWfzBD2SjBS2P4IYXfFg2ugBmnjrp2E5QoVn739qlpr0sCNSiRVbPNARLRPYrJA80FIAoBReFQ/Yxm7O7MXqikSW0GR0c1MOuQIDAQABAoGAAwe9v2/+8l8mw1rnqBbXQfmh/u2H7BuLZh68yJB/YOFonsWX24ioPgUHpqLb+EQEv86sCmFRk4eVOqLrN+R/BVV2jdnVTqtwbAluxSOXdkjv+eujwhuWjSrm7+BqdQaZDkz0PFSF/SZlgP5lsPuRUGqhq0y1uI+oG8RC4vQcNQECQQDpLgmvQbiAmkPnDfXWiSn0URO+3+6np3/g2q0VjSy6gZMrmpq607z/B371ZBl69Y9F5mMhP1vDjef9DPAaB60ZAkEArSzz93LCBk6pMYZ/ZpSTzKBURJ2AiipoHFOr4uRoCrp7+GrzXnTJwOQmL4fuIarcunda11CeWzdO+qf+je0CoQJBAL0isIxNPIBjoIw7kJpRa4xWbzO1o3itrlYOSbJfblZhX6RQpQzBTl12EQ6uANTxdSL3epSvNZlq8y8YXXBfpZECQQCUTXbwZjp6pEqIuyHHe13HU1ZXHHrD1+UovQ7qU6g159Xau9yG+4T7x2ZcNhBcjLJSjXwiAExc2VStm8iHSn2BAkAkqtMW5/Xh7SSiqxnPI1jALv8znUOuu/lslZKoJ5HLeMWMMlQTGvy5EnPIlgAKt4SU7SgmcqrIcsQJoKuv5rBx";

		final String DEFAULT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCCV88D7F/aUTvB9F0ZHhGk++9MPHblXc8zIrj2\r\nHXWBH/xgNzqZUPhinzgKFmJQE5EZydQbv8BW0iPoHjYPe0Zo/MHzJkvniaMhkYej2ii1tuQm411e\r\n9KLCR19mZqc1b96O3PGRcM5t0GZxt+ZzDX17+S02l58KkiNrELfLtJDa7QIDAQAB";
		final String DEFAULT_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIJXzwPsX9pRO8H0XRkeEaT770w8\r\nduVdzzMiuPYddYEf/GA3OplQ+GKfOAoWYlATkRnJ1Bu/wFbSI+geNg97Rmj8wfMmS+eJoyGRh6Pa\r\nKLW25CbjXV70osJHX2ZmpzVv3o7c8ZFwzm3QZnG35nMNfXv5LTaXnwqSI2sQt8u0kNrtAgMBAAEC\r\ngYA2h7np8Eghs/6GJE+WpIfJOEVi5aDwUJDQ2eP7w1NXlCQntLNlVQS1bdsqDTdQPP9lhJSij1RO\r\nflfbUlhXncFUrbQE6DExArlKtGfffzTUh/FM4antpvigcMQaV/wnQO4g9vfK6sN1bk6fpPRAafGB\r\n7WrIBFf+GhIB5+c8DXdyWQJBAMThHlhghdl3Kh/sxqif+0sJAJjg/2K5mJQtlnbn3PmEDUV4dlqt\r\nDbqJKk9ZFc4asj1wy/ohRaaepsHA/ijfMtMCQQCpe8gCw/y4KU0ybQVGtQsyfdQd8OxczLpkB+9h\r\n6jDFLoL2m/1aOv56s+XqhfTlb0NsGqGi9m+BUcADqRBkPaM/AkEAiNewL91nK3AYZ3g73JjWIaFw\r\nONKqsAQweU32d09R2FxgnRjNqtj94dnU/rH85fTITsxH+uOFdfpwDdwGzIHW4QJBAILTBYT8++q8\r\nFtbByLzXqxkCf+XMvuazMX1dkq6mM5dBW944csxflWfpzaPGW09vH0AnHnXIfDH6hJlOu1RlL8kC\r\nQEvY9FTDxQN/xBahRTWwBQYRKJRCsloS+HOxvPdXsyV9pzrQ7epLYKvDQQxJm4PfP5rkE+wblJ3z\r\n/JoECAkTM1k=";


//		String input = "wordplay";
//
//		// 使用公钥加密
//		String cipherText = EncryptionUtil.encryptRSA(input, publicKey);
//		System.out.println("密文：" + cipherText);

		String encry01 = "AVYL3mqp4e+MoFH7qccLr19R84EeF8SCSyKfIOvLgAm2dCfhZ2cgsirXcfoUKq1VANtleI5RgFEZ\n" +
				"XdVnNqsksAAijzg6Ps/qD7/CqNGGkekG/2se+cZV9NNsLQSEL6yaQv0FqAOyN8uXBbwfuAGzHPOD\n" +
				"Vlp0hlmbMTiwmL/McuA=";

		String encry02 = "F8TN/IiHGLbtZx36gc7Oed9sDPL9ips8at3/K5IhXRp/1N0tPgqrTFvJY0JIalUwA0KjGLc6dsp8FdB+0LOo5jqtnsxpaxWuUK8nExrp7bgl3w4nLUWkhnm6PxeLPzEkepgwQo0skOAi+g4oo3Dbp0aY/krXTVvpHhwtkd5MGxc=";

		String encry03 = "KK5adZTWRAfYbbTZ9dyFKKsmgg7L4zi6805tM8YdtXSF7hwRuxWWKaw7wlVsSTqefWzhf0kI7alLOTbgCVE7Xx6Huodvt1SrchrQ8Xl1MQ3oNi3aa5SDqEJv4N7IlMHk3MbQjukDwveRghRrJ9mg81oo00G4bquNS4HYS2UKLOo=";

		byte[] decrypted = EncryptionUtil.decryptRSA(EncryptionUtil.decryptBASE64(encry03.getBytes("UTF-8")),
				new EncryptionUtil.RSAKeyFactory().generatePrivateKeyByBase64Text(DEFAULT_PRIVATE_KEY));
		String decryptedUserCode = new String(decrypted, "UTF-8");
		System.out.println(decryptedUserCode);

//		java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//		// 使用私钥解密
//		String plainText = EncryptionUtil.decryptRSA(encry02, DEFAULT_PUBLIC_KEY);
//		System.out.println("明文：" + plainText);
	}
	
	@Test
	public void test06(){
		String admin = EncryptionUtil.encryptMD5("admin");
		System.out.println(admin);
	}
	
}

