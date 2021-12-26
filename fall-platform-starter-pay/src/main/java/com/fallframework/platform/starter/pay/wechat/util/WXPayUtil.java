package com.fallframework.platform.starter.pay.wechat.util;

import com.fallframework.platform.starter.core.util.EncryptionUtil;
import com.fallframework.platform.starter.pay.wechat.constant.WXPayConstants;
import com.fallframework.platform.starter.pay.wechat.constant.WXPayConstants.SignType;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

/**
 * 微信支付数据转换工具类
 *
 * @author zhuangpf
 */
@Component
public class WXPayUtil {

	private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static final Random RANDOM = new SecureRandom();

	/**
	 * XML格式字符串转换为Map
	 *
	 * @param strXML XML字符串
	 * @return XML数据转换后的Map
	 * @throws Exception
	 */
	public static Map<String, String> xmlToMap(String strXML) throws Exception {
		try {
			Map<String, String> data = new HashMap<String, String>();
			DocumentBuilder documentBuilder = WXPayXmlUtil.newDocumentBuilder();
			InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
			org.w3c.dom.Document doc = documentBuilder.parse(stream);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getDocumentElement().getChildNodes();
			for (int idx = 0; idx < nodeList.getLength(); ++idx) {
				Node node = nodeList.item(idx);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					org.w3c.dom.Element element = (org.w3c.dom.Element) node;
					data.put(element.getNodeName(), element.getTextContent());
				}
			}
			try {
				stream.close();
			} catch (Exception ex) {
				// do nothing
			}
			return data;
		} catch (Exception ex) {
			WXPayUtil.getLogger().warn("Invalid XML, can not convert to map. Error message: {}. XML content: {}", ex.getMessage(), strXML);
			throw ex;
		}

	}

	/**
	 * 将Map转换为XML格式的字符串
	 *
	 * @param data Map类型数据
	 * @return XML格式的字符串
	 * @throws Exception
	 */
	public static String mapToXml(Map<String, String> data) throws Exception {
		org.w3c.dom.Document document = WXPayXmlUtil.newDocument();
		org.w3c.dom.Element root = document.createElement("xml");
		document.appendChild(root);
		for (String key : data.keySet()) {
			String value = data.get(key);
			if (value == null) {
				value = "";
			}
			value = value.trim();
			org.w3c.dom.Element filed = document.createElement(key);
			filed.appendChild(document.createTextNode(value));
			root.appendChild(filed);
		}
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		DOMSource source = new DOMSource(document);
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		transformer.transform(source, result);
		String output = writer.getBuffer().toString(); //.replaceAll("\n|\r", "");
		try {
			writer.close();
		} catch (Exception ex) {
		}
		return output;
	}


	/**
	 * 生成带有 sign 的 XML 格式字符串
	 *
	 * @param data Map类型数据
	 * @param key  API密钥
	 * @return 含有sign字段的XML
	 */
	public static String generateSignedXml(final Map<String, String> data, String key) throws Exception {
		return generateSignedXml(data, key, SignType.MD5);
	}

	/**
	 * 生成带有 sign 的 XML 格式字符串
	 *
	 * @param data     Map类型数据
	 * @param key      API密钥
	 * @param signType 签名类型
	 * @return 含有sign字段的XML
	 */
	public static String generateSignedXml(final Map<String, String> data, String key, SignType signType) throws Exception {
		String sign = generateSignature(data, key, signType);
		data.put(WXPayConstants.FIELD_SIGN, sign);
		return mapToXml(data);
	}


	/**
	 * 判断签名是否正确
	 *
	 * @param xmlStr XML格式数据
	 * @param key    API密钥
	 * @return 签名是否正确
	 * @throws Exception
	 */
	public static boolean isSignatureValid(String xmlStr, String key) throws Exception {
		Map<String, String> data = xmlToMap(xmlStr);
		if (!data.containsKey(WXPayConstants.FIELD_SIGN)) {
			return false;
		}
		String sign = data.get(WXPayConstants.FIELD_SIGN);
		return generateSignature(data, key).equals(sign);
	}

	/**
	 * 判断签名是否正确，必须包含sign字段，否则返回false。使用MD5签名。
	 *
	 * @param data Map类型数据
	 * @param key  API密钥
	 * @return 签名是否正确
	 * @throws Exception
	 */
	public static boolean isSignatureValid(Map<String, String> data, String key) throws Exception {
		return isSignatureValid(data, key, SignType.MD5);
	}

	/**
	 * 判断签名是否正确，必须包含sign字段，否则返回false。
	 *
	 * @param data     Map类型数据
	 * @param key      API密钥
	 * @param signType 签名方式
	 * @return 签名是否正确
	 * @throws Exception
	 */
	public static boolean isSignatureValid(Map<String, String> data, String key, SignType signType) throws Exception {
		if (!data.containsKey(WXPayConstants.FIELD_SIGN)) {
			return false;
		}
		String sign = data.get(WXPayConstants.FIELD_SIGN);
		return generateSignature(data, key, signType).equals(sign);
	}

	/**
	 * 生成签名
	 *
	 * @param data 待签名数据
	 * @param key  API密钥
	 * @return 签名
	 */
	public static String generateSignature(final Map<String, String> data, String key) throws Exception {
		return generateSignature(data, key, SignType.MD5);
	}

	/**
	 * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
	 *
	 * @param data     待签名数据
	 * @param key      API密钥
	 * @param signType 签名方式
	 * @return 签名
	 */
	public static String generateSignature(final Map<String, String> data, String key, SignType signType) throws Exception {
		Set<String> keySet = data.keySet();
		String[] keyArray = keySet.toArray(new String[keySet.size()]);
		Arrays.sort(keyArray);
		StringBuilder sb = new StringBuilder();
		for (String k : keyArray) {
			if (k.equals(WXPayConstants.FIELD_SIGN)) {
				continue;
			}
			if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
				sb.append(k).append("=").append(data.get(k).trim()).append("&");
		}
		sb.append("key=").append(key);
		if (SignType.MD5.equals(signType)) {
			return MD5(sb.toString()).toUpperCase();
		} else if (SignType.HMACSHA256.equals(signType)) {
			return HMACSHA256(sb.toString(), key);
		} else {
			throw new Exception(String.format("Invalid sign_type: %s", signType));
		}
	}


	/**
	 * 获取随机字符串 Nonce Str
	 *
	 * @return String 随机字符串
	 */
	public static String generateNonceStr() {
		char[] nonceChars = new char[32];
		for (int index = 0; index < nonceChars.length; ++index) {
			nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
		}
		return new String(nonceChars);
	}


	/**
	 * 生成 MD5
	 *
	 * @param data 待处理数据
	 * @return MD5结果
	 */
	public static String MD5(String data) throws Exception {
		java.security.MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] array = md.digest(data.getBytes("UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (byte item : array) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 生成 HMACSHA256
	 *
	 * @param data 待处理数据
	 * @param key  密钥
	 * @return 加密结果
	 * @throws Exception
	 */
	public static String HMACSHA256(String data, String key) throws Exception {
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
		sha256_HMAC.init(secret_key);
		byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (byte item : array) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 日志
	 *
	 * @return
	 */
	public static Logger getLogger() {
		Logger logger = LoggerFactory.getLogger("wxpay java sdk");
		return logger;
	}

	/**
	 * 获取当前时间戳，单位秒
	 *
	 * @return
	 */
	public static long getCurrentTimestamp() {
		return System.currentTimeMillis() / 1000;
	}

	/**
	 * 获取当前时间戳，单位毫秒
	 *
	 * @return
	 */
	public static long getCurrentTimestampMs() {
		return System.currentTimeMillis();
	}


	/**
	 * 微信支付签名算法 sign
	 */
	public static String createSign(String signKey, SortedMap<String, String> paramMap) {
		StringBuilder sb = new StringBuilder();       // 多线程访问的情况下需要用StringBuffer
		Set es = paramMap.keySet();                   // 所有参与传参的key按照accsii排序（升序）
		for (Object set : es) {
			String k = set.toString();
			Object v = paramMap.get(k);
			sb.append(k)
					.append("=")
					.append(v.toString())
					.append("&");
		}
		sb.append("key=").append(signKey);
		return EncryptionUtil.encryptMD5(sb.toString(), "utf-8");
	}

	/**
	 * 校验 sign
	 */
	public static boolean checkMd5Sign(String content, String sign, String key) {
		if (StringUtils.isBlank(content)) {
			return false;
		}
		StringBuilder buf = new StringBuilder(content);
		buf.append("&key=" + key);
		return EncryptionUtil.encryptMD5(buf.toString(), "utf-8").equalsIgnoreCase(sign);
	}

	/**
	 * 初始化公共的请求参数
	 */
	public void initCommonParams(SortedMap<Object, Object> paramMap) {


	}

	/**
	 * 将请求参数转换成XML格式
	 */
	public static String constructRequestXml(SortedMap<Object, Object> paramMap) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = paramMap.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 解析XML为Map
	 */
	public static Map<String, String> doXMLParse(String strXml) throws JDOMException, IOException {
		strXml = filterXXE(strXml);
		strXml = strXml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
		if (StringUtils.isBlank(strXml)) {
			return null;
		}
		Map m = new HashMap();
		InputStream in = new ByteArrayInputStream(strXml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v;
			List children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = WXPayUtil.getChildrenText(children);
			}

			m.put(k, v);
		}
		in.close();
		return m;
	}

	/**
	 * 获取子结点的xml
	 *
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(WXPayUtil.getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}

		return sb.toString();
	}

	/**
	 * 通过DOCTYPE和ENTITY来加载本地受保护的文件、替换掉即可
	 * 漏洞原理：https://my.oschina.net/u/574353/blog/1841103
	 *      * 防止 XXE漏洞 注入实体攻击
	 *      * 过滤 过滤用户提交的XML数据
	 *      * 过滤关键词：<!DOCTYPE和<!ENTITY，或者SYSTEM和PUBLIC。
	 *    
	 */
	public static String filterXXE(String xmlStr) {
		xmlStr = xmlStr.replace("DOCTYPE", "").replace("SYSTEM", "").replace("ENTITY", "").replace("PUBLIC", "");
		return xmlStr;
	}

	public String requestWithoutCert(String urlSuffix, SortedMap<Object, Object> paramMap) throws IOException {
		// 将请求参数转换成XML格式
		String requestXML = WXPayUtil.constructRequestXml(paramMap);
		// 构建 HttpClient
		BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager(
				RegistryBuilder.<ConnectionSocketFactory>create()
						.register("http", PlainConnectionSocketFactory.getSocketFactory())
						.register("https", SSLConnectionSocketFactory.getSocketFactory())
						.build(),
				null,
				null,
				null
		);
		HttpClient httpClient = HttpClientBuilder.create()
				.setConnectionManager(connManager)
				.build();
		String url = "https://" + WXPayConstants.DOMAIN_API + urlSuffix;
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(WXPayConstants.HTTP_READ_TIMEOUT).setConnectTimeout(WXPayConstants.HTTP_CONNECT_TIMEOUT).build();
		httpPost.setConfig(requestConfig);
		StringEntity postEntity = new StringEntity(requestXML, "UTF-8");
		httpPost.addHeader("Content-Type", "text/xml");
		httpPost.addHeader("User-Agent", WXPayConstants.USER_AGENT + " " + paramMap.get("mch_id"));
		httpPost.setEntity(postEntity);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();
		String respXML = EntityUtils.toString(httpEntity, "UTF-8");
		return respXML;
	}

	public String requestWithCert(String urlSuffix, SortedMap<Object, Object> paramMap, KeyManagerFactory kmf) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		// 将请求参数转换成XML格式
		String requestXML = WXPayUtil.constructRequestXml(paramMap);
		BasicHttpClientConnectionManager connManager;
		// 创建 SSLContext
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());
		SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
				sslContext,
				new String[]{"TLSv1"},
				null,
				new DefaultHostnameVerifier());

		connManager = new BasicHttpClientConnectionManager(
				RegistryBuilder.<ConnectionSocketFactory>create()
						.register("http", PlainConnectionSocketFactory.getSocketFactory())
						.register("https", sslConnectionSocketFactory)
						.build(),
				null,
				null,
				null
		);
		HttpClient httpClient = HttpClientBuilder.create()
				.setConnectionManager(connManager)
				.build();

		String url = "https://" + WXPayConstants.DOMAIN_API + urlSuffix;
		HttpPost httpPost = new HttpPost(url);

		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(WXPayConstants.HTTP_READ_TIMEOUT).setConnectTimeout(WXPayConstants.HTTP_CONNECT_TIMEOUT).build();
		httpPost.setConfig(requestConfig);

		StringEntity postEntity = new StringEntity(requestXML, "UTF-8");
		httpPost.addHeader("Content-Type", "text/xml");
		httpPost.addHeader("User-Agent", WXPayConstants.USER_AGENT + " " + paramMap.get("mch_id"));
		httpPost.setEntity(postEntity);

		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();
		return EntityUtils.toString(httpEntity, "UTF-8");
	}
}
