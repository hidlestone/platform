package com.fallframework.platform.starter.security.filter;

import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * UsernamePasswordAuthenticationFilter是AbstractAuthenticationProcessingFilter针对使用用户名和密码进行身份验证而定制化的一个过滤器。
 * 其添加是在调用http.formLogin()时作用，默认的登录请求pattern为 "/login" ，并且为POST请求。
 * 当我们登录的时候，也就是匹配到loginProcessingUrl，这个过滤器就会委托认证管理器authenticationManager来验证登录。
 *
 * @author zhuangpf
 */
public class UsernamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

	private RedisUtil redisUtil;
	private AuthenticationManager authenticationManager;

	public UsernamePasswordAuthFilter(AuthenticationManager authenticationManager, RedisUtil redisUtil) {
		this.authenticationManager = authenticationManager;
		this.redisUtil = redisUtil;
		this.setPostOnly(true);
		// 设置登陆路径，并且post请求
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		String username = this.obtainUsername(request);
		String password = this.obtainPassword(request);
		if (username == null) {
			username = "";
		}
		if (password == null) {
			password = "";
		}
		username = username.trim();
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						username, password, new ArrayList<>()));
	}

	/**
	 * 认证成功调用的方法，将生成的token存入到redis<br/>
	 * 改成在 AuthenticationSuccessHandlerImpl 中处理
	 */
	/*@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		// 认证成功之后，得到认证成功后的用户信息
		User user = (User) authResult.getPrincipal();
		// 生成token
		String token = JWTUtil.createToken(user);
		// 存入缓存
		redisUtil.hset(SecurityStarterConstant.CACHE_KEY_USER_TOKEN, String.valueOf(user.getId()), token);
	}*/

}
