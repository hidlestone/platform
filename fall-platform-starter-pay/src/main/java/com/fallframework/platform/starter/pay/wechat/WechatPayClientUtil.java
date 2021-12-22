package com.fallframework.platform.starter.pay.wechat;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.XmlUtil;
import com.fallframework.platform.starter.pay.wechat.util.WechatPayUtil;
import com.fallframework.platform.starter.pay.wechat.exception.WechatPayException;
import com.fallframework.platform.starter.pay.wechat.model.base.WechatPayPrePayModel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 微信支付，封装的客户端入口<br/>
 * 对request自动加上签名，转成xml，发送请求到微信支付，会返回结果做解析。
 *
 * @author zhuangpf
 */
@Getter
@Setter
public class WechatPayClientUtil {

	private Log LOGGER = LogFactory.getLog(this.getClass());

	private static final String SERVER_URL = "https://api.mch.weixin.qq.com";

	private String appId;         // 微信为公众账号Id
	private String mchId;         // 微信支付 商户号Id
	private String apiKey;        // 秘钥，用于签名
	private byte[] certFile;      // 退款时候，数字证书
	private boolean requireCert;  // 是否需要带上支付凭证 TODO

	public WechatPayClientUtil(String apiKey) {
		this.apiKey = apiKey;
	}

	public WechatPayClientUtil(String appId, String mchId, String apiKey) {
		this.appId = appId;
		this.mchId = mchId;
		this.apiKey = apiKey;
	}

	public WechatPayClientUtil(String appId, String mchId, String apiKey, byte[] certFile) {
		this.appId = appId;
		this.mchId = mchId;
		this.apiKey = apiKey;
		this.certFile = certFile;
	}

	/**
	 * 支付
	 */
	public String pay(WechatPayPrePayModel model) throws WechatPayException {
		model.setAppId(appId);
		model.setMchId(mchId);
		model.setNonceStr(RandomStringUtils.random(32, true, true));

		// 将对象转换成 map
		Map<String, String> map = new HashMap<>();
		BeanUtil.copyProperties(model, map);
		String url = ""; // TODO
		LOGGER.info(String.format("wechat.pay.url=%s,request=%s", url, map));

		// 所有参与传参的参数按照accsii排序（升序）
		SortedMap<String, String> paramMap = new TreeMap<String, String>(map);
		String sign = WechatPayUtil.createSign(apiKey, paramMap);
		model.setSign(sign);
		paramMap.put("sign", sign);
		// 判断是否需要带上支付证书
		CloseableHttpClient client = this.getClient();
		String text = "";
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
			StringEntity payload = new StringEntity(XmlUtil.mapToXml(paramMap, null).toString(), "UTF-8");
			httpPost.setEntity(payload);
			text = client.execute(httpPost, new ResponseHandler<String>() {
				@Override
				public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
					StringBuilder builder = new StringBuilder();
					HttpEntity entity = response.getEntity();
					String text;
					if (entity != null) {
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
						while ((text = bufferedReader.readLine()) != null) {
							builder.append(text);
						}

					}
					return builder.toString();
				}
			});
		} catch (Exception e) {
			throw new WechatPayException(e);
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				throw new WechatPayException(e);
			}
		}
		return text;
	}

	/**
	 * 判断是否需要带上 支付证书
	 */
	private CloseableHttpClient getClient() throws WechatPayException {
		CloseableHttpClient client;
		if (requireCert) {
			try {
				KeyStore keyStore = KeyStore.getInstance("PKCS12");
				ByteArrayInputStream inputStream = new ByteArrayInputStream(certFile);
				try {
					keyStore.load(inputStream, this.mchId.toCharArray());
				} finally {
					inputStream.close();
				}
				SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, this.mchId.toCharArray()).build();
				SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
				client = HttpClients.custom().setSSLSocketFactory(factory).build();
			} catch (Exception e) {
				throw new WechatPayException(e);
			}
		} else {
			client = HttpClients.createDefault();
		}
		return client;
	}
}
