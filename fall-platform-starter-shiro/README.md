

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











