package com.fallframework.platform.starter.guard.filter;

import com.alibaba.fastjson.JSON;
import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.config.model.SysParamItemResponse;
import com.fallframework.platform.starter.config.service.PlatformSysParamUtil;
import com.fallframework.platform.starter.core.constant.CoreContextConstant;
import com.fallframework.platform.starter.core.context.ApplicationContextFallPlatform;
import com.fallframework.platform.starter.core.util.EncryptionUtil;
import com.fallframework.platform.starter.guard.constant.GuardStarterConstant;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 防止表单重复提交的过滤器，全局功能设置
 *
 * @author zhuangpf
 */
public class RepeatSubmitFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(RepeatSubmitFilter.class);

	@Autowired
	private static PlatformSysParamUtil platformSysParamUtil;
	@Autowired
	private RedisUtil redisUtil;

	// 是否开启表单重复提交检查
	private static final Boolean APPLICATION_SIGNATURE_REPEAT_CHECK_ENABLE;
	// 是否开启签名
	private static final Boolean APPLICATION_SIGNATURE_ENABLE;
	// 不需要签名的服务名称
	private static final String APPLICATION_SIGNATURE_EXCLUDE_SERVICE_NAMES;
	// 签名的 content-type
	private static final String APPLICATION_SIGNATURE_CONTENT_TYPES;
	// 不需要签名的url	
	private static final String APPLICATION_SIGNATURE_EXCLUDE_URLS;
	// 带签名的请求方法
	private static final String APPLICATION_SIGNATURE_METHOD_NAME;
	// 带签名的请求超时时间
	private static final Integer APPLICATION_SIGNATURE_STIME_TIMEOUT;
	// 带签名的请求默认超时时间
	private static final Integer APPLICATION_DEFAULT_STIME_TIMEOUT;
	// nonce超时时间
	private static final Integer APPLICATION_SIGNATURE_NONCE_TIMEOUT;
	// 签名无需加密的参数
	private static final String APPLICATION_SIGNATURE_EXCLUDE_PARAM_NAMES;
	// 默认需要检查的提交/保存方法
	private static final String DEFAULT_NEED_CHECK_METHOD_NAMES;
	// 默认签名无需加密的参数
	private static final String DEFAULT_EXCLUDE_PARAM_NAMES;
	// RSA 私钥
	private static final String RSA_PRIVATE_KEY;

	// 不需要签名的服务名称
	private static final List<String> excludeServiceNameList;
	// 签名的 content-type
	private static final List<String> signatureContenTypeList;
	// 不需要签名的url	
	private static final List<String> signatureExcludeUrlList;
	// 默认需要检查的提交/保存方法
	private static final List<String> needCheckMethodNamelist;
	// 签名无需加密的参数+默认签名无需加密的参数
	private static final List<String> excludeParamNamelist;


	static {
		Map<String, String> sysItemMap = platformSysParamUtil.getSysParamGroupItemMap("APPLICATION_SIGNATURE").getData();
		LOGGER.info("SYS PARAM APPLICATION_SIGNATURE : " + sysItemMap.toString());
		APPLICATION_SIGNATURE_REPEAT_CHECK_ENABLE = Boolean.valueOf(sysItemMap.get("APPLICATION_SIGNATURE_REPEAT_CHECK_ENABLE"));
		APPLICATION_SIGNATURE_ENABLE = Boolean.valueOf(sysItemMap.get("APPLICATION_SIGNATURE_ENABLE"));
		APPLICATION_SIGNATURE_EXCLUDE_SERVICE_NAMES = sysItemMap.get("APPLICATION_SIGNATURE_EXCLUDE_SERVICE_NAMES");
		APPLICATION_SIGNATURE_CONTENT_TYPES = sysItemMap.get("APPLICATION_SIGNATURE_CONTENT_TYPES");
		APPLICATION_SIGNATURE_EXCLUDE_URLS = sysItemMap.get("APPLICATION_SIGNATURE_EXCLUDE_URLS");
		APPLICATION_SIGNATURE_METHOD_NAME = sysItemMap.get("APPLICATION_SIGNATURE_METHOD_NAME");
		APPLICATION_SIGNATURE_STIME_TIMEOUT = Integer.valueOf(sysItemMap.get("APPLICATION_SIGNATURE_EXCLUDE_SERVICE_NAMES"));
		APPLICATION_DEFAULT_STIME_TIMEOUT = Integer.valueOf(sysItemMap.get("APPLICATION_DEFAULT_STIME_TIMEOUT"));
		APPLICATION_SIGNATURE_NONCE_TIMEOUT = Integer.valueOf(sysItemMap.get("APPLICATION_SIGNATURE_NONCE_TIMEOUT"));
		APPLICATION_SIGNATURE_EXCLUDE_PARAM_NAMES = sysItemMap.get("APPLICATION_SIGNATURE_EXCLUDE_PARAM_NAMES");
		DEFAULT_NEED_CHECK_METHOD_NAMES = sysItemMap.get("DEFAULT_NEED_CHECK_METHOD_NAMES");
		DEFAULT_EXCLUDE_PARAM_NAMES = sysItemMap.get("DEFAULT_EXCLUDE_PARAM_NAMES");

		SysParamItemResponse sysParamItem = platformSysParamUtil.getSysParamItem("RSA", "RSA_PRIVATE_KEY").getData();
		RSA_PRIVATE_KEY = sysParamItem.getValue();

		// 不需要签名的服务名称
		String[] excludeServiceNameArr = APPLICATION_SIGNATURE_EXCLUDE_SERVICE_NAMES.split(",");
		excludeServiceNameList = new ArrayList<>(excludeServiceNameArr.length);
		Collections.addAll(excludeServiceNameList, excludeServiceNameArr);
		// 签名的 content-type
		String[] contentTypeArr = APPLICATION_SIGNATURE_CONTENT_TYPES.split(",");
		signatureContenTypeList = new ArrayList<>(contentTypeArr.length);
		Collections.addAll(signatureContenTypeList, contentTypeArr);
		// 不需要签名的url	
		String[] signatureExcludeUrlArr = APPLICATION_SIGNATURE_EXCLUDE_URLS.split(",");
		signatureExcludeUrlList = new ArrayList<>(signatureExcludeUrlArr.length);
		Collections.addAll(signatureExcludeUrlList, signatureExcludeUrlArr);
		// 需要检查的提交/保存方法
		String[] needCheckMethodNameArr = DEFAULT_NEED_CHECK_METHOD_NAMES.split(",");
		needCheckMethodNamelist = new ArrayList<>(needCheckMethodNameArr.length);
		Collections.addAll(needCheckMethodNamelist, needCheckMethodNameArr);
		// 签名无需加密的参数+默认签名无需加密的参数
		String[] signatureExcludeParamNameArr = APPLICATION_SIGNATURE_EXCLUDE_PARAM_NAMES.split(",");
		String[] defaultExcludeParamNameArr = DEFAULT_EXCLUDE_PARAM_NAMES.split(",");
		excludeParamNamelist = new ArrayList<>(signatureExcludeParamNameArr.length + defaultExcludeParamNameArr.length);
		Collections.addAll(excludeParamNamelist, signatureExcludeParamNameArr);
		Collections.addAll(excludeParamNamelist, defaultExcludeParamNameArr);
	}

	@Override
	public void init(FilterConfig filterConfig) {
		LOGGER.debug("RepeatSubmitFilter init.");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		// 内部请求放行
		if (CoreContextConstant.CALL_TYPE_INNER.equals(request.getHeader(CoreContextConstant.CALL_TYPE))) {
			chain.doFilter(req, response);
		}
		if (!APPLICATION_SIGNATURE_REPEAT_CHECK_ENABLE && !APPLICATION_SIGNATURE_ENABLE) {
			chain.doFilter(req, response);
		}
		// 当前应用名称
		String applicationName = ApplicationContextFallPlatform.getApplicationName();
		if (excludeServiceNameList.contains(applicationName)) {
			chain.doFilter(req, response);
		}
		long stime = 0;
		if (this.checkUriSignature(request.getRequestURI())) {
			if (StringUtils.isBlank(request.getHeader(CoreContextConstant.STIME))) {
				throw new RuntimeException("request header stime can not be empty.");
			}
			// 请求时间
			stime = Long.parseLong(request.getHeader(CoreContextConstant.STIME));
			// 获取请求超时时间
			Integer timeout = null != APPLICATION_SIGNATURE_STIME_TIMEOUT ? APPLICATION_SIGNATURE_STIME_TIMEOUT : APPLICATION_DEFAULT_STIME_TIMEOUT;
			long currentTime = System.currentTimeMillis();
			if ((currentTime - stime) > (long) timeout) {
				LOGGER.error("currentTime:{},stime:{}", currentTime, stime);
				throw new RuntimeException("stime timeout.");
			}
		}
		// nonce(Number once)校验
		String nonce = request.getHeader(CoreContextConstant.NONCE);
		if (APPLICATION_SIGNATURE_REPEAT_CHECK_ENABLE) {
			// 校验是否重复提交
			this.checkRepeatedRequest(request, nonce);
		}
		// 开启签名
		if (APPLICATION_SIGNATURE_ENABLE) {
			// 校验请求参数是否被篡改
			this.checkParamBeModified(request, nonce);
		}
	}

	/**
	 * 校验请求参数是否被篡改
	 */
	private void checkParamBeModified(HttpServletRequest request, String nonce) {
		// 获取请求头中的签名 sign
		String sign = request.getHeader(CoreContextConstant.SIGN);
		if (StringUtils.isBlank(sign)) {
			throw new RuntimeException("sign can not be empty.");
		}
		// 获取AES密文
		String encryptCode = request.getHeader(CoreContextConstant.ENCRYPT_CODE);
		LOGGER.error("encrypt-code : {}", encryptCode);
		if (StringUtils.isBlank(encryptCode)) {
			throw new RuntimeException("encrypt-code can not be empty.");
		}
		String aesKey = "";
		try {
			// 获取AES加密密钥
			aesKey = EncryptionUtil.decryptRSA(encryptCode, RSA_PRIVATE_KEY);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("checkParamBeModified decrypt fail.");
		}

		// 请求的原始参数 parameter
		Map<String, Object> originalParam = new HashMap();
		// 请求体 request-body
		String bodyParam = null;
		// 请求参数 keyset
		Set<String> parameterNameSet = request.getParameterMap().keySet();
		for (String param : parameterNameSet) {
			originalParam.put(param, StringUtils.join(request.getParameterValues(param), ","));
		}
		// 请求体
		try {
			if (request.getContentType().contains(MediaType.APPLICATION_JSON.toString())) {
				request = new BodyReaderHttpServletRequestWrapper(request);
				bodyParam = IOUtils.toString(request.getInputStream());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("request.getInputStream fail.");
		}
		// 合并 parameter 和 request-body
		Map<String, Object> originalReqMap = this.getAllParamMap(originalParam, bodyParam);
		// 排序后的请求参数
		Map<String, Object> allParamMap = this.convertAndSortMap(originalReqMap, excludeParamNamelist);
		String allParamJsonStr = JSON.toJSONString(allParamMap);
		// 进行MD5加密
		String allParamJsonStrMd5 = EncryptionUtil.encryptMD5(allParamJsonStr);
		String backSign = "";
		try {
			backSign = EncryptionUtil.encryptAES(allParamJsonStr.toLowerCase(), aesKey);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("EncryptionUtil.encryptAES fail.");
		}
		if (!sign.equalsIgnoreCase(backSign)) {
			LOGGER.error("nonce : {}", nonce);
			LOGGER.error("encryptCode : {}", encryptCode);
			LOGGER.error("aeskey : {}", aesKey);
			LOGGER.error("输入流转字符串:{}", bodyParam);
			LOGGER.error("原始参数 originalParam : {}", JSON.toJSON(originalParam));
			LOGGER.error("后端实际参数加密串 allParamJsonStr : {}", allParamJsonStr);
			LOGGER.error("后端参数Md5加密结果 allParamJsonStrMd5 : {}", allParamJsonStrMd5);
			LOGGER.error("前端 sign ：{}", sign);
			LOGGER.error("后端 backSign ：{}", backSign);
			// 提示参数应被修改
			throw new RuntimeException("request param has alread been modified.");
		}
	}

	/**
	 * 转换&排序
	 */
	private Map<String, Object> convertAndSortMap(Map<String, Object> originalReqMap, List<String> excludeParamNamelist) {
		Map<String, Object> tmpMap = new HashMap();
		for (Map.Entry<String, Object> entry : originalReqMap.entrySet()) {
			if (!excludeParamNamelist.contains(entry.getKey())) {
				tmpMap.put(entry.getKey(), entry.getValue());
			}
			if (entry.getValue() != null && NumberUtils.isDigits(entry.getValue().toString())) {
				tmpMap.put(entry.getKey(), entry.getValue().toString());
			} else {
				tmpMap.put(entry.getKey(), entry.getValue());
			}
		}
		Map<String, Object> resultMap = new LinkedHashMap<>();
		tmpMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(x -> resultMap.put(x.getKey(), (x.getValue())));
		return resultMap;
	}

	/**
	 * 合并 parameter 和 request-body
	 */
	private Map<String, Object> getAllParamMap(Map<String, Object> originalParam, String requestBody) {
		if (StringUtils.isNotBlank(requestBody)) {
			Map<String, Object> bodyJson = JSON.parseObject(requestBody);
			originalParam.putAll(bodyJson);
		}
		return originalParam;
	}


	/**
	 * 检查是否重复提交
	 */
	private void checkRepeatedRequest(HttpServletRequest request, String nonce) {
		// 校验nonce(Number once)
		if (StringUtils.isBlank(nonce)) {
			throw new RuntimeException("nonce can not be empty");
		}
		String nonceKey = GuardStarterConstant.SIGN_NONCE + nonce;
		if (null != this.redisUtil.get(nonceKey)) {
			LOGGER.error("nonce:{},url:{} can not repeate commit.", nonce, request.getRequestURI());
			throw new RuntimeException("nonce:" + nonce + ",url:" + request.getRequestURI() + " can not repeate commit.");
		} else {
			// nonceKey存入缓存，ttl默认：60s
			Integer signNonceTimeout = null != APPLICATION_SIGNATURE_NONCE_TIMEOUT ? APPLICATION_SIGNATURE_NONCE_TIMEOUT : GuardStarterConstant.INIT_NONCE_TIMEOUT;
			this.redisUtil.set(nonceKey, 1, signNonceTimeout / 1000);
		}
	}

	private boolean checkUriSignature(String requestURI) {
		if (signatureExcludeUrlList.contains(requestURI)) {
			return false;
		}
		// 请求url的方法：如 localhost/wordplay/xxx/save -> save
		// localhost/wordplay/xxx/save?name=zhansan  -> save
		if (requestURI.indexOf("?") > -1) {
			String[] requestUrls = requestURI.split("\\?");
			requestURI = requestUrls[0];
		}
		String requestMethodName = requestURI.substring(requestURI.lastIndexOf("/") + 1, requestURI.length());
		if (needCheckMethodNamelist.contains(requestMethodName)) {
			return true;
		}
		return false;
	}

	@Override
	public void destroy() {
		LOGGER.debug("RepeatSubmitFilter destroy.");
	}
}
