package com.fallframework.platform.starter.task.quartz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "qrtz_blob_triggers")
public class QrtzBlobTriggers {

	/**
	 * 调度名称
	 */
	@TableField(value = "SCHED_NAME")
	private String schedName;

	/**
	 * qrtz_triggers表trigger_name的外键
	 */
	@TableField(value = "TRIGGER_NAME")
	private String triggerName;

	/**
	 * qrtz_triggers表trigger_group的外键
	 */
	@TableField(value = "TRIGGER_GROUP")
	private String triggerGroup;

	/**
	 * 一个blob字段，存放持久化Trigger对象
	 */
	@TableField(value = "BLOB_DATA")
	private byte[] blobData;

}