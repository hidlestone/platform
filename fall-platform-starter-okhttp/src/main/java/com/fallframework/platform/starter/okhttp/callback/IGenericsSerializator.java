package com.fallframework.platform.starter.okhttp.callback;

/**
 * Created by JimGong on 2016/6/23.
 */
public interface IGenericsSerializator {

	<T> Object transform(String response, Class<T> classOfT);
	
}
