package com.fallframework.platform.starter.activiti.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.fallframework.platform.starter.activiti.model.AssignTaskDto;
import com.fallframework.platform.starter.activiti.model.CompleteTaskDto;
import com.fallframework.platform.starter.activiti.model.PendingTaskDto;
import com.fallframework.platform.starter.activiti.model.RejectTaskDto;
import com.fallframework.platform.starter.activiti.model.TaskDetailOutVo;
import com.fallframework.platform.starter.activiti.model.TaskQueryDto;
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
	public ResponseResult<Leaf<Task>> getTaskList(TaskQueryDto dto) {
		TaskQuery taskQuery = taskService.createTaskQuery();
		if (StringUtils.isNotEmpty(dto.getTaskId())) {
			taskQuery.taskId(dto.getTaskId());
		}
		if (StringUtils.isNotEmpty(dto.getName())) {
			taskQuery.taskName(dto.getName());
		}
		if (StringUtils.isNotEmpty(dto.getNameLike())) {
			taskQuery.taskNameLike(dto.getNameLike());
		}
		if (StringUtils.isNotEmpty(dto.getNameLikeIgnoreCase())) {
			taskQuery.taskNameLikeIgnoreCase(dto.getNameLikeIgnoreCase());
		}
		if (CollectionUtil.isNotEmpty(dto.getNameList())) {
			taskQuery.taskNameIn(dto.getNameList());
		}
		if (CollectionUtil.isNotEmpty(dto.getNameListIgnoreCase())) {
			taskQuery.taskNameInIgnoreCase(dto.getNameListIgnoreCase());
		}
		if (StringUtils.isNotEmpty(dto.getDescription())) {
			taskQuery.taskDescription(dto.getDescription());
		}
		if (StringUtils.isNotEmpty(dto.getDescriptionLike())) {
			taskQuery.taskDescriptionLike(dto.getDescriptionLike());
		}
		if (StringUtils.isNotEmpty(dto.getDescriptionLikeIgnoreCase())) {
			taskQuery.taskDescriptionLikeIgnoreCase(dto.getDescriptionLikeIgnoreCase());
		}
		if (null != dto.getPriority()) {
			taskQuery.taskPriority(dto.getPriority());
		}
		if (null != dto.getMinPriority()) {
			taskQuery.taskMinPriority(dto.getMinPriority());
		}
		if (null != dto.getMaxPriority()) {
			taskQuery.taskMaxPriority(dto.getMaxPriority());
		}
		if (StringUtils.isNotEmpty(dto.getAssignee())) {
			taskQuery.taskAssignee(dto.getAssignee());
		}
		if (StringUtils.isNotEmpty(dto.getAssigneeLike())) {
			taskQuery.taskAssigneeLike(dto.getAssigneeLike());
		}
		if (StringUtils.isNotEmpty(dto.getAssigneeLikeIgnoreCase())) {
			taskQuery.taskAssigneeLikeIgnoreCase(dto.getAssigneeLikeIgnoreCase());
		}
		if (CollectionUtil.isNotEmpty(dto.getAssigneeIds())) {
			taskQuery.taskAssigneeIds(dto.getAssigneeIds());
		}
		if (StringUtils.isNotEmpty(dto.getInvolvedUser())) {
			taskQuery.taskInvolvedUser(dto.getInvolvedUser());
		}
		if (CollectionUtil.isNotEmpty(dto.getInvolvedGroups())) {
			taskQuery.taskInvolvedGroupsIn(dto.getInvolvedGroups());
		}
		if (StringUtils.isNotEmpty(dto.getOwner())) {
			taskQuery.taskOwner(dto.getOwner());
		}
		if (StringUtils.isNotEmpty(dto.getOwnerLike())) {
			taskQuery.taskOwnerLike(dto.getOwnerLike());
		}
		if (StringUtils.isNotEmpty(dto.getOwnerLikeIgnoreCase())) {
			taskQuery.taskOwnerLikeIgnoreCase(dto.getOwnerLikeIgnoreCase());
		}
		if (dto.getUnassigned()) {
			taskQuery.taskUnassigned();
		}
		if (null != dto.getDelegationState()) {
			taskQuery.taskDelegationState(dto.getDelegationState());
		}
		if (StringUtils.isNotEmpty(dto.getCandidateUser())) {
			taskQuery.taskCandidateUser(dto.getCandidateUser());
		}
		if (StringUtils.isNotEmpty(dto.getCandidateGroup())) {
			taskQuery.taskCandidateGroup(dto.getCandidateGroup());
		}
		if (CollectionUtil.isNotEmpty(dto.getCandidateGroups())) {
			taskQuery.taskCandidateGroupIn(dto.getCandidateGroups());
		}
		if (StringUtils.isNotEmpty(dto.getTenantId())) {
			taskQuery.taskTenantId(dto.getTenantId());
		}
		if (StringUtils.isNotEmpty(dto.getTenantIdLike())) {
			taskQuery.taskTenantIdLike(dto.getTenantIdLike());
		}
		if (dto.getWithoutTenantId()) {
			taskQuery.taskWithoutTenantId();
		}
		if (StringUtils.isNotEmpty(dto.getProcessInstanceId())) {
			taskQuery.processInstanceId(dto.getProcessInstanceId());
		}
		if (CollectionUtil.isNotEmpty(dto.getProcessInstanceIds())) {
			taskQuery.processInstanceIdIn(dto.getProcessInstanceIds());
		}
		if (StringUtils.isNotEmpty(dto.getExecutionId())) {
			taskQuery.executionId(dto.getExecutionId());
		}
		if (null != dto.getCreateTime()) {
			taskQuery.taskCreatedOn(dto.getCreateTime());
		}
		if (null != dto.getCreateTimeBefore()) {
			taskQuery.taskCreatedBefore(dto.getCreateTimeBefore());
		}
		if (null != dto.getCreateTimeAfter()) {
			taskQuery.taskCreatedAfter(dto.getCreateTimeAfter());
		}
		if (StringUtils.isNotEmpty(dto.getCategory())) {
			taskQuery.taskCategory(dto.getCategory());
		}
		if (StringUtils.isNotEmpty(dto.getKey())) {
			taskQuery.taskDefinitionKey(dto.getKey());
		}
		if (StringUtils.isNotEmpty(dto.getKeyLike())) {
			taskQuery.taskDefinitionKeyLike(dto.getKeyLike());
		}
		if (StringUtils.isNotEmpty(dto.getProcessDefinitionKey())) {
			taskQuery.processDefinitionKey(dto.getProcessDefinitionKey());
		}
		if (StringUtils.isNotEmpty(dto.getProcessDefinitionKeyLike())) {
			taskQuery.processDefinitionKeyLike(dto.getProcessDefinitionKeyLike());
		}
		if (StringUtils.isNotEmpty(dto.getProcessDefinitionKeyLikeIgnoreCase())) {
			taskQuery.processDefinitionKeyLikeIgnoreCase(dto.getProcessDefinitionKeyLikeIgnoreCase());
		}
		if (CollectionUtil.isNotEmpty(dto.getProcessDefinitionKeys())) {
			taskQuery.processDefinitionKeyIn(dto.getProcessDefinitionKeys());
		}
		if (StringUtils.isNotEmpty(dto.getProcessDefinitionId())) {
			taskQuery.processDefinitionId(dto.getProcessDefinitionId());
		}
		if (StringUtils.isNotEmpty(dto.getProcessDefinitionName())) {
			taskQuery.processDefinitionName(dto.getProcessDefinitionName());
		}
		if (StringUtils.isNotEmpty(dto.getProcessDefinitionNameLike())) {
			taskQuery.processDefinitionNameLike(dto.getProcessDefinitionNameLike());
		}
		if (CollectionUtil.isNotEmpty(dto.getProcessCategoryInList())) {
			taskQuery.processCategoryIn(dto.getProcessCategoryInList());
		}
		if (CollectionUtil.isNotEmpty(dto.getProcessCategoryNotInList())) {
			taskQuery.processCategoryNotIn(dto.getProcessCategoryNotInList());
		}
		if (StringUtils.isNotEmpty(dto.getDeploymentId())) {
			taskQuery.deploymentId(dto.getDeploymentId());
		}
		if (CollectionUtil.isNotEmpty(dto.getDeploymentIds())) {
			taskQuery.deploymentIdIn(dto.getDeploymentIds());
		}
		if (StringUtils.isNotEmpty(dto.getProcessInstanceBusinessKey())) {
			taskQuery.processInstanceBusinessKey(dto.getProcessInstanceBusinessKey());
		}
		if (StringUtils.isNotEmpty(dto.getProcessInstanceBusinessKeyLike())) {
			taskQuery.processInstanceBusinessKeyLike(dto.getProcessInstanceBusinessKeyLike());
		}
		if (StringUtils.isNotEmpty(dto.getProcessInstanceBusinessKeyLikeIgnoreCase())) {
			taskQuery.processInstanceBusinessKeyLikeIgnoreCase(dto.getProcessInstanceBusinessKeyLikeIgnoreCase());
		}
		if (null != dto.getDueDate()) {
			taskQuery.taskDueDate(dto.getDueDate());
		}
		if (null != dto.getDueBefore()) {
			taskQuery.taskDueBefore(dto.getDueBefore());
		}
		if (null != dto.getDueAfter()) {
			taskQuery.taskDueAfter(dto.getDueAfter());
		}
		if (dto.getWithoutDueDate()) {
			taskQuery.withoutTaskDueDate();
		}
		if ("active".equals(dto.getSuspensionState().getStateCode())) {
			taskQuery.active();
		}
		if ("suspended".equals(dto.getSuspensionState().getStateCode())) {
			taskQuery.suspended();
		}
		if (dto.getExcludeSubtasks()) {
			taskQuery.excludeSubtasks();
		}
		if (dto.getIncludeTaskLocalVariables()) {
			taskQuery.includeTaskLocalVariables();
		}
		if (dto.getIncludeProcessVariables()) {
			taskQuery.includeProcessVariables();
		}
		if (null != dto.getTaskVariablesLimit()) {
			taskQuery.limitTaskVariables(dto.getTaskVariablesLimit());
		}
		if (dto.getBothCandidateAndAssigned() && StringUtils.isNotEmpty(dto.getUserIdForCandidateAndAssignee())) {
			taskQuery.taskCandidateOrAssigned(dto.getUserIdForCandidateAndAssignee());
		}
		if (StringUtils.isNotEmpty(dto.getLocale())) {
			taskQuery.locale(dto.getLocale());
		}
		if (dto.getWithLocalizationFallback()) {
			taskQuery.withLocalizationFallback();
		}
		// 总记录数
		long total = taskQuery.count();
		// 分页数据
		List<Task> taskList = taskQuery.orderByTaskCreateTime().desc().listPage(dto.firstRowNum(), dto.getPageSize());
		Leaf<Task> leaf = new Leaf(taskList, total, dto);
		return ResponseResult.success(leaf);
	}

	@Override
	public ResponseResult<Leaf<Task>> getPendingTaskList(PendingTaskDto dto) {
		// 查询条件
		TaskQuery taskQuery = taskService.createTaskQuery();
		if (StringUtils.isNotEmpty(dto.getProcdefKey())) {
			taskQuery.processDefinitionKey(dto.getProcdefKey());
		}
		if (StringUtils.isNotEmpty(dto.getAssignee())) {
			taskQuery.taskAssignee(dto.getAssignee());
		}
		if (StringUtils.isNotEmpty(dto.getCandidateUser())) {
			taskQuery.taskCandidateUser(dto.getCandidateUser());
		}
		if (StringUtils.isNotEmpty(dto.getCandidateGroup())) {
			taskQuery.taskCandidateGroup(dto.getCandidateGroup());
		}
		// 总记录数
		long total = taskQuery.count();
		// 分页数据
		List<Task> taskList = taskQuery.orderByTaskCreateTime().desc().listPage(dto.firstRowNum(), dto.getPageSize());
		Leaf<Task> leaf = new Leaf(taskList, total, dto);
		return ResponseResult.success(leaf);
	}

	@Override
	public ResponseResult completTask(CompleteTaskDto dto) {
		Task task = taskService.createTaskQuery()
				.taskId(dto.getTaskId())
				.taskAssignee(dto.getAssignee()).singleResult();
		// 任务不存在
		if (null == task) {
			return ResponseResult.fail("task is not exist");
		}
		// 处理任务
		taskService.complete(task.getId());
		return ResponseResult.success();
	}

	@Override
	public ResponseResult rejectTask(RejectTaskDto dto) {
		// 当前任务
		Task currentTask = taskService.createTaskQuery().taskId(dto.getTaskId()).singleResult();
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
		if (dto.getReturnStartFlag()) {
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
	public ResponseResult assignTask(AssignTaskDto dto) {
		taskService.claim(dto.getTaskId(), dto.getAssignee());
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
		TaskDetailOutVo response = new TaskDetailOutVo();
		response.setTask(task);
		response.setFormProperties(formProperties);
		return ResponseResult.success(response);
	}

}
