package com.fallframework.platform.starter.task.quartz.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class JobRequest extends BaseEntityRequest {

	private static final long serialVersionUID = 422811729223671154L;

	/**
	 * 任务名称
	 */
	private String jobName;

	/**
	 * 任务分组
	 */
	private String jobGroup;

	/**
	 * 任务类
	 */
	private String jobClass;

	/**
	 * 优先级
	 */
	private int triggerPriority;

	/**
	 * 触发器类型(0:表达式触发器 1:简单触发器)
	 */
	private TriggerTypeEnum triggerType = TriggerTypeEnum.CRON;

	/**
	 * 任务表达式
	 */
	private String cronExpression;

	/**
	 * 表达式中断恢复策略
	 */
	private CronMisfireEnum cronMisfire;

	/**
	 * 间隔多少秒
	 */
	private int intervalInSeconds = 0;

	/**
	 * 重复次数(-1:永久)
	 */
	private int repeatCount = 0;

	/**
	 * 简单中断恢复策略
	 */
	private SimpleMisfireEnum simpleMisfire;

	/**
	 * 任务需要传递数据使用
	 */
	private Map<String, Object> jobDataMap;

	/**
	 * 任务描述
	 */
	private String jobDescription;

	/**
	 * 触发器描述
	 */
	private String triggerDescription;

}
