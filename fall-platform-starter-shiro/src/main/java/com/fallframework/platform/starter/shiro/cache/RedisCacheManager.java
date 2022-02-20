package com.fallframework.platform.starter.shiro.cache;

import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.config.service.PlatformSysParamUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * shiro redis缓存管理器
 *
 * @author zhuangpf
 */
public class RedisCacheManager implements CacheManager {

	private PlatformSysParamUtil platformSysParamUtil;
	private RedisUtil redisUtil;

	public RedisCacheManager(PlatformSysParamUtil platformSysParamUtil, RedisUtil redisUtil) {
		this.platformSysParamUtil = platformSysParamUtil;
		this.redisUtil = redisUtil;
	}

	@Override
	public <K, V> Cache<K, V> getCache(String s) throws CacheException {
		return new RedisCache<K, V>(platformSysParamUtil,redisUtil);
	}

}
