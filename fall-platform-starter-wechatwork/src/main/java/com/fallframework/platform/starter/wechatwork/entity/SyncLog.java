package com.fallframework.platform.starter.wechatwork.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("wxwork_sync_log")
public class SyncLog extends WXWorkBaseEntity {

	private static final long serialVersionUID = -2486776350540446521L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.INPUT)
	private Long id;

	/**
	 * 所属配置id
	 */
	@TableField("config_id")
	private Long configId;

	/**
	 * 同步结果编码
	 */
	@TableField("errcode")
	private Integer errcode;

	/**
	 * 同步结果信息
	 */
	@TableField("errmsg")
	private String errmsg;

}