package com.fallframework.platform.starter.core.constant;

/**
 * 上下文环境相关常量
 *
 * @author payn
 */
public class CoreContextConstant {

	// 自定义(可能使用)的请求头
	// Request Headers:module-code
	public static final String MODULE_CODE = "module-code";
	// Request Headers:Authorization 即token
	public static final String TOKEN = "token";
	// Request Headers:client-id
	public static final String CLIENT_ID = "client-id";
	// Request Headers:client-code 可能取值为：app/web/wx/...
	public static final String CLIENT_CODE = "client-code";
	// Request Headers:target
	public static final String TARGET = "target";
	// Request Headers:project
	public static final String PROJECT = "project";
	// Request Headers:request-id
	public static final String REQUEST_ID = "request-id";
	// Request Headers:request-order
	public static final String REQUEST_ORDER = "request-order";
	// Request Headers:app-id
	public static final String APP_ID = "app-id";
	// Request Headers:version
	public static final String VERSION = "version";
	// Request Headers:language
	public static final String LANGUAGE = "language";
	// Request Headers:callType
	public static final String CALL_TYPE = "call-type";
	public static final String CALL_TYPE_INNER = "inner";
	// Request Headers:stime 请求时间
	public static final String STIME = "stime";
	// Request Headers:nonce(Number once)
	public static final String NONCE = "nonce";
	// Request Headers:sign 签名
	public static final String SIGN = "sign";
	// Request Headers:encrypt-code AES密文
	public static final String ENCRYPT_CODE = "encrypt-code";
	// Request Headers:show-error-details 异常详细信息
	public static final String SHOW_ERROR_DETAILS = "show-error-details";
	// 内部请求签名
	public static final String INTERNAL_CALL_SIGN = "internal-call-sign";
	// 内部请求签名
	public static final String INTERNAL_CALL_TIME = "internal-call-time";

	// 需要重复提交校验的constnt-type
	public static final String NEED_CHECK_CONTENT_TYPES = "application/x-www-form-urlencoded,application/json";

	// cookie name:i18n_key
	public static final String I18N_KEY = "i18n_key";

	// 默认中文：zh_CN
	public static final String DEFAULT_LANGUAGE = "zh_CN";
	// 默认字符集
	public static final String DEFAULT_CHARSET = "UTF-8";

	public static final String REMEMBER_ME = "remember-me";

	// Request Headers:accesstoken 访问token
	public static final String ACCESSTOKEN = "accesstoken";

	// Request Headers:refreshtoken 访问token
	public static final String REFRESHTOKEN = "refreshtoken";


}
