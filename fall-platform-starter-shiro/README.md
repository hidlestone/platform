

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


