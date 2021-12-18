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
@TableName(value = "pay_type")
public class PayType extends BaseEntity {

	private static final long serialVersionUID = -5368827267357351090L;

	/**
	 * 支付类型编码
	 */
	@TableId(value = "code", type = IdType.INPUT)
	private String code;

	/**
	 * 支付类型名称
	 */
	@TableField(value = "name")
	private String name;

	/**
	 * 支付描述
	 */
	@TableField(value = "desc")
	private String desc;

	/**
	 * 状态
	 */
	@TableField(value = "status")
	private Byte status;

}