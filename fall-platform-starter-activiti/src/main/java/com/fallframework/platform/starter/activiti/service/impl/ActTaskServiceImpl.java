package com.fallframework.platform.starter.activiti.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.fallframework.platform.starter.activiti.model.AssignTaskRequest;
import com.fallframework.platform.starter.activiti.model.CompleteTaskRequest;
import com.fallframework.platform.starter.activiti.model.PendingTaskRequest;
import com.fallframework.platform.starter.activiti.model.RejectTaskRequest;
import com.fallframework.platform.starter.activiti.model.TaskDetailResponse;
import com.fallframework.platform.starter.activiti.model.TaskQueryRequest;
import com.fallframework.platform.starter.activiti.service.ActTaskService;
import com.fallframework.platform.starter.activiti.service.cmd.DeleteTaskCmd;
import com.fallframework.platform.starter.activiti.service.cmd.SetFLowNodeAndGoCmd;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
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
	private FormService formService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private ManagementService managementService;

	@Override
	public ResponseResult<Leaf<Task>> getTaskList(TaskQueryRequest request) {
		TaskQuery taskQuery = taskService.createTaskQuery();
		if (StringUtils.isNotEmpty(request.getTaskId())) {
			taskQuery.taskId(request.getTaskId());
		}
		if (StringUtils.isNotEmpty(request.getName())) {
			taskQuery.taskName(request.getName());
		}
		if (StringUtils.isNotEmpty(request.getNameLike())) {
			taskQuery.taskNameLike(request.getNameLike());
		}
		if (StringUtils.isNotEmpty(request.getNameLikeIgnoreCase())) {
			taskQuery.taskNameLikeIgnoreCase(request.getNameLikeIgnoreCase());
		}
		if (CollectionUtil.isNotEmpty(request.getNameList())) {
			taskQuery.taskNameIn(request.getNameList());
		}
		if (CollectionUtil.isNotEmpty(request.getNameListIgnoreCase())) {
			taskQuery.taskNameInIgnoreCase(request.getNameListIgnoreCase());
		}
		if (StringUtils.isNotEmpty(request.getDescription())) {
			taskQuery.taskDescription(request.getDescription());
		}
		if (StringUtils.isNotEmpty(request.getDescriptionLike())) {
			taskQuery.taskDescriptionLike(request.getDescriptionLike());
		}
		if (StringUtils.isNotEmpty(request.getDescriptionLikeIgnoreCase())) {
			taskQuery.taskDescriptionLikeIgnoreCase(request.getDescriptionLikeIgnoreCase());
		}
		if (null != request.getPriority()) {
			taskQuery.taskPriority(request.getPriority());
		}
		if (null != request.getMinPriority()) {
			taskQuery.taskMinPriority(request.getMinPriority());
		}
		if (null != request.getMaxPriority()) {
			taskQuery.taskMaxPriority(request.getMaxPriority());
		}
		if (StringUtils.isNotEmpty(request.getAssignee())) {
			taskQuery.taskAssignee(request.getAssignee());
		}
		if (StringUtils.isNotEmpty(request.getAssigneeLike())) {
			taskQuery.taskAssigneeLike(request.getAssigneeLike());
		}
		if (StringUtils.isNotEmpty(request.getAssigneeLikeIgnoreCase())) {
			taskQuery.taskAssigneeLikeIgnoreCase(request.getAssigneeLikeIgnoreCase());
		}
		if (CollectionUtil.isNotEmpty(request.getAssigneeIds())) {
			taskQuery.taskAssigneeIds(request.getAssigneeIds());
		}
		if (StringUtils.isNotEmpty(request.getInvolvedUser())) {
			taskQuery.taskInvolvedUser(request.getInvolvedUser());
		}
		if (CollectionUtil.isNotEmpty(request.getInvolvedGroups())) {
			taskQuery.taskInvolvedGroupsIn(request.getInvolvedGroups());
		}
		if (StringUtils.isNotEmpty(request.getOwner())) {
			taskQuery.taskOwner(request.getOwner());
		}
		if (StringUtils.isNotEmpty(request.getOwnerLike())) {
			taskQuery.taskOwnerLike(request.getOwnerLike());
		}
		if (StringUtils.isNotEmpty(request.getOwnerLikeIgnoreCase())) {
			taskQuery.taskOwnerLikeIgnoreCase(request.getOwnerLikeIgnoreCase());
		}
		if (request.getUnassigned()) {
			taskQuery.taskUnassigned();
		}
		if (null != request.getDelegationState()) {
			taskQuery.taskDelegationState(request.getDelegationState());
		}
		if (StringUtils.isNotEmpty(request.getCandidateUser())) {
			taskQuery.taskCandidateUser(request.getCandidateUser());
		}
		if (StringUtils.isNotEmpty(request.getCandidateGroup())) {
			taskQuery.taskCandidateGroup(request.getCandidateGroup());
		}
		if (CollectionUtil.isNotEmpty(request.getCandidateGroups())) {
			taskQuery.taskCandidateGroupIn(request.getCandidateGroups());
		}
		if (StringUtils.isNotEmpty(request.getTenantId())) {
			taskQuery.taskTenantId(request.getTenantId());
		}
		if (StringUtils.isNotEmpty(request.getTenantIdLike())) {
			taskQuery.taskTenantIdLike(request.getTenantIdLike());
		}
		if (request.getWithoutTenantId()) {
			taskQuery.taskWithoutTenantId();
		}
		if (StringUtils.isNotEmpty(request.getProcessInstanceId())) {
			taskQuery.processInstanceId(request.getProcessInstanceId());
		}
		if (CollectionUtil.isNotEmpty(request.getProcessInstanceIds())) {
			taskQuery.processInstanceIdIn(request.getProcessInstanceIds());
		}
		if (StringUtils.isNotEmpty(request.getExecutionId())) {
			taskQuery.executionId(request.getExecutionId());
		}
		if (null != request.getCreateTime()) {
			taskQuery.taskCreatedOn(request.getCreateTime());
		}
		if (null != request.getCreateTimeBefore()) {
			taskQuery.taskCreatedBefore(request.getCreateTimeBefore());
		}
		if (null != request.getCreateTimeAfter()) {
			taskQuery.taskCreatedAfter(request.getCreateTimeAfter());
		}
		if (StringUtils.isNotEmpty(request.getCategory())) {
			taskQuery.taskCategory(request.getCategory());
		}
		if (StringUtils.isNotEmpty(request.getKey())) {
			taskQuery.taskDefinitionKey(request.getKey());
		}
		if (StringUtils.isNotEmpty(request.getKeyLike())) {
			taskQuery.taskDefinitionKeyLike(request.getKeyLike());
		}
		if (StringUtils.isNotEmpty(request.getProcessDefinitionKey())) {
			taskQuery.processDefinitionKey(request.getProcessDefinitionKey());
		}
		if (StringUtils.isNotEmpty(request.getProcessDefinitionKeyLike())) {
			taskQuery.processDefinitionKeyLike(request.getProcessDefinitionKeyLike());
		}
		if (StringUtils.isNotEmpty(request.getProcessDefinitionKeyLikeIgnoreCase())) {
			taskQuery.processDefinitionKeyLikeIgnoreCase(request.getProcessDefinitionKeyLikeIgnoreCase());
		}
		if (CollectionUtil.isNotEmpty(request.getProcessDefinitionKeys())) {
			taskQuery.processDefinitionKeyIn(request.getProcessDefinitionKeys());
		}
		if (StringUtils.isNotEmpty(request.getProcessDefinitionId())) {
			taskQuery.processDefinitionId(request.getProcessDefinitionId());
		}
		if (StringUtils.isNotEmpty(request.getProcessDefinitionName())) {
			taskQuery.processDefinitionName(request.getProcessDefinitionName());
		}
		if (StringUtils.isNotEmpty(request.getProcessDefinitionNameLike())) {
			taskQuery.processDefinitionNameLike(request.getProcessDefinitionNameLike());
		}
		if (CollectionUtil.isNotEmpty(request.getProcessCategoryInList())) {
			taskQuery.processCategoryIn(request.getProcessCategoryInList());
		}
		if (CollectionUtil.isNotEmpty(request.getProcessCategoryNotInList())) {
			taskQuery.processCategoryNotIn(request.getProcessCategoryNotInList());
		}
		if (StringUtils.isNotEmpty(request.getDeploymentId())) {
			taskQuery.deploymentId(request.getDeploymentId());
		}
		if (CollectionUtil.isNotEmpty(request.getDeploymentIds())) {
			taskQuery.deploymentIdIn(request.getDeploymentIds());
		}
		if (StringUtils.isNotEmpty(request.getProcessInstanceBusinessKey())) {
			taskQuery.processInstanceBusinessKey(request.getProcessInstanceBusinessKey());
		}
		if (StringUtils.isNotEmpty(request.getProcessInstanceBusinessKeyLike())) {
			taskQuery.processInstanceBusinessKeyLike(request.getProcessInstanceBusinessKeyLike());
		}
		if (StringUtils.isNotEmpty(request.getProcessInstanceBusinessKeyLikeIgnoreCase())) {
			taskQuery.processInstanceBusinessKeyLikeIgnoreCase(request.getProcessInstanceBusinessKeyLikeIgnoreCase());
		}
		if (null != request.getDueDate()) {
			taskQuery.taskDueDate(request.getDueDate());
		}
		if (null != request.getDueBefore()) {
			taskQuery.taskDueBefore(request.getDueBefore());
		}
		if (null != request.getDueAfter()) {
			taskQuery.taskDueAfter(request.getDueAfter());
		}
		if (request.getWithoutDueDate()) {
			taskQuery.withoutTaskDueDate();
		}
		if ("active".equals(request.getSuspensionState().getStateCode())) {
			taskQuery.active();
		}
		if ("suspended".equals(request.getSuspensionState().getStateCode())) {
			taskQuery.suspended();
		}
		if (request.getExcludeSubtasks()) {
			taskQuery.excludeSubtasks();
		}
		if (request.getIncludeTaskLocalVariables()) {
			taskQuery.includeTaskLocalVariables();
		}
		if (request.getIncludeProcessVariables()) {
			taskQuery.includeProcessVariables();
		}
		if (null != request.getTaskVariablesLimit()) {
			taskQuery.limitTaskVariables(request.getTaskVariablesLimit());
		}
		if (request.getBothCandidateAndAssigned() && StringUtils.isNotEmpty(request.getUserIdForCandidateAndAssignee())) {
			taskQuery.taskCandidateOrAssigned(request.getUserIdForCandidateAndAssignee());
		}
		if (StringUtils.isNotEmpty(request.getLocale())) {
			taskQuery.locale(request.getLocale());
		}
		if (request.getWithLocalizationFallback()) {
			taskQuery.withLocalizationFallback();
		}
		// 总记录数
		long total = taskQuery.count();
		// 分页数据
		List<Task> taskList = taskQuery.orderByTaskCreateTime().desc().listPage(request.getFirstRow(), request.getPageSize());
		Leaf<Task> leaf = new Leaf(taskList, total, request);
		return ResponseResult.success(leaf);
	}

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
		List<Task> taskList = taskQuery.orderByTaskCreateTime().desc().listPage(request.getFirstRow(), request.getPageSize());
		Leaf<Task> leaf = new Leaf(taskList, total, request);
		return ResponseResult.success(leaf);
	}

	@Override
	public ResponseResult completTask(CompleteTaskRequest request) {
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
		if (request.getReturnStartFlag()) {
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

	@Override
	public ResponseResult getTaskDetail(String taskId) {
		// 当前任务
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		TaskFormData taskFormData = formService.getTaskFormData(taskId);
		List<FormProperty> formProperties = taskFormData.getFormProperties();
		// 查询历史操作 TODO
		// 返回的数据
		TaskDetailResponse response = new TaskDetailResponse();
		response.setTask(task);
		response.setFormProperties(formProperties);
		return ResponseResult.success(response);
	}

}
