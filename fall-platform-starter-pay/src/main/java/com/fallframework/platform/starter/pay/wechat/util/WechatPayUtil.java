package com.fallframework.platform.starter.pay.wechat.util;

import com.fallframework.platform.starter.core.util.EncryptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * 微信支付数据转换工具类
 *
 * @author zhuangpf
 */
@Component
public class WechatPayUtil {

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
				v = WechatPayUtil.getChildrenText(children);
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
					sb.append(WechatPayUtil.getChildrenText(list));
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

}
