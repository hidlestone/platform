package com.fallframework.platform.starter.ureport.ftp;

import com.fallframework.platform.starter.ureport.exception.ConnectionPoolException;
import com.fallframework.platform.starter.ureport.properties.FTPProperties;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool.PoolableObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * FTPClient 工厂类， 提供FTPClient实例的创建、销毁、验证工作
 *
 * @author zhuangpf
 */
public class FTPClientFactory implements PoolableObjectFactory<FTPClient> {

	private Logger LOGGER = LoggerFactory.getLogger(FTPClientFactory.class);

	@Autowired
	private FTPProperties properties;

	/**
	 * 创建一个FTPClient对象
	 */
	@Override
	public FTPClient makeObject() throws Exception {
		FTPClient ftpClient = new FTPClient();
		/*ftpClient.setConnectTimeout(clientTimeout);*/
		ftpClient.connect(properties.getHostname(), properties.getPort());
		int replyCode = ftpClient.getReplyCode();
		if (!FTPReply.isPositiveCompletion(replyCode)) {
			ftpClient.disconnect();
			LOGGER.warn("FTPServer refuse connect");
			return null;
		}
		boolean login = ftpClient.login(properties.getUsername(), properties.getPassword());
		if (!login) {
			throw new FTPConnectionClosedException("FTPServer login fail");
		}
//		ftpClient.setControlEncoding("UTF-8");
		FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
		conf.setServerLanguageCode("zh");
		ftpClient.configure(conf);
		/*ftpClient.setFileType(fileType);
		ftpClient.setBufferSize(1024);
		ftpClient.setControlEncoding(encoding);
		if (passiveMode) {
           ftpClient.enterLocalPassiveMode();
        }*/
		return ftpClient;
	}

	/**
	 * 销毁一个FTPClient对象
	 */
	@Override
	public void destroyObject(FTPClient ftpClient) throws Exception {
		try {
			if (ftpClient != null && ftpClient.isConnected()) {
				ftpClient.logout();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ftpClient.disconnect();
		}
	}

	/**
	 * 验证一个FTPClient对象是否可用
	 */
	@Override
	public boolean validateObject(FTPClient ftpClient) {
		try {
			return ftpClient.sendNoOp();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ConnectionPoolException("FTPClient validate fail");
		}
	}

	@Override
	public void activateObject(FTPClient ftpClient) throws Exception {

	}

	@Override
	public void passivateObject(FTPClient ftpClient) throws Exception {

	}

}
