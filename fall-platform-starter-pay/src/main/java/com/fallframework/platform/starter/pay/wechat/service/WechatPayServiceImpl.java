package com.fallframework.platform.starter.pay.wechat.service;

import cn.hutool.core.util.XmlUtil;
import com.fallframework.platform.starter.core.entity.response.ResponseResult;
import com.fallframework.platform.starter.pay.common.api.PayService;
import com.fallframework.platform.starter.pay.entity.PayOrder;
import com.fallframework.platform.starter.pay.entity.PayWechatConfig;
import com.fallframework.platform.starter.pay.enums.TradeTypeEnum;
import com.fallframework.platform.starter.pay.model.PayNotifyResponse;
import com.fallframework.platform.starter.pay.model.SignatureItem;
import com.fallframework.platform.starter.pay.util.WebPropertiesUtil;
import com.fallframework.platform.starter.pay.wechat.util.WechatPayUtil;
import com.fallframework.platform.starter.pay.wechat.WechatPayClientUtil;
import com.fallframework.platform.starter.pay.wechat.exception.WechatPayException;
import com.fallframework.platform.starter.pay.wechat.model.base.WechatPayPrePayModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author zhuangpf
 */
public class WechatPayServiceImpl implements PayService {

	private Log LOGGER = LogFactory.getLog(WechatPayServiceImpl.class);

	// 通知url必须为直接可访问的url，不能携带参数
	private String notifyUrl = WebPropertiesUtil.getInstance().getValue("wechat.pay.notify.url");

	@Override
	public ResponseResult pay(PayWechatConfig config, PayOrder order) throws WechatPayException {
		// 订单状态。创建支付订单
//		PayProcessStatus.CREATE_PAYMENT;

//		WechatPayPrePayOutVo
		// model中 appId,sign,mchId,nonceStr 在WechatClient中设置
		WechatPayPrePayModel model = new WechatPayPrePayModel();
		model.setOutTradeNo(order.getPayOrderNo());
		model.setSpbillCreateIp(order.getUserIp());
		model.setBody(order.getSubject());
		model.setDetail(order.getDetail());
		model.setTimeStart(order.getStartTime());
		model.setTimeExpire(order.getExpireTime());
		model.setTotalFee(order.getPayAmount());
		model.setNotifyUrl(notifyUrl);
		model.setTradeType(order.getTradeType());
		// 交易类型为：WAP 支付
		if (TradeTypeEnum.WAP.equals(order.getTradeType())) {
			model.setTradeType(TradeTypeEnum.JSAPI);
		}
		model.setOpenId(order.getOpenId());

		// 请求
		WechatPayClientUtil clientUtil = new WechatPayClientUtil(config.getAppId(), config.getMchId(), config.getApiKey());
		String resp = clientUtil.pay(model);
		LOGGER.info("wechat.pay.resp : " + resp);
		return this.parseResp(resp);
	}

	/**
	 * 解析响应的结果
	 */
	private ResponseResult parseResp(String resp) throws WechatPayException {
		// 响应字符串转换成 map
		Map<String, Object> map = XmlUtil.xmlToMap(resp);
		Map<String, String> respMap = new HashMap<>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			respMap.put(entry.getKey(), entry.getValue().toString());
		}
		ResponseResult responseResult = this.convertRespMap(resp);
		// 解析失败
		if (null == responseResult || !responseResult.isSuccess()) {
			throw new WechatPayException("wechatpay parse response exception.");
		}
		// 获取返回值中的 sign
		SignatureItem signatureItem = this.getSignatureItem(respMap);
		if (null == signatureItem) {
			throw new WechatPayException("signatureItem is null.");
		}
		if (responseResult.isSuccess() && StringUtils.isNotEmpty(signatureItem.getSign())) {
			// TODO
			boolean checkContentFlag = WechatPayUtil.checkMd5Sign(signatureItem.getSignContent(), signatureItem.getSign(), "apiKey");
			if (!checkContentFlag) {
				throw new WechatPayException("sign check fail: check Sign and Data Fail!");
			}
		}
		return responseResult;
	}

	/**
	 * 获取返回值中的sign，以及计算sign的字符串
	 */
	private SignatureItem getSignatureItem(Map<String, String> respMap) {
		if (respMap == null || respMap.isEmpty()) {
			return null;
		}
		if (StringUtils.isBlank(respMap.get("sign").toString())) {
			return null;
		}
		SignatureItem signatureItem = new SignatureItem();
		signatureItem.setSign(respMap.get("sign").toString());
		// TODO
		// 所有参与传参的参数按照accsii排序（升序）
		SortedMap<String, String> paramMap = new TreeMap<String, String>(respMap);
		signatureItem.setSignContent(WechatPayUtil.createSign("", paramMap));
		return signatureItem;
	}

	/**
	 * map 转换成 ResponseResult
	 */
	private ResponseResult convertRespMap(String resp) {
		return null;
	}

	@Override
	public String getOutTradeNo(String notify) {
		return null;
	}

	@Override
	public PayNotifyResponse parse() {
		return null;
	}

	@Override
	public boolean synchronize() {
		return false;
	}
}
