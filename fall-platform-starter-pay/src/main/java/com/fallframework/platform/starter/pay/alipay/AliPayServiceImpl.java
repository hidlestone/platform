package com.fallframework.platform.starter.pay.alipay;

import com.fallframework.platform.starter.pay.util.WebPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 扫码支付wiki：https://doc.open.alipay.com/doc2/detail?spm=0.0.0.0.E3tvGh&treeId=26&articleId=103286&docType=1
 * 扫码支付，异步回调，订单查询，申请退款
 * <p/>
 * 网页支付wiki:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.08w3Le&treeId=60&articleId=103564&docType=1
 * 网页支付接口，demo，签名规则，异步回调，同步回调，申请退款
 * <p/>
 * 移动支付（APP支付）wiki:
 * https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.Tn7dlq&treeId=59&articleId=103663&docType=1
 * app支付，签名机制，客户端调用， 异步回调
 * <p/>
 * 开发工具包下载， 扫码支付demo，app支付demo:
 * https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.S9aWYF&treeId=54&articleId=104506&docType=1
 * <p/>
 * 支付API：
 * https://doc.open.alipay.com/docs/api.htm?spm=a219a.7386797.0.0.ppgPy5&docType=4&apiId=757
 * <p/>
 * 支付宝秘钥见：README.md
 */
@Service
public class AliPayServiceImpl implements AliPayService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AliPayService.class);

	private static final BigDecimal HUNDRED = new BigDecimal("100");

	// 通知url必须为直接可访问的url，不能携带参数
	private String notifyUrl = WebPropertiesUtil.getInstance().getValue("ali.pay.notify.url");
	
	
	
	

}
