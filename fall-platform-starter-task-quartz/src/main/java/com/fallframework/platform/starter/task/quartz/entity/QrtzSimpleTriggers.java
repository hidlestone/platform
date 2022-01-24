package com.fallframework.platform.starter.task.quartz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "qrtz_simple_triggers")
public class QrtzSimpleTriggers {

	/**
	 * 调度名称
	 */
	@TableField(value = "SCHED_NAME")
	private String schedName;

	/**
	 * qrtz_triggers表trigger_ name的外键
	 */
	@TableField(value = "TRIGGER_NAME")
	private String triggerName;

	/**
	 * qrtz_triggers表trigger_group的外键
	 */
	@TableField(value = "TRIGGER_GROUP")
	private String triggerGroup;

	/**
	 * 重复的次数统计
	 */
	@TableField(value = "REPEAT_COUNT")
	private Long repeatCount;

	/**
	 * 重复的间隔时间
	 */
	@TableField(value = "REPEAT_INTERVAL")
	private Long repeatInterval;

	/**
	 * 已经触发的次数
	 */
	@TableField(value = "TIMES_TRIGGERED")
	private Long timesTriggered;

}