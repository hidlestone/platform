package com.fallframework.platform.starter.okhttp.util;

public class Exceptions {

	public static void illegalArgument(String msg, Object... params) {
		throw new IllegalArgumentException(String.format(msg, params));
	}
	
}
