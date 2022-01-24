package com.fallframework.platform.starter.task.quartz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
	@TableId(value = "SCHED_NAME", type = IdType.INPUT)
	private String schedName;

	/**
	 * qrtz_triggers表trigger_group的外键
	 */
	@TableId(value = "TRIGGER_GROUP", type = IdType.INPUT)
	private String triggerGroup;
	
}