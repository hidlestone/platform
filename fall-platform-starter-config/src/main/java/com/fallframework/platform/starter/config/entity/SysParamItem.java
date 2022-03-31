package com.fallframework.platform.starter.config.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.api.model.StatusEnum;
import com.fallframework.platform.starter.data.mp.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "s_sys_param_item")
public class SysParamItem extends BaseEntity {

	private static final long serialVersionUID = -735051379561213142L;

	/**
	 * 系统参数编码
	 */
	@TableId(value = "`code`", type = IdType.INPUT)
	private String code;

	/**
	 * 系统参数值
	 */
	@TableField(value = "`value`")
	private String value;

	/**
	 * 加密值
	 */
	@TableField(value = "`encrypted_value`")
	private String encryptedValue;

	/**
	 * 系统参数描述
	 */
	@TableField(value = "`desc`")
	private String desc;

	/**
	 * 是否启用：0-停用，1-启用
	 */
	@TableField(value = "`status`")
	private StatusEnum status;

	/**
	 * 系统参数组编码
	 */
	@TableField(value = "`group_code`")
	private String groupCode;

}