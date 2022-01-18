package com.fallframework.platform.starter.core.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 应用环境初始化器<br>
 * ServletContextListener 监听 tomcat 的启动和关闭
 * <p>
 * Order(-2147483648) 控制配置类的加载顺序，值越小越先加载
 *
 * @author zhuangpf
 */
@Component
@Order(-2147483648)
public class ApplicationContextInitializer implements ServletContextListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextInitializer.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		if (sce.getServletContext().getAttribute("org.springframework.web.context.WebApplicationContext.ROOT") != null) {
			ApplicationContext ctx = (ApplicationContext) sce.getServletContext().getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
			// 初始化ceep应用上下文环境
			ApplicationContextFallPlatform.setApplicationContext(ctx);
			LOGGER.info("fall platform context initialized : " + ctx);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LOGGER.info("fall platform context destroyed.");
	}
}
