package com.fallframework.platform.starter.task.quartz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "qrtz_fired_triggers")
public class QrtzFiredTriggers {
	
	/**
	 * 调度名称
	 */
	@TableId(value = "SCHED_NAME", type = IdType.INPUT)
	private String schedName;

	/**
	 * 调度器实例id
	 */
	@TableId(value = "ENTRY_ID", type = IdType.INPUT)
	private String entryId;

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
	 * 调度器实例名
	 */
	@TableField(value = "INSTANCE_NAME")
	private String instanceName;

	/**
	 * 触发的时间
	 */
	@TableField(value = "FIRED_TIME")
	private Long firedTime;

	/**
	 * 定时器制定的时间
	 */
	@TableField(value = "SCHED_TIME")
	private Long schedTime;

	/**
	 * 优先级
	 */
	@TableField(value = "PRIORITY")
	private Integer priority;

	/**
	 * 状态
	 */
	@TableField(value = "STATE")
	private String state;

	/**
	 * 集群中job的名字
	 */
	@TableField(value = "JOB_NAME")
	private String jobName;

	/**
	 * 集群中job的所属组的名字
	 */
	@TableField(value = "JOB_GROUP")
	private String jobGroup;

	/**
	 * 是否并发
	 */
	@TableField(value = "IS_NONCONCURRENT")
	private String isNonconcurrent;

	/**
	 * 是否接受恢复执行，默认为false，设置了RequestsRecovery为true，则会被重新执行
	 */
	@TableField(value = "REQUESTS_RECOVERY")
	private String requestsRecovery;

}