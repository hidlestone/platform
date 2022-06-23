package com.fallframework.platform.starter.wechatwork.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.wechatwork.model.SynStrategyEnum;
import com.fallframework.platform.starter.wechatwork.model.SynTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("wxwork_syn_config")
public class SynConfig extends WXWorkBaseEntity {

	private static final long serialVersionUID = -2075536771904012553L;

	/**
	 * 配置id
	 */
	@TableId(value = "id", type = IdType.INPUT)
	private Long id;

	/**
	 * 同步类型
	 */
	@TableField("syn_type")
	private SynTypeEnum synType;

	/**
	 * 同步策略
	 */
	@TableField("syn_strategy")
	private SynStrategyEnum synStrategy;

	/**
	 * 是否全量同步过
	 */
	@TableField("full_synced")
	private Byte fullSynced;

}