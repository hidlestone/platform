package com.fallframework.platform.starter.pay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.data.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "pay_wechat_config")
public class PayWechatConfig extends BaseEntity {
	
	private static final long serialVersionUID = -1006167654737504894L;
	
	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.INPUT)
	private Long id;

	/**
	 * 支付类型编码：PayTypeEnum
	 */
	@TableField(value = "pay_type_code")
	private String payTypeCode;

	/**
	 * 加密类型：EncryptTypeEnum
	 */
	@TableField(value = "encrypt_type")
	private String encryptType;

	/**
	 * 仅微信使用，凭证文件Id
	 */
	@TableField(value = "cert_file_id")
	private String certFileId;

	/**
	 * 仅微信使用，开通微信支付后，会把微信支付的账号，密码，以及apikey发给开发者用于签名
	 */
	@TableField(value = "api_key")
	private String apiKey;

	/**
	 * 微信为公众账号Id
	 */
	@TableField(value = "app_id")
	private String appId;

	/**
	 * 商户Id/合作伙伴Id，例如微信为12开头的一串数字(账户信息-微信支付商户号)
	 */
	@TableField(value = "mch_id")
	private String mchId;

	/**
	 * 状态
	 */
	@TableField(value = "status")
	private Boolean status;
	
}