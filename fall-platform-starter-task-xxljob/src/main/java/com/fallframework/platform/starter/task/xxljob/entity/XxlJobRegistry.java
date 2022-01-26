package com.fallframework.platform.starter.task.xxljob.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName(value = "xxl_job_registry")
public class XxlJobRegistry {

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@TableField(value = "registry_group")
	private String registryGroup;

	@TableField(value = "registry_key")
	private String registryKey;

	@TableField(value = "registry_value")
	private String registryValue;

	@TableField(value = "update_time")
	private Date updateTime;

}