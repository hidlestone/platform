package com.fallframework.platform.starter.core.util;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author payn
 * @date 2021/6/3 17:46
 */
public class PasswordUtils {

	public static final String NUMBER_RANGE = "0123456789";
	public static final String SYMBOL_RANGE = "!?@#$%^&*~+-_";
	public static final String LETTER_RANGE = "abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
	public static final Pattern DIGITAL_PATTERN = Pattern.compile(".*\\d{1,}.*");
	public static final Pattern ALPHABET_PATTERN = Pattern.compile(".*[a-zA-Z]{1,}.*");

	public PasswordUtils() {
	}

	public static String encrptSHA256(String text) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			return byte2Hex(messageDigest.digest(text.getBytes("UTF-8"))).toUpperCase();
		} catch (NoSuchAlgorithmException var2) {
			var2.printStackTrace();
		} catch (UnsupportedEncodingException var3) {
			var3.printStackTrace();
		}

		return "Should never return this.";
	}

	private static String byte2Hex(byte[] bytes) {
		StringBuffer stringBuffer = new StringBuffer();
		String temp = null;

		for (int i = 0; i < bytes.length; ++i) {
			temp = Integer.toHexString(bytes[i] & 255);
			if (temp.length() == 1) {
				stringBuffer.append("0");
			}

			stringBuffer.append(temp);
		}

		return stringBuffer.toString();
	}

	public static String create(int len, boolean includeNumber, boolean includeLetter, boolean includeSymbol) {
		String range = "";
		if (includeNumber) {
			range = range + "0123456789";
		}

		if (includeLetter) {
			range = range + "abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
		}

		if (includeSymbol) {
			range = range + "!?@#$%^&*~+-_";
		}

		if (StringUtils.isBlank(range)) {
			throw new IllegalArgumentException("至少启用一种密码字典。");
		} else {
			char[] charr = range.toCharArray();
			StringBuilder sb = new StringBuilder();
			Random r = new Random();

			for (int x = 0; x < len; ++x) {
				sb.append(charr[r.nextInt(charr.length)]);
			}

			String result = sb.toString();
			return includeNumber && !containsDigital(result) || includeLetter && !containsAlphabet(result) || includeSymbol && !containsSpecialSymbol(result) ? create(len, includeNumber, includeLetter, includeSymbol) : result;
		}
	}

	public static boolean containsDigital(String s) {
		return DIGITAL_PATTERN.matcher(s).matches();
	}

	public static boolean containsAlphabet(String s) {
		return ALPHABET_PATTERN.matcher(s).matches();
	}

	public static boolean containsSpecialSymbol(String s) {
		char[] var1 = "!?@#$%^&*~+-_".toCharArray();
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			char c = var1[var3];
			if (s.contains(String.valueOf(c))) {
				return true;
			}
		}

		return false;
	}
}
