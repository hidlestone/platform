package com.fallframework.platform.starter.activiti.service;

import com.fallframework.platform.starter.activiti.model.AssignTaskDto;
import com.fallframework.platform.starter.activiti.model.CompleteTaskDto;
import com.fallframework.platform.starter.activiti.model.PendingTaskDto;
import com.fallframework.platform.starter.activiti.model.RejectTaskDto;
import com.fallframework.platform.starter.activiti.model.TaskDetailOutVo;
import com.fallframework.platform.starter.activiti.model.TaskQueryDto;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.engine.task.Task;

public interface ActTaskService {

	/**
	 * 分页查询任务
	 *
	 * @param dto 请求参数
	 * @return 任务列表分页
	 */
	ResponseResult<Leaf<Task>> getTaskList(TaskQueryDto dto);

	/**
	 * 查询用户待处理任务列表
	 *
	 * @param dto 请求参数
	 * @return 待处理任务列表
	 */
	ResponseResult<Leaf<Task>> getPendingTaskList(PendingTaskDto dto);

	/**
	 * 完成任务
	 *
	 * @param dto 请求参数
	 * @return 是否成功
	 */
	ResponseResult completTask(CompleteTaskDto dto);

	/**
	 * 任务驳回
	 *
	 * @param dto 请求参数
	 * @return 是否成功
	 */
	ResponseResult rejectTask(RejectTaskDto dto);

	/**
	 * 指派任务
	 *
	 * @param dto 请求参数
	 * @return 是否成功
	 */
	ResponseResult assignTask(AssignTaskDto dto);

	/**
	 * 获取任务详细信息
	 *
	 * @param taskId 任务ID
	 * @return 任务详细信息
	 */
	ResponseResult<TaskDetailOutVo> getTaskDetail(String taskId);

}
