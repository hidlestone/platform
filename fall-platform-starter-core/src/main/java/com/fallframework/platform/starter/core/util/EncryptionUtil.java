package com.fallframework.platform.starter.core.util;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.UnsupportedCharsetException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 常用加密算法
 *
 * @author payn
 */
public class EncryptionUtil {

	public static String encryptBASE64(String plainText) {
		return encryptBASE64(plainText, "UTF-8");
	}

	public static String encryptBASE64(String plainText, String charset) {
		try {
			return (new BASE64Encoder()).encode(plainText.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedCharsetException(charset);
		}
	}

	public static byte[] encryptBASE64(byte[] plainText) {
		return (new BASE64Encoder()).encode(plainText).getBytes();
	}

	public static void encryptBASE64(InputStream is, OutputStream os) throws IOException {
		(new BASE64Encoder()).encodeBuffer(is, os);
	}

	public static String decryptBASE64(String cipherText) {
		return decryptBASE64(cipherText, "UTF-8");
	}

	public static String decryptBASE64(String cipherText, String charset) {
		try {
			return new String((new BASE64Decoder()).decodeBuffer(cipherText), charset);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public static byte[] decryptBASE64(byte[] cipherText) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			decryptBASE64((InputStream) (new ByteArrayInputStream(cipherText)), (OutputStream) baos);
			return baos.toByteArray();
		} catch (IOException var2) {
			throw new IllegalStateException(var2);
		}
	}

	public static void decryptBASE64(InputStream is, OutputStream os) throws IOException {
		(new BASE64Decoder()).decodeBuffer(is, os);
	}

	public static String encryptMD5(String text) {
		return encryptMD5(text, "UTF-8");
	}

	public static String encryptMD5(String text, String charset) {
		try {
			return encryptMD5(text.getBytes(charset));
		} catch (UnsupportedEncodingException var3) {
			throw new UnsupportedCharsetException(charset);
		}
	}

	public static String encryptMD5(byte[] text) {
		try {
			return encryptMD5((InputStream) (new ByteArrayInputStream(text)));
		} catch (IOException var2) {
			throw new IllegalStateException(var2);
		}
	}

	public static String encryptMD5(InputStream is) throws IOException {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[2048];
			boolean var3 = true;

			int length;
			while ((length = is.read(buffer)) != -1) {
				md5.update(buffer, 0, length);
			}

			return byte2Hex(md5.digest());
		} catch (NoSuchAlgorithmException var4) {
			throw new IllegalStateException(var4);
		}
	}

	public static String encryptSHA(String text) throws Exception {
		return new String(encryptSHA(text.getBytes()));
	}

	public static byte[] encryptSHA(byte[] data) throws Exception {
		MessageDigest sha = MessageDigest.getInstance("SHA");
		sha.update(data);
		return sha.digest();
	}

