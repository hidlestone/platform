package com.fallframework.platform.starter.task.quartz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "qrtz_cron_triggers")
public class QrtzCronTriggers {

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
	 * cron表达式
	 */
	@TableField(value = "CRON_EXPRESSION")
	private String cronExpression;

	/**
	 * 时区
	 */
	@TableField(value = "TIME_ZONE_ID")
	private String timeZoneId;

}