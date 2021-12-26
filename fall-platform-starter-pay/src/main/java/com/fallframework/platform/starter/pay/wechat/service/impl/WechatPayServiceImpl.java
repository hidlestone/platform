package com.fallframework.platform.starter.pay.wechat.service.impl;

import com.fallframework.platform.starter.core.entity.response.ResponseResult;
import com.fallframework.platform.starter.pay.util.ZxingUtil;
import com.fallframework.platform.starter.pay.wechat.constant.WXPayConstants;
import com.fallframework.platform.starter.pay.wechat.constant.WechatPayConstant;
import com.fallframework.platform.starter.pay.wechat.model.CloseOrderRequest;
import com.fallframework.platform.starter.pay.wechat.model.OrderQueryRequest;
import com.fallframework.platform.starter.pay.wechat.model.RefundRequest;
import com.fallframework.platform.starter.pay.wechat.model.UnifiedOrderRequest;
import com.fallframework.platform.starter.pay.wechat.service.WechatPayService;
import com.fallframework.platform.starter.pay.wechat.util.WXPayUtil;
import com.fallframework.platform.starter.pay.wechat.util.WechatPayProperties;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.net.ssl.KeyManagerFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 微信支付
 *
 * @author zhuangpf
 */
@Service
public class WechatPayServiceImpl implements WechatPayService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WechatPayServiceImpl.class);

	@Autowired
	private WechatPayProperties wechatPayProperties;
	@Autowired
	private WXPayUtil WXPayUtil;
	/**
	 * 二维码存放路径
	 */
	@Value("${file.path}")
	private String filePath;

	private static final int height = 256;

	public static final String SF_FILE_SEPARATOR = System.getProperty("file.separator");//文件分隔符

	/**
	 * 统一下单<br/>
	 * 成功则返回生成的二维码的连接地址
	 *
	 * @param request 统一下单请求参数
	 * @return 成功则返回生成的二维码的连接地址
	 */
	@Override
	public ResponseResult<String> unifiedOrder(UnifiedOrderRequest request) {
		try {
			// 请求参数
			SortedMap<Object, Object> paramMap = new TreeMap<>();
			// 初始化公共的请求参数
			WXPayUtil.initCommonParams(paramMap);
			paramMap.put("product_id", request.getProduct_id());             // 商品ID
			paramMap.put("body", request.getBody());                         // 商品描述
			paramMap.put("out_trade_no", request.getOut_trade_no());         // 商户订单号
			paramMap.put("total_fee", request.getTotal_fee());               // 总金额
			paramMap.put("spbill_create_ip", request.getSpbill_create_ip()); // 发起人IP地址
			paramMap.put("notify_url", request.getNotify_url());             // 回调地址
			paramMap.put("trade_type", request.getTrade_type());             // 交易类型
			paramMap.put("sign", request.getSign());                         // 签名
			// >>> 请求
			String urlSuffix;
			if (this.wechatPayProperties.getUseSandbox()) {
				urlSuffix = WXPayConstants.SANDBOX_UNIFIEDORDER_URL_SUFFIX;
			} else {
				urlSuffix = WXPayConstants.UNIFIEDORDER_URL_SUFFIX;
			}
			// 不带凭证文件请求
			String respXML = WXPayUtil.requestWithoutCert(urlSuffix, paramMap);
			// 解析响应的XML
			Map<String, String> respMap = WXPayUtil.doXMLParse(respXML);
			// >>> 返回码
			String returnCode = respMap.get("return_code");
			if ("SUCCESS".equals(returnCode)) {
				String resultCode = respMap.get("result_code");
				if ("SUCCESS".equals(resultCode)) {
					LOGGER.info("订单号：{}，生成微信支付码成功", request.getOut_trade_no());
					// 二维码URL
					String codeUrl = respMap.get("code_url");
					String imgName = request.getOut_trade_no() + ".png";
					String imgPath = filePath + SF_FILE_SEPARATOR + imgName;
					// 生成二维码
					HashMap hashMap = new HashMap();
					hashMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
					hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
					hashMap.put(EncodeHintType.MARGIN, 2);
					BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE, height, height, hashMap);
					ZxingUtil.writeToFile(bitMatrix, "png", new File(imgPath));
					return ResponseResult.success(imgPath);
				} else {
					String errCodeDes = respMap.get("err_code_des");
					LOGGER.error("订单号：{}生成微信支付码(系统)失败:{}", request.getOut_trade_no(), errCodeDes);
					return ResponseResult.fail(errCodeDes);
				}
			} else {
				String returnMsg = respMap.get("return_msg");
				LOGGER.error("(订单号：{}生成微信支付码(通信)失败:{}", request.getOut_trade_no(), returnMsg);
				return ResponseResult.fail(returnMsg);
			}
		} catch (Exception e) {
			LOGGER.error("订单号：{}生成微信支付码失败(系统异常))", request.getOut_trade_no(), e);
			return ResponseResult.fail();
		}
	}

	/**
	 * 订单查询<br/>
	 * 场景：刷卡支付、公共号支付、扫码支付、APP支付
	 * <p>
	 * SUCCESS—支付成功
	 * REFUND—转入退款
	 * NOTPAY—未支付
	 * CLOSED—已关闭
	 * REVOKED—已撤销（刷卡支付）
	 * USERPAYING--用户支付中
	 * PAYERROR--支付失败(其他原因，如银行返回失败)
	 * 支付状态机请见下单API页面
	 */
	@Override
	public ResponseResult<String> orderQuery(OrderQueryRequest request) {
		String respXML = "";
		try {
			// 请求参数
			SortedMap<Object, Object> paramMap = new TreeMap<>();
			// 初始化公共的请求参数
			WXPayUtil.initCommonParams(paramMap);
			paramMap.put("out_trade_no", request.getOut_trade_no());// 商户订单号
			paramMap.put("sign", request.getSign());// 签名
			// 将请求参数转换成XML格式
			String requestXML = WXPayUtil.constructRequestXml(paramMap);
			// >>> 请求
			String urlSuffix;
			if (this.wechatPayProperties.getUseSandbox()) {
				urlSuffix = WXPayConstants.SANDBOX_ORDERQUERY_URL_SUFFIX;
			} else {
				urlSuffix = WXPayConstants.ORDERQUERY_URL_SUFFIX;
			}
			respXML = WXPayUtil.requestWithoutCert(urlSuffix, paramMap);

			LOGGER.info("orderQuery : " + requestXML);

			// 解析响应的XML
			Map<String, String> respMap = WXPayUtil.doXMLParse(respXML);
			// 返回码
			String returnCode = respMap.get("return_code");
			if (WechatPayConstant.SUCCESS.equalsIgnoreCase(returnCode)) {
				String resultCode = respMap.get("result_code");
				if (WechatPayConstant.SUCCESS.equalsIgnoreCase(resultCode)) {
					String tradeState = respMap.get("trade_state");
					LOGGER.info(tradeState);
				} else {
					String errCodeDes = respMap.get("err_code_des");
					LOGGER.info(errCodeDes);
				}
			} else {
				String returnMsg = respMap.get("return_msg");
				LOGGER.info(returnMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseResult.success(respXML);
	}

	/**
	 * 关闭订单
	 */
	@Override
	public ResponseResult closeOrder(CloseOrderRequest request) {
		try {
			// 请求参数
			SortedMap<Object, Object> paramMap = new TreeMap<>();
			// 初始化公共的请求参数
			WXPayUtil.initCommonParams(paramMap);
			paramMap.put("out_trade_no", request.getOut_trade_no());         // 商户订单号
			paramMap.put("sign", request.getSign());                         // 签名
			// 将请求参数转换成XML格式
			String requestXML = WXPayUtil.constructRequestXml(paramMap);
			// >>> 请求
			String urlSuffix;
			if (this.wechatPayProperties.getUseSandbox()) {
				urlSuffix = WXPayConstants.SANDBOX_CLOSEORDER_URL_SUFFIX;
			} else {
				urlSuffix = WXPayConstants.CLOSEORDER_URL_SUFFIX;
			}
			String respXML = WXPayUtil.requestWithoutCert(urlSuffix, paramMap);
			// 解析响应的XML
			Map<String, String> respMap = WXPayUtil.doXMLParse(respXML);
			// 返回码
			String returnCode = respMap.get("return_code");
			if (WXPayConstants.SUCCESS.equalsIgnoreCase(returnCode)) {
				String resultCode = respMap.get("result_code");
				if (WechatPayConstant.SUCCESS.equalsIgnoreCase(resultCode)) {
					return ResponseResult.success(respMap.get("result_msg"));
				} else {
					String errCodeDes = respMap.get("err_code_des");
					LOGGER.info(errCodeDes);
					return ResponseResult.fail(errCodeDes, respMap.get("result_msg"));
				}
			} else {
				return ResponseResult.fail(returnCode, respMap.get("return_msg"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseResult.success();
	}

	/**
	 * 申请退款
	 */
	@Override
	public ResponseResult refund(RefundRequest request) {

		try {
			// 请求参数
			SortedMap<Object, Object> paramMap = new TreeMap<>();
			// 初始化公共的请求参数
			WXPayUtil.initCommonParams(paramMap);
			paramMap.put("out_trade_no", request.getOut_trade_no());         // 商户订单号
			paramMap.put("sign", request.getSign());                         // 签名
			// 将请求参数转换成XML格式
			String requestXML = WXPayUtil.constructRequestXml(paramMap);
			// >>> 请求
			String urlSuffix;
			if (this.wechatPayProperties.getUseSandbox()) {
				urlSuffix = WXPayConstants.SANDBOX_REFUND_URL_SUFFIX;
			} else {
				urlSuffix = WXPayConstants.REFUND_URL_SUFFIX;
			}

			// 证书 TODO
			String filePath = "";
			char[] password = wechatPayProperties.getMchId().toCharArray();
			InputStream certStream = new BufferedInputStream(new FileInputStream(filePath));
			KeyStore ks = KeyStore.getInstance("PKCS12");
			ks.load(certStream, password);
			// 实例化密钥库 & 初始化密钥工厂
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(ks, password);

			String respXML = WXPayUtil.requestWithCert(urlSuffix, paramMap, kmf);
			// 解析响应的XML
			Map<String, String> respMap = WXPayUtil.doXMLParse(respXML);
			// 返回码
			String returnCode = respMap.get("return_code");
			if (WXPayConstants.SUCCESS.equalsIgnoreCase(returnCode)) {
				String resultCode = respMap.get("result_code");
				if (WechatPayConstant.SUCCESS.equalsIgnoreCase(resultCode)) {
					return ResponseResult.success(respMap.get("result_msg"));
				} else {
					String errCodeDes = respMap.get("err_code_des");
					LOGGER.info(errCodeDes);
					return ResponseResult.fail(errCodeDes, respMap.get("result_msg"));
				}
			} else {
				return ResponseResult.fail(returnCode, respMap.get("return_msg"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseResult.success();
	}

	@Override
	public String refundQuery() {
		return null;
	}

	@Override
	public String downloadBill() {
		return null;
	}
}
