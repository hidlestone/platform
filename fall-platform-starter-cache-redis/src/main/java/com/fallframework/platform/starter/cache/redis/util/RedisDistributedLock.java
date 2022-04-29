package com.fallframework.platform.starter.cache.redis.util;

import com.fallframework.platform.starter.config.model.SysParamGroupEnum;
import com.fallframework.platform.starter.config.service.PlatformSysParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Redis分布式锁简单实现<br>
 * <p>
 * 加锁：if redis.call("setnx", KEYS[1], KEYS[2]) == 1 then redis.call("setex", KEYS[1], KEYS[3], KEYS[2]) return 1 else return 2 end;<br>
 * 解锁：if redis.call("get", KEYS[1]) == KEYS[2] then return redis.call("del", KEYS[1]) else return 2 end;<br>
 * <p>
 * Redis Setnx（SET if Not eXists） 命令在指定的 key 不存在时，为 key 设置指定的值。如：SETNX KEY_NAME VALUE <br>
 * Redis Setex 命令为指定的 key 设置值及其过期时间。如果 key 已经存在， SETEX 命令将会替换旧的值。如：SETEX KEY_NAME TIMEOUT VALUE <br>
 * <p>
 * 加锁成功返回1，失败返回2。<br>
 * 解锁成功返回1，锁不存在/未找到锁返回0，锁名和值不匹配返回2。<br>
 *
 * @author zhuangpf
 */
