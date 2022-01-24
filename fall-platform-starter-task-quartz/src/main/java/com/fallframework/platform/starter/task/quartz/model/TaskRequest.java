package com.fallframework.platform.starter.task.quartz.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class TaskRequest extends BaseEntityRequest {

	private static final long serialVersionUID = 6019233858853367703L;

	/**
	 * 任务名称
	 */
	private String jobName;
	
	/**
	 * 任务分组
	 */
	private String jobGroup;

}
