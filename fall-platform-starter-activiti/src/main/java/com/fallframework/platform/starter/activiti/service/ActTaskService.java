package com.fallframework.platform.starter.activiti.service;

import com.fallframework.platform.starter.activiti.model.TaskResponse;
import com.fallframework.platform.starter.api.response.ResponseResult;

import java.util.List;

public interface ActTaskService {

	/**
	 * 查询用户待处理任务列表
	 *
	 * @param userId     用户ID
	 * @param procdefKey 流程定义key
	 * @return 待处理任务列表
	 */
	ResponseResult<List<TaskResponse>> getPendingTaskList(String userId, String procdefKey);

	/**
	 * 完成任务
	 *
	 * @param userId 用户ID
	 * @param taskId 任务ID
	 * @return 是否成功
	 */
	ResponseResult doCompletTask(String userId, String taskId);

}
