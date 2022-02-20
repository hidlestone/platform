package com.fallframework.platform.starter.shiro.cache;

import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.config.model.SysParamItemResponse;
import com.fallframework.platform.starter.config.service.PlatformSysParamUtil;
import com.fallframework.platform.starter.shiro.constant.ShiroStarterConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 自定义shiro redis缓存实现
 *
 * @author zhuangpf
 */
public class RedisCache<K, V> implements Cache<K, V> {

	private PlatformSysParamUtil platformSysParamUtil;
	private RedisUtil redisUtil;

	public RedisCache(PlatformSysParamUtil platformSysParamUtil, RedisUtil redisUtil) {
		this.platformSysParamUtil = platformSysParamUtil;
		this.redisUtil = redisUtil;
	}

	/**
	 * 缓存的key名称为 shiro:cache:{account}
	 *
	 * @param key 即token
	 * @return 缓存key
	 */
	private String getKey(Object key) {
//		return ShiroStarterConstant.PREFIX_SHIRO_CACHE + jwtUtil.getClaim(key.toString(), ShiroStarterConstant.ACCOUNT);
		return ShiroStarterConstant.PREFIX_SHIRO_CACHE + key.toString();
	}

	/**
	 * 获取缓存<br/>
	 * 即key，shiro:cache:{account} 对应数据
	 */
	@Override
	public Object get(Object k) throws CacheException {
		return redisUtil.get(this.getKey(k));
	}

	/**
	 * 保存缓存
	 */
	@Override
	public Object put(Object k, Object v) throws CacheException {
		// 获取shiro缓存过期时间-5分钟-5*60(秒为单位)，(一般设置与AccessToken过期时间一致)
		SysParamItemResponse sysParamItem = platformSysParamUtil.getSysParamItem(ShiroStarterConstant.SYS_PARAM_GROUP, ShiroStarterConstant.CACHE_KEY_CACHE_EXPIRE_TIME).getData();
		if (null == sysParamItem || StringUtils.isEmpty(sysParamItem.getValue())) {
			throw new RuntimeException("SHIRO CACHE_EXPIRE_TIME is not exist.");
		}
		return redisUtil.set(this.getKey(k), v, Long.parseLong(sysParamItem.getValue()));
	}

	/**
	 * 移除缓存
	 */
	@Override
	public Object remove(Object k) throws CacheException {
		redisUtil.del(k.toString());
		return null;
	}

	/**
	 * 清空所有(前缀为shiro:cache:)的缓存
	 */
	@Override
	public void clear() throws CacheException {
		// 获取所有key
		Set<String> keys = redisUtil.keys(ShiroStarterConstant.PREFIX_SHIRO_CACHE);
		// 批量删除所有
		redisUtil.del(keys);
	}

	/**
	 * 缓存的个数
	 */
	@Override
	public int size() {
		// 获取所有key
		Set<String> keys = redisUtil.keys(ShiroStarterConstant.PREFIX_SHIRO_CACHE);
		return keys.size();
	}

	/**
	 * 获取所有的key
	 */
	@Override
	public Set keys() {
		Set<String> keys = redisUtil.keys(ShiroStarterConstant.PREFIX_SHIRO_CACHE);
		return keys;
	}

	/**
	 * 获取所有的value
	 */
	@Override
	public Collection values() {
		Set<String> keys = this.keys();
		List<Object> values = redisUtil.getValues(keys);
		return values;
	}

}
