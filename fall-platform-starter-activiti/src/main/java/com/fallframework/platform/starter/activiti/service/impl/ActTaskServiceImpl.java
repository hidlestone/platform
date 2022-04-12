package com.fallframework.platform.starter.activiti.service.impl;

import com.fallframework.platform.starter.activiti.model.AssignTaskRequest;
import com.fallframework.platform.starter.activiti.model.CompletTaskRequest;
import com.fallframework.platform.starter.activiti.model.PendingTaskRequest;
import com.fallframework.platform.starter.activiti.model.RejectTaskRequest;
import com.fallframework.platform.starter.activiti.service.ActTaskService;
import com.fallframework.platform.starter.activiti.service.cmd.DeleteTaskCmd;
import com.fallframework.platform.starter.activiti.service.cmd.SetFLowNodeAndGoCmd;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
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
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private ManagementService managementService;

	@Override
	public ResponseResult<Leaf<Task>> getPendingTaskList(PendingTaskRequest request) {
		// 查询条件
		TaskQuery taskQuery = taskService.createTaskQuery();
		if (StringUtils.isNotEmpty(request.getProcdefKey())) {
			taskQuery.processDefinitionKey(request.getProcdefKey());
		}
		if (StringUtils.isNotEmpty(request.getAssignee())) {
			taskQuery.taskAssignee(request.getAssignee());
		}
		if (StringUtils.isNotEmpty(request.getCandidateUser())) {
			taskQuery.taskCandidateUser(request.getCandidateUser());
		}
		if (StringUtils.isNotEmpty(request.getCandidateGroup())) {
			taskQuery.taskCandidateGroup(request.getCandidateGroup());
		}
		// 总记录数
		long total = taskQuery.count();
		// 分页数据
		List<Task> taskList = taskQuery.listPage(request.getFirstRow(), request.getPageSize());
		Leaf leaf = new Leaf(taskList, total, request);
		return ResponseResult.success(leaf);
	}

	@Override
	public ResponseResult completTask(CompletTaskRequest request) {
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

	@Override
	public ResponseResult rejectTask(RejectTaskRequest request) {
		// 当前任务
		Task currentTask = taskService.createTaskQuery().taskId(request.getTaskId()).singleResult();
		// 获取流程定义
		BpmnModel bpmnModel = repositoryService.getBpmnModel(currentTask.getProcessDefinitionId());
		// 历史
		List<HistoricActivityInstance> historicList = historyService.createHistoricActivityInstanceQuery()
				.processInstanceId(currentTask.getProcessInstanceId()).activityType("userTask") // TODO
				.finished().orderByHistoricActivityInstanceEndTime().asc().list();
		if (null == historicList || historicList.size() == 0) {
			throw new ActivitiException("操作历史流程不存在");
		}
		// 获取目标节点定义
		FlowNode targetNode = null;
		// 驳回到发起点
		if (request.getReturnStart()) {
			targetNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicList.get(0).getActivityId());
		} else {
			// 驳回到上一个节点
			FlowNode currNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(currentTask.getTaskDefinitionKey());
			// 倒序审核任务列表，最后一个不与当前节点相同的节点设置为目标节点
			for (int i = 0; i < historicList.size(); i++) {
				FlowNode lastNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicList.get(i).getActivityId());
				if (historicList.size() > 0 && currNode.getId().equals(lastNode.getId())) {
					targetNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicList.get(i - 1).getActivityId());
					break;
				}
			}
			if (targetNode == null && historicList.size() > 0) {
				targetNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicList.get(historicList.size() - 1).getActivityId());
			}
			if (null == targetNode) {
				throw new ActivitiException("开始节点不存在");
			}
			// TODO
			// 删除当前运行任务
			String executionEntityId = managementService.executeCommand(new DeleteTaskCmd(currentTask.getId()));
			// 流程执行到来源节点
			managementService.executeCommand(new SetFLowNodeAndGoCmd(targetNode, executionEntityId));
		}
		return ResponseResult.success();
	}

	@Override
	public ResponseResult assignTask(AssignTaskRequest request) {
		taskService.claim(request.getTaskId(), request.getAssignee());
		return ResponseResult.success();
	}

}
