package com.fallframework.platform.starter.activiti.service;

import com.fallframework.platform.starter.activiti.model.AssignTaskRequest;
import com.fallframework.platform.starter.activiti.model.CompleteTaskRequest;
import com.fallframework.platform.starter.activiti.model.PendingTaskRequest;
import com.fallframework.platform.starter.activiti.model.RejectTaskRequest;
import com.fallframework.platform.starter.activiti.model.TaskQueryRequest;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.engine.task.Task;

public interface ActTaskService {

	/**
	 * 分页查询任务
	 *
	 * @param request 请求参数
	 * @return 任务列表分页
	 */
	ResponseResult<Leaf<Task>> getTaskList(TaskQueryRequest request);

	/**
	 * 查询用户待处理任务列表
	 *
	 * @param request 请求参数
	 * @return 待处理任务列表
	 */
	ResponseResult<Leaf<Task>> getPendingTaskList(PendingTaskRequest request);

	/**
	 * 完成任务
	 *
	 * @param request 请求参数
	 * @return 是否成功
	 */
	ResponseResult completTask(CompleteTaskRequest request);

	/**
	 * 任务驳回
	 *
	 * @param request 请求参数
	 * @return 是否成功
	 */
	ResponseResult rejectTask(RejectTaskRequest request);

	/**
	 * 指派任务
	 *
	 * @param request 请求参数
	 * @return 是否成功
	 */
	ResponseResult assignTask(AssignTaskRequest request);

	/**
	 * 获取任务详细信息
	 *
	 * @param taskId 任务ID
	 * @return 任务详细信息
	 */
	ResponseResult getTaskDetail(String taskId);

}
