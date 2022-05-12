package com.fallframework.platform.starter.config.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.api.model.StatusEnum;
import com.fallframework.platform.starter.data.mp.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@TableName(value = "s_sys_param_group")
public class SysParamGroup extends BaseEntity {

	private static final long serialVersionUID = -7376969741515058697L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 系统参数组编码
	 */
	@TableId(value = "code")
	private String code;

	/**
	 * 系统参数组描述
	 */
	@TableField(value = "`desc`")
	private String desc;

	/**
	 * 是否启用
	 */
	@TableField(value = "`status`")
	private StatusEnum status;

	@TableField(exist = false)
	private List<SysParamItem> sysParamItems;

}