package com.fallframework.platform.starter.task.quartz.service;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.task.quartz.model.JobRequest;
import com.fallframework.platform.starter.task.quartz.model.TaskRequest;
import com.fallframework.platform.starter.task.quartz.model.TaskResponse;

import java.util.List;

public interface JobService {

	/**
	 * 保存定时任务
	 *
	 * @param request 请求参数
	 * @return 是否保存成功
	 */
	ResponseResult saveJob(JobRequest request);

	/**
	 * 删除定时任务
	 *
	 * @param request 请求参数
	 * @return 是否删除成功
	 */
	ResponseResult deleteJob(JobRequest request);

	/**
	 * 更新定时任务
	 *
	 * @param request 请求参数
	 * @return 是否更新成功
	 */
	ResponseResult updateJob(JobRequest request);

	/**
	 * 查询所有的任务列表
	 *
	 * @return 所有任务列表
	 */
	ResponseResult<List<TaskResponse>> jobList();

	/**
	 * 暂停定时任务
	 *
	 * @param request 请求参数
	 * @return 是否暂停成功
	 */
	ResponseResult pause(TaskRequest request);

	/**
	 * 重新开始定时任务
	 *
	 * @param request 请求参数
	 * @return 是否重启成功
	 */
	ResponseResult resume(TaskRequest request);

}
