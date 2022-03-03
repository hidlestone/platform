package com.fallframework.platform.starter.ureport.ftp;

import com.fallframework.platform.starter.ureport.exception.ConnectionPoolException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;

import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * FTP 客户端连接池
 *
 * @author zhuangpf
 */
public class FTPClientPool implements ObjectPool<FTPClient> {

	private FTPClientFactory factory;

	private BlockingQueue<FTPClient> pool;

	public FTPClientPool(FTPClientFactory factory) {
		this(10, factory);
	}

	/**
	 * 构造方法
	 */
	public FTPClientPool(int maxPoolSize, FTPClientFactory factory) {
		this.factory = factory;
		this.pool = new ArrayBlockingQueue<>(maxPoolSize * 2);
		try {
			for (int i = 0; i < maxPoolSize; i++) {
				addObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 连接池创建失败
			throw new ConnectionPoolException("FTPClientPool create fial");
		}
	}

	/**
	 * 从连接池中取出一个对象
	 */
	@Override
	public FTPClient borrowObject() throws Exception, NoSuchElementException, IllegalStateException {
		FTPClient ftpClient = pool.take();
		if (ftpClient == null) {
			ftpClient = factory.makeObject();
			addObject();
		} else if (!factory.validateObject(ftpClient)) {
			// 从连接池中失效
			invalidateObject(ftpClient);
			// 制造新的对象，并添加到池中
			ftpClient = factory.makeObject();
			addObject();
		}
		return ftpClient;
	}

	/**
	 * 归还一个对象到连接池中
	 */
	@Override
	public void returnObject(FTPClient ftpClient) throws Exception {
		if (ftpClient != null && !pool.offer(ftpClient, 3, TimeUnit.SECONDS)) {
			factory.destroyObject(ftpClient);
		}
	}

	/**
	 * 从连接池中移除一个对象
	 */
	@Override
	public void invalidateObject(FTPClient client) throws Exception {
		pool.remove(client);
	}

	/**
	 * 添加对象到连接池
	 */
	@Override
	public void addObject() throws Exception, IllegalStateException, UnsupportedOperationException {
		pool.offer(factory.makeObject(), 3, TimeUnit.SECONDS);
	}

	@Override
	public int getNumIdle() throws UnsupportedOperationException {
		return 0;
	}

	@Override
	public int getNumActive() throws UnsupportedOperationException {
		return 0;
	}

	@Override
	public void clear() throws Exception, UnsupportedOperationException {
	
	}

	/**
	 * 关闭对象池，清理内存释放资源等
	 */
	@Override
	public void close() throws Exception {
		while (pool.size() > 0) {
			FTPClient ftpClient = pool.take();
			factory.destroyObject(ftpClient);
		}
	}

	/**
	 * 需要一个工厂来制造池中的对象
	 */
	@Override
	public void setFactory(PoolableObjectFactory<FTPClient> poolableObjectFactory) throws IllegalStateException, UnsupportedOperationException {

	}
}
