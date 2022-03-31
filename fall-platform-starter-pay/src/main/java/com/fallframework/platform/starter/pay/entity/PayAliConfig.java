package com.fallframework.platform.starter.pay.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.data.mp.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "pay_ali_config")
public class PayAliConfig extends BaseEntity {

	private static final long serialVersionUID = -273401550157282503L;
	
	/**
	 * 主键
	 */
	@TableField(value = "id")
	private Long id;

	/**
	 * 支付类型编码
	 */
	@TableField(value = "pay_type_code")
	private String payTypeCode;

	/**
	 * 加密方式
	 */
	@TableField(value = "encrypt_type")
	private String encryptType;

	/**
	 * 支付宝为20开头的一串数字（管理中心-我的应用）
	 */
	@TableField(value = "app_id")
	private String appId;

	/**
	 * 支付宝为（从我的应用-查看-使用者管理-使用者Id）
	 */
	@TableField(value = "mch_id")
	private String mchId;

	/**
	 * 状态
	 */
	@TableField(value = "status")
	private Boolean status;

	/**
	 * 商户私钥，商户公钥需要在支付宝开放平台设置，
	 */
	@TableField(value = "private_key")
	private String privateKey;

	/**
	 * 支付宝公钥，由支付宝开放平台提供
	 */
	@TableField(value = "public_key")
	private String publicKey;

}