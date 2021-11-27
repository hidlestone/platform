package com.fallframework.platform.starter.core.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * 应用上下文环境、基础配置<br>
 * 对 ApplicationContext 做一层封装
 *
 * @author payn
 */
public class ApplicationContextFallPlatform {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextFallPlatform.class);

	/**
	 * 应用上下文环境
	 */
	private static ApplicationContext applicationContext;

	/**
	 * 设置上下文环境
	 */
	public static void setApplicationContext(ApplicationContext ac) {
		if (applicationContext == null) {
			applicationContext = ac;
			LOGGER.info("FallPlatformApplicationContext ready to use: " + applicationContext);
		}
	}

	/**
	 * 获取上下文环境
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取环境对象
	 */
	public static Environment getEnvironment() {
		return (Environment) getBean(Environment.class);
	}

	/**
	 * 获取应用名称
	 */
	public static String getApplicationName() {
		Environment env = getEnvironment();
		return env.getProperty("spring.application.name");
	}

	/**
	 * 获取bean对象
	 */
	public static <T> T getBean(String name, Class<T> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}

	/**
	 * 获取bean对象
	 */
	public static <T> T getBean(Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
	}

	/**
	 * 获取bean对象map
	 */
	public static <T> Map<String, T> getBeansOfType(Class<T> requiredType) {
		return BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, requiredType);
	}

	/**
	 * 根据注解获取bean
	 */
	public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
		return applicationContext.getBeansWithAnnotation(annotationType);
	}

	/**
	 * 获取bean对象
	 */
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	/**
	 * 获取环境参数
	 */
	public static String getProperty(String key) {
		return applicationContext.getEnvironment().getProperty(key);
	}

	/**
	 * 获取数据源
	 */
	public static DataSource getDataSource() {
		return (DataSource) applicationContext.getBean(DataSource.class);
	}

}
