package com.fallframework.platform.starter.activiti.service.impl;

import com.fallframework.platform.starter.activiti.model.PendingTaskRequest;
import com.fallframework.platform.starter.activiti.service.ActTaskService;
import com.fallframework.platform.starter.api.model.Leaf;
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
	public ResponseResult<Leaf<Task>> getPendingTaskList(PendingTaskRequest request) {
		List<Task> taskList = taskService.createTaskQuery()
				.processDefinitionKey(request.getProcdefKey())
				.taskAssignee(request.getAssignee())
				.listPage(request.getFirstRowFromNumZero(), request.getPageSize());
		Leaf leaf = new Leaf(taskList, 0, 1, 1);
		return ResponseResult.success(leaf);
	}

	@Override
	public ResponseResult doCompletTask(String userId, String taskId) {
		return null;
	}

}
