package com.fallframework.platform.starter.task.quartz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "qrtz_scheduler_state")
public class QrtzSchedulerState {

	/**
	 * 调度名称
	 */
	@TableField(value = "SCHED_NAME")
	private String schedName;

	/**
	 * 之前配置文件中org.quartz.scheduler.instanceId配置的名字，就会写入该字段
	 */
	@TableField(value = "INSTANCE_NAME")
	private String instanceName;

	/**
	 * 上次检查时间
	 */
	@TableField(value = "LAST_CHECKIN_TIME")
	private Long lastCheckinTime;

	/**
	 * 检查间隔时间
	 */
	@TableField(value = "CHECKIN_INTERVAL")
	private Long checkinInterval;

}