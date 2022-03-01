

获取系统的参数配置：
1、使用property文件配置
config.properties
```
fall.shiro.loginUrl=test
fall.shiro.successUrl=test
fall.shiro.unauthorizedUrl=test
fall.shiro.filterChainDefinition=test
fall.shiro.rememberMeCookieMaxAge=test
```
ShiroConfigProperty
```java
/**
 * shiro 配置参数
 *
 * @author zhuangpf
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "fall.shiro")
@PropertySource("classpath:config.properties")
public class ShiroConfigProperty {

	private String loginUrl;
	private String successUrl;
	private String unauthorizedUrl;
	private String filterChainDefinition;
	private String rememberMeCookieMaxAge;

}
```





1、自定义session规则，实现前后分离，在跨域等情况下使用token方式进行登录验证
ShiroSession
2、rememberme
3、jwtFilter 自动刷新token
4、其他功能及问题


对于Subject我们一般这么使用：
1、身份验证（login）
2、授权（hasRole*/isPermitted*或checkRole*/checkPermission*）
3、将相应的数据存储到会话（Session）
4、切换身份（RunAs）/多线程身份传播
5、退出




登录认证授权过程
一、初始流程
1、账号密码请求登录接口
2、进行用户认证，如果有开启缓存，则在cookie中返回JESSIONID
3、再次请求接口的时候，在请求中获取JSSIONID，获取到缓存的session会话，会话中保存着登录用户的信息，则不需要再次进行认证


二、进行jtw 和 redis 的改造
1、登录认证后，将返回的JSESSIONID改成 accesstoken，并将session保存到redis中
2、再次请求接口的时候，在请求头中获取token，解析，获取到缓存的会话用户对象


授权方法执行了两次，待优化。














