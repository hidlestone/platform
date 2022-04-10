package com.fallframework.platform.starter.activiti.service.impl;

import com.alibaba.fastjson.JSON;
import com.fallframework.platform.starter.activiti.model.TaskResponse;
import com.fallframework.platform.starter.activiti.service.ActTaskService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuangpf
 */
@Service
public class ActTaskServiceImpl implements ActTaskService {

	@Autowired
	private TaskService taskService;

	@Override
	public ResponseResult<List<TaskResponse>> getPendingTaskList(String userId, String procdefKey) {
		List<Task> taskList = taskService.createTaskQuery()
				.processDefinitionKey(procdefKey)
				.taskAssignee(userId)
				.list();
		// TODO
		List<TaskResponse> taskResponseList = JSON.parseArray(JSON.toJSONString(taskList), TaskResponse.class);
		return ResponseResult.success(taskResponseList);
	}

	@Override
	public ResponseResult doCompletTask(String userId, String taskId) {
		return null;
	}

}
