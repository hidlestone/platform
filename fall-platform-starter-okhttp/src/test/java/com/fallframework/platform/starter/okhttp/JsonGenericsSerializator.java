package com.fallframework.platform.starter.okhttp;

import com.alibaba.fastjson.JSON;
import com.fallframework.platform.starter.okhttp.callback.IGenericsSerializator;

/**
 * Created by JimGong on 2016/6/23.
 */
public class JsonGenericsSerializator implements IGenericsSerializator {

	@Override
	public <T> T transform(String response, Class<T> classOfT) {
		return JSON.parseObject(response, classOfT);
	}

}
