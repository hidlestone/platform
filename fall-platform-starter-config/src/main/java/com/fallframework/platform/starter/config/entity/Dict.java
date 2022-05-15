package com.fallframework.platform.starter.config.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.api.model.StatusEnum;
import com.fallframework.platform.starter.data.mp.entity.BaseEntity;
import com.fallframework.platform.starter.i18n.entity.I18nResource;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@TableName(value = "s_dict")
public class Dict extends BaseEntity {

	private static final long serialVersionUID = -1048086286087613537L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 字典编码
	 */
	@TableField(value = "`code`")
	private String code;

	/**
	 * 字典描述
	 */
	@TableField(value = "`desc`")
	private String desc;

	/**
	 * 是否启用
	 */
	@TableField(value = "`status`")
	private StatusEnum status;

	/**
	 * 字典多语言词条
	 */
	@TableField(exist = false)
	private List<I18nResource> i18nResources;

	/**
	 * 字典项明细
	 */
	@TableField(exist = false)
	private List<DictDtl> dictDtls;

}