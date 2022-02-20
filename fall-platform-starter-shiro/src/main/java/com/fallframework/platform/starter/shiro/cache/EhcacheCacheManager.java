package com.fallframework.platform.starter.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * shiro ehcache缓存管理器
 *
 * @author zhuangpf
 */
public class EhcacheCacheManager implements CacheManager {

	@Override
	public <K, V> Cache<K, V> getCache(String s) throws CacheException {
		return new EhcacheCache();
	}

}
