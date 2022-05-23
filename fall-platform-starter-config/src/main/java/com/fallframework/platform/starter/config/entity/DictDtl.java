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
@TableName(value = "s_dict_dtl")
public class DictDtl extends BaseEntity {

	private static final long serialVersionUID = -8971147452183256727L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 字典项ID
	 */
	@TableField(value = "dict_id")
	private Long dictId;

	/**
	 * 明细编码
	 */
	@TableField(value = "`code`")
	private String code;

	/**
	 * 明细值
	 */
	@TableField(value = "`value`")
	private String value;
	
	/**
	 * 明细描述
	 */
	@TableField(value = "`desc`")
	private String desc;

	/**
	 * 是否启用
	 */
	@TableField(value = "`status`")
	private StatusEnum status;

	/**
	 * 字典明细多语言词条
	 */
	@TableField(exist = false)
	private List<I18nResource> i18nResources;

}
