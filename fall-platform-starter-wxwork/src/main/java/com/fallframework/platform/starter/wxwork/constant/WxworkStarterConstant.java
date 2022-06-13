package com.fallframework.platform.starter.wxwork.constant;

/**
 * @author zhuangpf
 */
public class WxworkStarterConstant {

	/**
	 * 缓存key：accesstoken
	 */
	public static final String CACHE_KEY_ACCESS_TOKEN = "wxwork:accesstoken";

	// ----------------------------------------- token ----------------------------------------- //
	/**
	 * 获取token
	 */
	public static final String URL_GETTOKEN = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

	// ----------------------------------------- 通讯录 ----------------------------------------- //
	/**
	 * 创建成员
	 */
	public static final String URL_USER_CREATE = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=ACCESS_TOKEN";

	/**
	 * 读取成员
	 */
	public static final String URL_USER_GET = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";


}
