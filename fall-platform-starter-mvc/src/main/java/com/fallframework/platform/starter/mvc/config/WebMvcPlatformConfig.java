package com.fallframework.platform.starter.mvc.config;

import com.fallframework.platform.starter.config.model.SysParamItemResponse;
import com.fallframework.platform.starter.config.service.PlatformSysParamService;
import com.fallframework.platform.starter.mvc.filter.CurrentContextFilter;
import com.fallframework.platform.starter.mvc.filter.LanguageFilter;
import com.fallframework.platform.starter.mvc.filter.XSSFilter;
import com.fallframework.platform.starter.mvc.interceptor.CommonInfoInterceptor;
import com.fallframework.platform.starter.mvc.interceptor.TokenInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;

/**
 * @author zhuangpf
 */
@Configuration
@Import({FeignClientsConfiguration.class})
@Order(2147483647)
public class WebMvcPlatformConfig implements WebMvcConfigurer {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebMvcPlatformConfig.class);
	@Autowired(required = false)
	private static PlatformSysParamService platformSysParamService;

	public static final int LANGUAGE_FILTER_ORDER;
	public static final int CURRENT_CONTEXT_FILTER_ORDER;
	public static final int SIGNATURE_FILTER_ORDER;
	public static final int XSS_FILTER_ORDER;

	static {
		Map<String, String> sysItemMap = platformSysParamService.getSysParamGroupItemMap("FILTER_ORDER").getData();
		LANGUAGE_FILTER_ORDER = Integer.valueOf(sysItemMap.get("LANGUAGE_FILTER_ORDER"));
		CURRENT_CONTEXT_FILTER_ORDER = Integer.valueOf(sysItemMap.get("CURRENT_CONTEXT_FILTER_ORDER"));
		SIGNATURE_FILTER_ORDER = Integer.valueOf(sysItemMap.get("SIGNATURE_FILTER_ORDER"));
		XSS_FILTER_ORDER = Integer.valueOf(sysItemMap.get("XSS_FILTER_ORDER"));
	}

	/**
	 * 异步请求(Async-Support)配置 ASYNC_SUPPORT_CONFIG
	 */
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		SysParamItemResponse sysParamItem = platformSysParamService.getSysParamItem("ASYNC_SUPPORT", "ASYNC_SUPPORT_TIMEOUT").getData();
		// 设置默认的超时时间，（毫秒，Tomcat下默认是10000毫秒，即10秒）
		configurer.setDefaultTimeout(Integer.valueOf(sysParamItem.getValue()));
		// 注册异步的拦截器
		configurer.registerCallableInterceptors(new CallableProcessingInterceptor[]{this.timeoutInterceptor()});
		// 设定异步请求线程池callable等, spring默认线程不可重用
		configurer.setTaskExecutor(this.threadPoolTaskExecutor());
	}

	/**
	 * 异步请求(Async-Support)线程池
	 */
	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		Map<String, String> sysItemMap = platformSysParamService.getSysParamGroupItemMap("ASYNC_SUPPORT").getData();
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(Integer.valueOf(sysItemMap.get("ASYNC_SUPPORT_CORE_POOL_SIZE")));
		executor.setMaxPoolSize(Integer.valueOf(sysItemMap.get("ASYNC_SUPPORT_MAX_POOL_SIZE")));
		executor.setQueueCapacity(Integer.valueOf(sysItemMap.get("ASYNC_SUPPORT_QUEUE_CAPACITY")));
		executor.setThreadNamePrefix(sysItemMap.get("ASYNC_SUPPORT_THREAD_NAME_PREFIX"));
		return executor;
	}

	/**
	 * 异步请求超时拦截器
	 */
	@Bean
	public TimeoutCallableProcessingInterceptor timeoutInterceptor() {
		return new TimeoutCallableProcessingInterceptor();
	}

	/**
	 * 跨域资源共享（Cross-origin resource sharing）<br>
	 * 设置跨域请求参数<br>
	 * Access-Control-Allow-Credentials：该响应头非必须，值是bool类型，表示是否允许发送Cookie。<br>
	 * Access-Control-Allow-Origin：该响应头是服务器必须返回的。它的值要么是请求时Origin的值（可从request里获取），要么是*这样浏览器才会接受服务器的返回结果。<br>
	 * Access-Control-Allow-Headers：表示我服务器支持的所有头字段，不限于预检请求中的头字段。<br>
	 * Access-Control-Max-Age：表示需要缓存预检结果多长时间，单位是秒。<br>
	 */
	@Bean
	public CorsFilter corsFilter() {
		Map<String, String> sysItemMap = platformSysParamService.getSysParamGroupItemMap("ASYNC_SUPPORT").getData();
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(Boolean.valueOf(sysItemMap.get("ALLOW_CREDENTIALS")));
		corsConfiguration.addAllowedOrigin(sysItemMap.get("ALLOWED_ORIGIN"));
		corsConfiguration.addAllowedHeader(sysItemMap.get("ALLOWED_HEADER"));
		corsConfiguration.addAllowedMethod(sysItemMap.get("ALLOWED_METHOD"));
		corsConfiguration.setMaxAge(Long.valueOf(sysItemMap.get("MAX_AGE")));
		source.registerCorsConfiguration(sysItemMap.get("REGISTER_PATH"), corsConfiguration);
		return new CorsFilter(source);
	}

	/**
	 * 注册拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 公共拦截器
		registry.addInterceptor(new CommonInfoInterceptor()).addPathPatterns(new String[]{"/**"});
		// 证书校验拦截器
//		registry.addInterceptor(new LicenseInterceptor()).addPathPatterns(new String[]{"/**"});
		// token 拦截器
		registry.addInterceptor(new TokenInterceptor()).addPathPatterns(new String[]{"/**"});
	}

	/**
	 * Filter：多语言过滤器
	 */
	@Bean
	public FilterRegistrationBean<LanguageFilter> languageFilter() {
		FilterRegistrationBean<LanguageFilter> bean = new FilterRegistrationBean();
		bean.setFilter(new LanguageFilter());
		bean.addUrlPatterns(new String[]{"/*"});
		bean.setOrder(LANGUAGE_FILTER_ORDER);
		return bean;
	}

	/**
	 * Filter：当前请求上下文过滤器
	 */
	@Bean
	public FilterRegistrationBean<CurrentContextFilter> currentContextFilter() {
		FilterRegistrationBean<CurrentContextFilter> bean = new FilterRegistrationBean();
		bean.setFilter(new CurrentContextFilter());
		bean.addUrlPatterns(new String[]{"/*"});
		bean.setOrder(CURRENT_CONTEXT_FILTER_ORDER);
		return bean;
	}

	/**
	 * Filter：防止表单重复提交的过滤器
	 */
	@Bean
	public FilterRegistrationBean<RepeatSignatureValidateFilter> repeatSignatureValidateFilter() {
		FilterRegistrationBean<RepeatSignatureValidateFilter> bean = new FilterRegistrationBean();
		bean.setFilter(new RepeatSignatureValidateFilter());
		bean.addUrlPatterns(new String[]{"/*"});
		bean.setOrder(SIGNATURE_FILTER_ORDER);
		return bean;
	}

	/**
	 * Filter：
	 */
	@Bean
	public FilterRegistrationBean<XSSFilter> xssFilter() {
		FilterRegistrationBean<XSSFilter> bean = new FilterRegistrationBean();
		bean.setFilter(new XSSFilter());
		bean.addUrlPatterns(new String[]{"/*"});
		bean.setOrder(XSS_FILTER_ORDER);
		return bean;
	}

}
