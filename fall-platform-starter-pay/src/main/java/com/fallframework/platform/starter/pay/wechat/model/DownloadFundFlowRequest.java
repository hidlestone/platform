package com.fallframework.platform.starter.pay.wechat.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 下载资金账单请求参数
 *
 * @author zhuangpf
 */
@Getter
@Setter
public class DownloadFundFlowRequest extends BaseEntityRequest {

	private static final long serialVersionUID = 5053024815286587851L;

	/**
	 * 微信支付分配的公众账号ID（企业号corpid即为此appid）
	 */
	private String appid;

	/**
	 * 微信支付分配的商户号
	 */
	private String mch_id;

	/**
	 * 随机字符串，不长于32位。推荐随机数生成算法
	 */
	private String nonce_str;

	/**
	 * 签名，详见签名生成算法
	 */
	private String sign;

	/**
	 * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
	 */
	private String sign_type;

	/**
	 * 下载对账单的日期，格式：20140603
	 */
	private String bill_date;

	/**
	 * 账单的资金来源账户：
	 * <p>
	 * Basic  基本账户
	 * <p>
	 * Operation 运营账户
	 * <p>
	 * Fees 手续费账户
	 */
	private String account_type;

	/**
	 * 非必传参数，固定值：GZIP，返回格式为.gzip的压缩包账单。不传则默认为数据流形式。
	 */
	private String tar_type;

}
