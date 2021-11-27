package com.fallframework.platform.starter.core.util;

import com.fallframework.platform.starter.core.context.ApplicationContextFallPlatform;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Properties;

/**
 * @author zhuangpf
 */
public class ApplicationInstanceManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationInstanceManager.class);

	/**
	 * 服务名称
	 */
	private static String serviceName;
	/**
	 * 服务实例名称
	 */
	private static String applicationInstanceName;
	/**
	 * 服务实例id
	 */
	private static Long applicationInstanceId;

	/**
	 * 获取服务名称
	 * service-test1.0
	 */
	public static String getServiceName() {
		if (serviceName == null) {
			try {
				Registration registration = (Registration) ApplicationContextFallPlatform.getBean(Registration.class);
				if (registration != null) {
					serviceName = registration.getServiceId();
				}
			} catch (Exception e) {
				serviceName = getApplicationName();
			}
		}
		return serviceName;
	}

	/**
	 * 获取应用名称：应用名称+版本号，如service-test1.0
	 */
	public static String getApplicationName() {
		YamlPropertiesFactoryBean yamlMapFactoryBean = new YamlPropertiesFactoryBean();
		yamlMapFactoryBean.setResources(new Resource[]{new ClassPathResource("application.yml")});
		Properties properties = yamlMapFactoryBean.getObject();
		String applicationName = properties.getProperty("spring.application.name");
		String version = properties.getProperty("platform.version");
		if (StringUtils.isNotBlank(version)) {
			applicationName = applicationName + version;
		}
		return applicationName;
	}

	/**
	 * 获取应用实例名称：<br>
	 * 实例ID+主机<br>
	 * 应用名称+版本号+主机名称<br>
	 */
	public static String getApplicationInstanceName() {
		if (null == applicationInstanceName) {
			try {
				Registration registration = null;
				registration = (Registration) ApplicationContextFallPlatform.getBean(Registration.class);
				if (null != registration) {
					applicationInstanceName = registration.getInstanceId() + "-" + registration.getHost();
				}
			} catch (Exception var1) {
				applicationInstanceName = getApplicationName() + "-" + System.getenv("COMPUTERNAME");
			}
		}
		return applicationInstanceName;
	}

}
