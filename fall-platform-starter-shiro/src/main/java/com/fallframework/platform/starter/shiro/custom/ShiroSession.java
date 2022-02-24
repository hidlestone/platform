package com.fallframework.platform.starter.shiro.custom;

import com.alibaba.fastjson.JSON;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.core.constant.CoreContextConstant;
import com.fallframework.platform.starter.shiro.util.JWTUtil;
import com.fallframework.platform.starter.shiro.util.ShiroUtil;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 自定义session规则，实现前后分离，在跨域等情况下使用token方式进行登录验证才需要，否则没必须使用本类。
 * shiro默认使用 ServletContainerSessionManager 来做 session 管理，它是依赖于浏览器的 cookie 来维护 session 的,
 * 调用 storeSessionId 方法保存sesionId 到 cookie中。
 * 为了支持无状态会话，我们就需要继承 DefaultWebSessionManager
 * 自定义生成sessionId 则要实现 SessionIdGenerator
 * https://blog.csdn.net/weixin_33704591/article/details/92070894?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~aggregatepage~first_rank_ecpm_v1~rank_v31_ecpm-2-92070894.pc_agg_new_rank&utm_term=shiro+%E4%BD%BF%E7%94%A8%E8%87%AA%E5%AE%9A%E4%B9%89token%E8%AE%A4%E8%AF%81&spm=1000.2123.3001.4430
 *
 * @author zhuangpf
 */
public class ShiroSession extends DefaultWebSessionManager {

	private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

	private JWTUtil jwtUtil;

	public ShiroSession(JWTUtil jwtUtil) {
		super();
		this.jwtUtil = jwtUtil;
		// 设置 shiro session 失效时间，默认为30分钟，这里现在设置为15分钟
		// setGlobalSessionTimeout(MILLIS_PER_MINUTE * 15);
	}

	/**
	 * 获取sessionId，原本是根据sessionKey来获取一个sessionId
	 * 重写的部分多了一个把获取到的token设置到request的部分。这是因为app调用登陆接口的时候，是没有token的，登陆成功后，产生了token,我们把它放到request中，返回结
	 * 果给客户端的时候，把它从request中取出来，并且传递给客户端，客户端每次带着这个token过来，就相当于是浏览器的cookie的作用，也就能维护会话了
	 */
	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		// 获取请求头中 accesstoken & refreshtoken
		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		String accesstoken = httpRequest.getHeader(CoreContextConstant.ACCESSTOKEN);
		String refreshtoken = httpRequest.getHeader(CoreContextConstant.REFRESHTOKEN);
		ResponseResult responseResult = jwtUtil.accessRefreshTokeValidate(accesstoken, refreshtoken);
		if (!responseResult.isSuccess()) {
			ShiroUtil.resetResponse(response, responseResult);
			throw new RuntimeException(JSON.toJSONString(responseResult));
		}
		// 请求头中如果有 authToken, 则其值为sessionId
		request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
		// sessionId
		request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, accesstoken);
		request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
		return accesstoken;
	}
}
