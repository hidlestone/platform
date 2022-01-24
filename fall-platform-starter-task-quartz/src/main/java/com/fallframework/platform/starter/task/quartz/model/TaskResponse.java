package com.fallframework.platform.starter.task.quartz.model;

import com.fallframework.platform.starter.api.response.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class TaskResponse extends BaseEntityResponse {

	private static final long serialVersionUID = 5312867199737553334L;

	/**
	 * 调度名称
	 */
	private String schedulerName;

	/**
	 * 调度实例ID
	 */
	private String schedulerInstanceId;
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
	 * 任务描述
	 */
	private String jobDescription;
	/**
	 * 触发器描述
	 */
	private String triggerDescription;

	/**
	 * 优先级
	 */
	private int triggerPriority;

	/**
	 * 任务状态
	 */
	private String jobStatus;

	/**
	 * 任务表达式
	 */
	private String cronExpression;

	/**
	 * 任务需要传递数据使用
	 */
	private Map<String, Object> jobDataMap;

	//***********show*****************

	/**
	 * 上次触发时间
	 */
	private String previousFireTime;

	/**
	 * 下次触发时间
	 */
	private String nextFileTime;

	/**
	 * 创建时间
	 */
	private String createTime;

}
