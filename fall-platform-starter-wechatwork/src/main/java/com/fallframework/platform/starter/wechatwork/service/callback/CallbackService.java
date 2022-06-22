package com.fallframework.platform.starter.wechatwork.service.callback;

import com.alibaba.fastjson.JSON;
import com.fallframework.platform.starter.wechatwork.aes.AesException;
import com.fallframework.platform.starter.wechatwork.aes.WXBizJsonMsgCrypt;
import com.fallframework.platform.starter.wechatwork.config.WechatWorkProperties;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 企微回调服务
 *
 * @author zhuangpf
 */
@Service
public class CallbackService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CallbackService.class);

	/**
	 * 企微配置
	 */
	@Autowired
	private WechatWorkProperties wechatWorkProperties;

	/**
	 * 回调验证接口
	 */
	public String callback(String msg_signature,
						   String timestamp,
						   String nonce,
						   String echostr) throws AesException {
		LOGGER.info("CallbackService#callback , msg_signature : {},timestamp : {}, nonce : {},echostr : {}"
				, msg_signature, timestamp, nonce, echostr);
		// 获取通讯录配置
		WechatWorkProperties.ContactConfig contactConfig = wechatWorkProperties.getContactConfig();
		String token = contactConfig.getToken();
		String corpId = wechatWorkProperties.getCorpId();
		String encodingAESKey = contactConfig.getEncodingAESKey();
		WXBizJsonMsgCrypt wxcpt = new WXBizJsonMsgCrypt(token, encodingAESKey, corpId);
		/*------------使用示例一：验证回调URL---------------
		*企业开启回调模式时，企业微信会向验证url发送一个get请求 
		假设点击验证时，企业收到类似请求：
		* GET /cgi-bin/wxpush?msg_signature=5c45ff5e21c57e6ad56bac8758b79b1d9ac89fd3&timestamp=1409659589&nonce=263014780&echostr=P9nAzCzyDtyTWESHep1vC5X9xho%2FqYX3Zpb4yKa9SKld1DsH3Iyt3tP3zNdtp%2B4RPcs8TgAE7OaBO%2BFZXvnaqQ%3D%3D 
		* HTTP/1.1 Host: qy.weixin.qq.com
		接收到该请求时，企业应		
		1.解析出Get请求的参数，包括消息体签名(msg_signature)，时间戳(timestamp)，随机数字串(nonce)以及企业微信推送过来的随机加密字符串(echostr),
		这一步注意作URL解码。
		2.验证消息体签名的正确性 
		3. 解密出echostr原文，将原文当作Get请求的response，返回给企业微信
		第2，3步可以用企业微信提供的库函数VerifyURL来实现。
		*/
		// 解析出url上的参数值如下：
		String sVerifyMsgSig = msg_signature;
		String sVerifyTimeStamp = timestamp;
		String sVerifyNonce = nonce;
		String sVerifyEchoStr = echostr;
		String sEchoStr = null; //需要返回的明文
		try {
			sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,
					sVerifyNonce, sVerifyEchoStr);
			LOGGER.info("verifyurl echostr : " + sEchoStr);
		} catch (Exception e) {
			//验证URL失败，错误原因请查看异常
			e.printStackTrace();
		}
		// 验证URL成功，将sEchoStr返回
		return sEchoStr;
	}

	public String callback02(String requestBody,
							 String signature,
							 String timestamp,
							 String nonce) throws AesException {
		LOGGER.info("requestBody : {},signature : {}, timestamp : {},nonce : {}"
				, requestBody, signature, timestamp, nonce);
		// 获取通讯录配置
		WechatWorkProperties.ContactConfig contactConfig = wechatWorkProperties.getContactConfig();
		WxCpDefaultConfigImpl configStorage = new WxCpDefaultConfigImpl();
		configStorage.setCorpId(wechatWorkProperties.getCorpId());
		configStorage.setCorpSecret(contactConfig.getSecret());
		configStorage.setToken(contactConfig.getToken());
		configStorage.setAesKey(contactConfig.getEncodingAESKey());
		WxCpServiceImpl service = new WxCpServiceImpl();
		service.setWxCpConfigStorage(configStorage);
		// 解密xml请求体，并转换成JSON
		WxCpXmlMessage inMessage
				= WxCpXmlMessage.fromEncryptedXml(requestBody, service.getWxCpConfigStorage(), timestamp, nonce, signature);
		LOGGER.info("\n消息解密后内容为：\n{} ", JSON.toJSONString(inMessage));

		// TODO : 业务处理

		return null;
	}


}
