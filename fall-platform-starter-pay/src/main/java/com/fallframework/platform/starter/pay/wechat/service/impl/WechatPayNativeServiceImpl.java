package com.fallframework.platform.starter.pay.wechat.service.impl;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.fallframework.platform.starter.core.entity.response.ResponseResult;
import com.fallframework.platform.starter.pay.util.ZxingUtil;
import com.fallframework.platform.starter.pay.wechat.constant.WechatPayUrl;
import com.fallframework.platform.starter.pay.wechat.enums.TradeTypeEnum;
import com.fallframework.platform.starter.pay.wechat.model.UnifiedOrderRequest;
import com.fallframework.platform.starter.pay.wechat.service.WechatPayNativeService;
import com.fallframework.platform.starter.pay.wechat.util.WechatPayProperties;
import com.fallframework.platform.starter.pay.wechat.util.WechatPayUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author zhuangpf
 */
public class WechatPayNativeServiceImpl implements WechatPayNativeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WechatPayNativeServiceImpl.class);

	@Autowired
	private WechatPayProperties wechatPayProperties;
	@Autowired
	private WechatPayUtil wechatPayUtil;
	/**
	 * 二维码存放路径
	 */
	@Value("${file.path}")
	private String filePath;

	private static final int height = 256;

	public static final String SF_FILE_SEPARATOR = System.getProperty("file.separator");//文件分隔符

	@Override
	public ResponseResult<String> unifiedOrder(UnifiedOrderRequest request) {
		try {
			// 交易类型：原生扫码支付
			String trade_type = TradeTypeEnum.NATIVE.getCode();
			// 请求参数
			SortedMap<Object, Object> paramMap = new TreeMap<>();
			// 初始化公共的请求参数
			wechatPayUtil.initCommonParams(paramMap);
			paramMap.put("product_id", request.getProduct_id());// 商品ID
			paramMap.put("body", request.getBody());// 商品描述
			paramMap.put("out_trade_no", request.getOut_trade_no());// 商户订单号
			paramMap.put("total_fee", request.getTotal_fee());// 总金额
			paramMap.put("spbill_create_ip", request.getSpbill_create_ip());// 发起人IP地址
			paramMap.put("notify_url", request.getNotify_url());// 回调地址
			paramMap.put("trade_type", request.getTrade_type());// 交易类型
			paramMap.put("sign", request.getSign());// 签名
			// 将请求参数转换成XML格式
			String requestXML = WechatPayUtil.constructRequestXml(paramMap);
			// 请求
			//插件式配置请求参数（网址、请求参数、编码、client）
			HttpConfig config = HttpConfig.custom()
					//				.headers(headers)	//设置headers，不需要时则无需设置
					.timeout(1000)        //超时
					.url(WechatPayUrl.UNIFIED_ORDER_URL)           //设置请求的url
					.encoding("utf-8")  //设置请求和返回编码，默认就是Charset.defaultCharset()
					//.inenc("utf-8")   //设置请求编码，如果请求返回一直，不需要再单独设置
					//.inenc("utf-8")   //设置返回编码，如果请求返回一直，不需要再单独设置
					.json(requestXML) //json方式请求的话，就不用设置map方法，当然二者可以共用。
					//.context(HttpCookies.custom().getContext())      //设置cookie，用于完成携带cookie的操作
					//.out(new FileOutputStream("保存地址"))              //下载的话，设置这个方法,否则不要设置
					//.files(new String[]{"d:/1.txt","d:/2.txt"})      //上传的话，传递文件路径，一般还需map配置，设置服务器保存路径
					;
			// 响应
			String respXML = HttpClientUtil.post(config);
			// 解析响应的XML
			Map<String, String> respMap = WechatPayUtil.doXMLParse(requestXML);
			// 返回码
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

	@Override
	public String orderQuery() {
		return null;
	}

	@Override
	public String closeOrder() {
		return null;
	}

	@Override
	public String refund() {
		return null;
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