@Component
public class RedisDistributedLock {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisDistributedLock.class);

	// redis 分布式锁自动释放时间(ms)
	private final Long REDIS_LOCK_AUTO_RELEASE_TIME;
	// redis 分布式锁缓冲时间(ms)
	private final Long REDIS_LOCK_BUFFER;
	// 是否允许缓冲
	private final Boolean REDIS_LOCK_BUFFER_ENABLED;
	// redis 分布式锁加锁超时时间(ms)
	private final Long REDIS_LOCK_TIMEOUT;

	// lua脚本：释放锁
	private static final String LUA_UNLOCK_SCRIPT = "if redis.call(\"get\", KEYS[1]) == KEYS[2] then return redis.call(\"del\", KEYS[1]) else return 2 end;";
	// lua脚本：加锁
	private static final String LUA_LOCK_SCRIPT = "if redis.call(\"setnx\", KEYS[1], KEYS[2]) == 1 then redis.call(\"setex\", KEYS[1], KEYS[3], KEYS[2]) return 1 else return 2 end;";

	private RedisTemplate<String, Object> redisTemplate;

	public RedisDistributedLock(PlatformSysParamUtil platformSysParamUtil, RedisTemplate<String, Object> redisTemplate) {
		// 从缓存中获取配置参数：DISTRIBUTED_LOCK
		Map<String, String> sysItemMap = platformSysParamUtil.getSysParamGroupItemMap(SysParamGroupEnum.DISTRIBUTED_LOCK.toString()).getData();
		REDIS_LOCK_AUTO_RELEASE_TIME = Long.valueOf(platformSysParamUtil.mapGet(sysItemMap, "REDIS_LOCK_AUTO_RELEASE_TIME"));
		REDIS_LOCK_BUFFER = Long.valueOf(platformSysParamUtil.mapGet(sysItemMap, "REDIS_LOCK_BUFFER"));
		REDIS_LOCK_BUFFER_ENABLED = Boolean.valueOf(platformSysParamUtil.mapGet(sysItemMap, "REDIS_LOCK_BUFFER_ENABLED"));
		REDIS_LOCK_TIMEOUT = Long.valueOf(platformSysParamUtil.mapGet(sysItemMap, "REDIS_LOCK_TIMEOUT"));
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 加锁
	 */
	public void lock(String lockName, String value) throws Exception {
		lock(lockName, value, null, null, null);
	}

	/**
	 * 加锁，带超时时间(ms)
	 */
	public void lock(String lockName, String value, Long timeoutMilliseconds) throws Exception {
		lock(lockName, value, null, timeoutMilliseconds, null);
	}

	/**
	 * 加锁操作
	 */
	public void lock(String lockName, String value, Long autoReleaseMilliseconds, Long timeoutMilliseconds, Long bufferMilliseconds) throws Exception {
		// 开始时间
		long st = System.currentTimeMillis();
		long timeout = REDIS_LOCK_TIMEOUT;
		if (timeoutMilliseconds != null) {
			timeout = timeoutMilliseconds;
		}
		// 系统默认分布式锁自动释放时间
		long expireTime = REDIS_LOCK_AUTO_RELEASE_TIME;
		if (autoReleaseMilliseconds != null) {
			expireTime = autoReleaseMilliseconds;
		}
		// 结束时间
		long et;
		do {
			// 尝试获取锁
			if (tryLock(lockName, value, expireTime)) {
				LOGGER.debug("Get lock in " + (System.currentTimeMillis() - st) + " milliseconds");
				return;
			}
			try {
				// 缓冲时间
				if (bufferMilliseconds != null) {
					Thread.sleep(bufferMilliseconds);
				} else if (REDIS_LOCK_BUFFER_ENABLED) {
					// 缓冲睡眠
					Thread.sleep(REDIS_LOCK_BUFFER);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			et = System.currentTimeMillis();
		} while (et - st <= timeout);
		// 抛出异常：未获取到锁
		throw new TimeoutException("Waiting for distributed lock timeout in " + timeout + " milliseconds.");
	}

	/**
	 * 释放锁
	 */
	public void unlock(String lockName, String value) throws Exception {
		// 开始时间
		Long st = System.currentTimeMillis();
		// 执行lua脚本释放锁
		Long result = deleteIfEquals(lockName, value);
		if (result == 0L) {
			// 未找到锁
			throw new RuntimeException("Cannot find lock [" + lockName + "] with value [" + value + "], maybe lock released due to expired.");
		} else if (result == 2L) {
			// 锁名和值不匹配
			throw new RuntimeException("Lock [" + lockName + "] mismatched with value [" + value + "], maybe the value you supplied is incorrect or lock released due to expired and another thread gets the lock already.");
		} else {
			// 成功释放
			LOGGER.debug("Lock released in " + (System.currentTimeMillis() - st) + " milliseconds");
		}
	}

	/**
	 * 尝试加锁
	 */
	private boolean tryLock(String lockName, String value, Long expireMilliseconds) {
		boolean locked = setAndExpireIfAbsent(lockName, value, expireMilliseconds);
		return locked;
	}

	public boolean setAndExpireIfAbsent2(final String key, final String value, final long expire) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				Object obj = null;

				try {
					obj = connection.execute("set", new byte[][]{key.getBytes("UTF-8"), value.getBytes("UTF-8"), "NX".getBytes("UTF-8"), "EX".getBytes("UTF-8"), String.valueOf(expire).getBytes("UTF-8")});
				} catch (IOException e) {
					e.printStackTrace();
				}

				return obj != null;
			}
		});
	}

	/**
	 * redis加锁
	 */
	public boolean setAndExpireIfAbsent(String key, String value, long expire) {
		DefaultRedisScript<Long> script = new DefaultRedisScript(LUA_LOCK_SCRIPT);
		script.setResultType(Long.class);
		Long result = redisTemplate.execute(script, Arrays.asList(key, value, String.valueOf(expire / 1000L)), new Object[0]);
		return result == 1L;
	}

	/**
	 * redis解锁
	 */
	public Long deleteIfEquals(String key, String value) throws Exception {
		DefaultRedisScript<Long> script = new DefaultRedisScript(LUA_UNLOCK_SCRIPT);
		script.setResultType(Long.class);
		Long result = redisTemplate.execute(script, Arrays.asList(key, value), new Object[0]);
		return result;
	}

}
