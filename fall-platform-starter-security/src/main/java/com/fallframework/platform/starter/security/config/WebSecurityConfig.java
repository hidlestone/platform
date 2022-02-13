package com.fallframework.platform.starter.security.config;

import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.rbac.service.PermissionService;
import com.fallframework.platform.starter.security.filter.TokenAuthFilter;
import com.fallframework.platform.starter.security.filter.UsernamePasswordAuthFilter;
import com.fallframework.platform.starter.security.handle.AuthenticationEntryPointImpl;
import com.fallframework.platform.starter.security.handle.AuthenticationFailureHandlerImpl;
import com.fallframework.platform.starter.security.handle.AuthenticationSuccessHandlerImpl;
import com.fallframework.platform.starter.security.handle.DefaultPasswordEncoder;
import com.fallframework.platform.starter.security.handle.LogoutSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

/**
 * WebSecurityConfigurerAdapter : <br/>
 * 自定义类继承自WebSecurityConfigurerAdapter，进而实现对Spring Security更多的自定义配置，例如基于内存的认证。
 * <p>
 * HttpSecurity : <br/>
 * 虽然现在可以实现认证功能，但是受保护的资源都是默认的，而且也不能根据实际情况进行角色管理，如果要实现这些功能，
 * 就需要重写WebSecurityConfigurerAdapter 中的另一个方法configure，参数可以看到是HttpSecurity.
 *
 * @author zhuangpf
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 认证失败处理类
	 */
	@Autowired
	private AuthenticationEntryPointImpl unauthorizedHandler;
	@Autowired
	private AuthenticationFailureHandlerImpl authenticationFailureHandler;
	@Autowired
	private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;
	/**
	 * 退出处理类
	 */
	@Autowired
	private LogoutSuccessHandlerImpl logoutSuccessHandler;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private DefaultPasswordEncoder defaultPasswordEncoder;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private PermissionService permissionService;

	/**
	 * anyRequest          |   匹配所有请求路径
	 * access              |   SpringEl表达式结果为true时可以访问
	 * anonymous           |   匿名可以访问
	 * denyAll             |   用户不能访问
	 * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
	 * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
	 * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
	 * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
	 * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
	 * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
	 * permitAll           |   用户可以任意访问
	 * rememberMe          |   允许通过remember-me登录的用户访问
	 * authenticated       |   用户登录后可访问
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.formLogin()
				.loginPage("/login.html")
				.loginProcessingUrl("/dologin") // 自定义登录路径
				.successHandler(authenticationSuccessHandler) // 自定义登录成功处理
				.failureHandler(authenticationFailureHandler) // 自定义登录失败处理
				.and()
				// CSRF禁用，因为不使用sessions
				.csrf().disable()
				// 认证失败处理类
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
				.and()
				// 基于token，所以不需要session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				// 过滤请求
				.authorizeRequests()
				// 对于登录login 验证码captchaimage 允许匿名访问
				.antMatchers("/login", "/dologin", "/captchaimage").anonymous()
				.antMatchers(
						HttpMethod.GET,
						"/*.html",
						"/**/*.html",
						"/**/*.css",
						"/**/*.js"
				).permitAll()
				// 除上面外的所有请求全部需要鉴权认证
//				.anyRequest().authenticated()
				// 测试，基于资源的访问控制
				.anyRequest().access("@resourceAuthenticationControl.canAccess(request,authentication)")
				.and()
				.headers().frameOptions().disable()
				.and()
				// 认证过滤器
				.addFilter(new UsernamePasswordAuthFilter(authenticationManager(), redisUtil))
				// 授权过滤器
				.addFilter(new TokenAuthFilter(authenticationManager(), redisUtil, permissionService))
				// 访问被拒绝处理器
				.exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl());
		// 成功退出的处理
		http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
		// 自定义无访问权限跳转的页面 
//		http.exceptionHandling().accessDeniedPage("/unauth.html");
	}

	/**
	 * 配置认证服务
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
	}


}
