package com.fallframework.platform.starter.task.quartz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
	@TableId(value = "SCHED_NAME", type = IdType.INPUT)
	private String schedName;

	/**
	 * qrtz_triggers表trigger_ name的外键
	 */
	@TableId(value = "TRIGGER_NAME", type = IdType.INPUT)
	private String triggerName;

	/**
	 * qrtz_triggers表trigger_group的外键
	 */
	@TableId(value = "TRIGGER_GROUP", type = IdType.INPUT)
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