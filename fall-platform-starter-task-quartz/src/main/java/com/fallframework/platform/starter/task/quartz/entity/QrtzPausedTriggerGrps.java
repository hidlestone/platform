package com.fallframework.platform.starter.task.quartz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "qrtz_paused_trigger_grps")
public class QrtzPausedTriggerGrps {

	/**
	 * 调度名称
	 */
	@TableField(value = "SCHED_NAME")
	private String schedName;

	/**
	 * qrtz_triggers表trigger_group的外键
	 */
	@TableField(value = "TRIGGER_GROUP")
	private String triggerGroup;

}