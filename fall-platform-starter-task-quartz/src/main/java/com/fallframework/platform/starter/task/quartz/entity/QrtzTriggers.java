package com.fallframework.platform.starter.task.quartz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@TableName(value = "qrtz_triggers")
public class QrtzTriggers {

	/**
	 * 调度名称
	 */
	@TableField(value = "SCHED_NAME")
	private String schedName;

	/**
	 * 触发器的名字
	 */
	@TableField(value = "TRIGGER_NAME")
	private String triggerName;

	/**
	 * 触发器所属组的名字
	 */
	@TableField(value = "TRIGGER_GROUP")
	private String triggerGroup;

	/**
	 * qrtz_job_details表job_name的外键
	 */
	@TableField(value = "JOB_NAME")
	private String jobName;

	/**
	 * qrtz_job_details表job_group的外键
	 */
	@TableField(value = "JOB_GROUP")
	private String jobGroup;

	/**
	 * 详细描述信息
	 */
	@TableField(value = "DESCRIPTION")
	private String description;

	/**
	 * 下一次触发时间，默认为-1，意味不会自动触发
	 */
	@TableField(value = "NEXT_FIRE_TIME")
	private Long nextFireTime;

	/**
	 * 上一次触发时间（毫秒）
	 */
	@TableField(value = "PREV_FIRE_TIME")
	private Long prevFireTime;

	/**
	 * 优先级
	 */
	@TableField(value = "PRIORITY")
	private Integer priority;

	/**
	 * 当前触发器状态，设置为ACQUIRED,如果设置为WAITING，则job不会触发（WAITING:等待，PAUSED:暂停，ACQUIRED:正常执行，BLOCKED：阻塞，ERROR：错误）
	 */
	@TableField(value = "TRIGGER_STATE")
	private String triggerState;

	/**
	 * 触发器的类型，使用cron表达式
	 */
	@TableField(value = "TRIGGER_TYPE")
	private String triggerType;

	/**
	 * 开始时间
	 */
	@TableField(value = "START_TIME")
	private Long startTime;

	/**
	 * 结束时间
	 */
	@TableField(value = "END_TIME")
	private Long endTime;

	/**
	 * 日程表名称，表qrtz_calendars的calendar_name字段外键
	 */
	@TableField(value = "CALENDAR_NAME")
	private String calendarName;

	/**
	 * 日程表名称，表qrtz_calendars的calendar_name字段外键
	 */
	@TableField(value = "MISFIRE_INSTR")
	private Short misfireInstr;

	/**
	 * 存放持久化job对象
	 */
	@TableField(value = "JOB_DATA")
	private byte[] jobData;

}