	public static String encrptSHA256(String text) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			return byte2Hex(messageDigest.digest(text.getBytes("UTF-8"))).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "Should never return this.";
	}

	public static String encryptAES(String data, String key) throws Exception {
		return encryptAES(data.getBytes("UTF-8"), key.getBytes("UTF-8"));
	}

	public static String decryptAES(String data, String key) throws Exception {
		return decryptAES(data.getBytes("UTF-8"), key.getBytes("UTF-8"));
	}

	public static String encryptAES(byte[] data, byte[] key) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(key));
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(1, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
		return Base64.encodeBase64String(cipher.doFinal(data));
	}

	public static String decryptAES(byte[] data, byte[] key) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(key));
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(2, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
		byte[] decryptBytes = cipher.doFinal(Base64.decodeBase64(data));
		return new String(decryptBytes);
	}

	public static String encryptRSA(String text, String publicKey) throws UnsupportedEncodingException, InvalidKeySpecException {
		byte[] encrypted = encryptRSA((byte[]) text.getBytes("UTF-8"), (Key) (new EncryptionUtil.RSAKeyFactory()).generatePublicKeyByBase64Text(publicKey));
		return new String(encryptBASE64(encrypted), "UTF-8");
	}

	public static String decryptRSA(String cipherText, String privateKey) throws UnsupportedEncodingException, InvalidKeySpecException {
		byte[] decrypted = decryptRSA((byte[]) decryptBASE64(cipherText.getBytes("UTF-8")), (Key) (new EncryptionUtil.RSAKeyFactory()).generatePrivateKeyByBase64Text(privateKey));
		return new String(decrypted, "UTF-8");
	}

	public static byte[] encryptRSA(byte[] plainText, Key key) {
		return encryptRSA(plainText, key, 117);
	}

	public static byte[] encryptRSA(byte[] plainText, Key key, int encryptSize) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(1, key);
			return cipher.doFinal(plainText);
		} catch (Exception var4) {
			throw new IllegalStateException(var4);
		}
	}

	public static byte[] decryptRSA(byte[] cipherText, Key key) {
		return decryptRSA(cipherText, key, 128);
	}

	public static byte[] decryptRSA(byte[] cipherText, Key key, int decryptSize) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(2, key);
			return cipher.doFinal(cipherText);
		} catch (Exception var4) {
			throw new IllegalStateException(var4);
		}
	}

	public static String byte2Hex(byte[] bytes) {
		char[] digital = "0123456789ABCDEF".toCharArray();
		StringBuilder out = new StringBuilder("");

		for (int i = 0; i < bytes.length; ++i) {
			int bit = (bytes[i] & 240) >> 4;
			out.append(digital[bit]);
			bit = bytes[i] & 15;
			out.append(digital[bit]);
		}

		return out.toString();
	}

	public static byte[] hex2Byte(byte[] bytes) {
		if (bytes.length % 2 != 0) {
			throw new IllegalArgumentException("长度不是偶数");
		} else {
			byte[] outs = new byte[bytes.length / 2];

			for (int n = 0; n < bytes.length; n += 2) {
				String item = new String(bytes, n, 2);
				outs[n / 2] = (byte) Integer.parseInt(item, 16);
			}
			return outs;
		}
	}

	public static class RSAKeyFactory {
		RSAPublicKey publicKey;
		RSAPrivateKey privateKey;
		KeyFactory keyFactory;
		KeyPairGenerator keyPairGen;
		int keySize;

		public RSAKeyFactory() {
			this(1024);
		}

		public RSAKeyFactory(int keySize) {
			try {
				this.keyPairGen = KeyPairGenerator.getInstance("RSA");
				this.keyPairGen.initialize(keySize);
				this.keyFactory = KeyFactory.getInstance("RSA");
			} catch (NoSuchAlgorithmException var3) {
				;
			}
		}

		public KeyPair generateNewOne() {
			KeyPair keyPair = this.keyPairGen.generateKeyPair();
			this.publicKey = (RSAPublicKey) keyPair.getPublic();
			this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
			return new KeyPair(this.publicKey, this.privateKey);
		}

		public RSAPublicKey getPublicKey() {
			return this.publicKey;
		}

		public RSAPrivateKey getPrivateKey() {
			return this.privateKey;
		}

		public int getKeySize() {
			return this.keySize;
		}

		public PrivateKey generatePrivateKeyByKeySpec(KeySpec keySpec) throws InvalidKeySpecException {
			this.privateKey = (RSAPrivateKey) this.keyFactory.generatePrivate(keySpec);
			return this.privateKey;
		}

		public PrivateKey generatePrivateKeyByBase64Text(String text) throws InvalidKeySpecException {
			byte[] keyBuf = EncryptionUtil.decryptBASE64(text.getBytes());
			return this.generatePrivateKeyByKeySpec(new PKCS8EncodedKeySpec(keyBuf));
		}

		public RSAPublicKey generatePublicKeyByKeySpec(KeySpec keySpec) throws InvalidKeySpecException {
			this.publicKey = (RSAPublicKey) this.keyFactory.generatePublic(keySpec);
			return this.publicKey;
		}

		public RSAPublicKey generatePublicKeyByBase64Text(String text) throws InvalidKeySpecException {
			byte[] keyBuf = EncryptionUtil.decryptBASE64(text.getBytes());
			return this.generatePublicKeyByKeySpec(new X509EncodedKeySpec(keyBuf));
		}

		public RSAPublicKey generatePublicKey(BigInteger modulus, BigInteger publicExponent) throws InvalidKeySpecException {
			return this.generatePublicKeyByKeySpec(new RSAPublicKeySpec(modulus, publicExponent));
		}

		public PrivateKey generatePrivateKey(BigInteger modulus, BigInteger privateExponent) throws InvalidKeySpecException {
			return this.generatePrivateKeyByKeySpec(new RSAPrivateKeySpec(modulus, privateExponent));
		}

		public boolean isKeyPair() {
			return this.publicKey != null ? this.publicKey.getModulus().equals(this.privateKey != null ? this.privateKey.getModulus() : null) : false;
		}

		public void generateKeyByBase64Text(String publicKey, String privateKey) throws InvalidKeySpecException {
			RSAPrivateKey pri = this.privateKey;
			RSAPublicKey pub = this.publicKey;

			try {
				this.generatePublicKeyByBase64Text(publicKey);
				this.generatePrivateKeyByBase64Text(privateKey);
			} catch (InvalidKeySpecException var6) {
				this.privateKey = pri;
				this.publicKey = pub;
				throw var6;
			}
		}
	}
}
