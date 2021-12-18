package com.fallframework.platform.starter.cache.ehcache.util;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.io.Serializable;

/**
 * EHCache 缓存工具类
 *
 * @author zhaungpf
 */
public class EHCacheUtil {

	static CacheManager manager = null;
	static String configfile = "ehcache.xml";

	//EHCache初始化
	static {
		try {
			manager = CacheManager.create(EHCacheUtil.class.getClassLoader().getResourceAsStream(configfile));
		} catch (CacheException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将数据存入Cache
	 *
	 * @param cachename Cache名称
	 * @param key       类似redis的Key
	 * @param value     类似redis的value，value可以是任何对象、数据类型，比如person,map,list等
	 */
	public static void put(String cachename, Serializable key, Serializable value) {
		manager.getCache(cachename).put(new Element(key, value));
	}

	/**
	 * 获取缓存cachename中key对应的value
	 *
	 * @param cachename
	 * @param key
	 * @return
	 */
	public static Serializable get(String cachename, Serializable key) {
		try {
			Element e = manager.getCache(cachename).get(key);
			if (e == null) {
				return null;
			}
			return e.getValue();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (CacheException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 清除缓存名称为cachename的缓存
	 *
	 * @param cachename
	 */
	public static void clearCache(String cachename) {
		try {
			manager.getCache(cachename).removeAll();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 移除缓存cachename中key对应的value
	 *
	 * @param cachename
	 * @param key
	 */
	public static void remove(String cachename, Serializable key) {
		manager.getCache(cachename).remove(key);
	}
}
