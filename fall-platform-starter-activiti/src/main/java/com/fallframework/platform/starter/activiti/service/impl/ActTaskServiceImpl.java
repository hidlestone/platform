package com.fallframework.platform.starter.activiti.service.impl;

import com.fallframework.platform.starter.activiti.model.CompletTaskRequest;
import com.fallframework.platform.starter.activiti.model.PendingTaskRequest;
import com.fallframework.platform.starter.activiti.service.ActTaskService;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
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
		// 查询条件
		TaskQuery taskQuery = taskService.createTaskQuery()
				.processDefinitionKey(request.getProcdefKey())
				.taskAssignee(request.getAssignee());
		// 总记录数
		long total = taskQuery.count();
		// 分页数据
		List<Task> taskList = taskQuery.listPage(request.getFirstRow(), request.getPageSize());
		Leaf leaf = new Leaf(taskList, total, request);
		return ResponseResult.success(leaf);
	}

	@Override
	public ResponseResult doCompletTask(CompletTaskRequest request) {
		Task task = taskService.createTaskQuery()
				.taskId(request.getTaskId())
				.taskAssignee(request.getAssignee()).singleResult();
		// 任务不存在
		if (null == task) {
			return ResponseResult.fail("task is not exist");
		}
		// 处理任务
		taskService.complete(task.getId());
		return ResponseResult.success();
	}

}
