package com.fallframework.platform.starter.activiti.service.impl;

import com.fallframework.platform.starter.activiti.model.HistoricActivityRequest;
import com.fallframework.platform.starter.activiti.model.HistoricActivityResponse;
import com.fallframework.platform.starter.activiti.model.HistoricTaskInstanceRequest;
import com.fallframework.platform.starter.activiti.service.ActHistoryService;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuangpf
 */
@Service
public class ActHistoryServiceImpl implements ActHistoryService {

	@Autowired
	private HistoryService historyService;

	@Override
	public ResponseResult<Leaf<HistoricActivityResponse>> getHistoricActivityList(HistoricActivityRequest request) {
		HistoricActivityInstanceQuery historicActivityInstanceQuery = historyService.createHistoricActivityInstanceQuery().processInstanceId(request.getProcessInstanceId());
		long toatal = historicActivityInstanceQuery.count();
		// 历史活动实例
		List<HistoricActivityInstance> historicActivityInstanceList =
				historicActivityInstanceQuery.listPage(request.getFirstRow(), request.getPageSize());
		List<HistoricActivityResponse> resultList = new ArrayList<>();
		// 获取明细信息
		for (HistoricActivityInstance activity : historicActivityInstanceList) {
			HistoricActivityResponse response = new HistoricActivityResponse();
			response.setHistoricActivityInstance(activity);
			List<HistoricDetail> historicDetailList = historyService.createHistoricDetailQuery().activityInstanceId(activity.getProcessInstanceId()).list();
			response.setHistoricDetailList(historicDetailList);
			resultList.add(response);
		}
		Leaf<HistoricActivityResponse> leaf = new Leaf<>(resultList, toatal, request);
		return ResponseResult.success(leaf);
	}

	@Override
	public ResponseResult<Leaf<HistoricTaskInstance>> getHistoricTaskInstanceList(HistoricTaskInstanceRequest request) {
		// 查询条件
		HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery();
		if (StringUtils.isNotEmpty(request.getProcessDefinitionId())) {
			historicTaskInstanceQuery.processDefinitionId(request.getProcessDefinitionId());
		}
		if (StringUtils.isNotEmpty(request.getProcessDefinitionKey())) {
			historicTaskInstanceQuery.processDefinitionKey(request.getProcessDefinitionKey());
		}
		if (StringUtils.isNotEmpty(request.getProcessDefinitionKeyLike())) {
			historicTaskInstanceQuery.processDefinitionKeyLike(request.getProcessDefinitionKeyLike());
		}
		if (StringUtils.isNotEmpty(request.getProcessDefinitionKeyLikeIgnoreCase())) {
			historicTaskInstanceQuery.processDefinitionKeyLikeIgnoreCase(request.getProcessDefinitionKeyLikeIgnoreCase());
		}
		if (CollectionUtils.isEmpty(request.getProcessDefinitionKeys())) {
			historicTaskInstanceQuery.processDefinitionKeyIn(request.getProcessDefinitionKeys());
		}
		if (StringUtils.isNotEmpty(request.getProcessDefinitionName())) {
			historicTaskInstanceQuery.processDefinitionName(request.getProcessDefinitionName());
		}
		if (StringUtils.isNotEmpty(request.getProcessDefinitionNameLike())) {
			historicTaskInstanceQuery.processDefinitionNameLike(request.getProcessDefinitionNameLike());
		}
		if (CollectionUtils.isEmpty(request.getProcessCategoryInList())) {
			historicTaskInstanceQuery.processCategoryIn(request.getProcessCategoryInList());
		}
		if (CollectionUtils.isEmpty(request.getProcessCategoryNotInList())) {
			historicTaskInstanceQuery.processCategoryNotIn(request.getProcessCategoryNotInList());
		}
		if (StringUtils.isNotEmpty(request.getDeploymentId())) {
			historicTaskInstanceQuery.deploymentId(request.getDeploymentId());
		}
		if (CollectionUtils.isEmpty(request.getDeploymentIds())) {
			historicTaskInstanceQuery.deploymentIdIn(request.getDeploymentIds());
		}
		if (StringUtils.isNotEmpty(request.getProcessInstanceId())) {
			historicTaskInstanceQuery.processInstanceId(request.getProcessInstanceId());
		}
		if (CollectionUtils.isEmpty(request.getProcessInstanceIds())) {
			historicTaskInstanceQuery.processInstanceIdIn(request.getProcessInstanceIds());
		}
		if (StringUtils.isNotEmpty(request.getProcessInstanceBusinessKey())) {
			historicTaskInstanceQuery.processInstanceBusinessKey(request.getProcessInstanceBusinessKey());
		}
		if (StringUtils.isNotEmpty(request.getProcessInstanceBusinessKeyLike())) {
			historicTaskInstanceQuery.processInstanceBusinessKeyLike(request.getProcessInstanceBusinessKeyLike());
		}
		if (StringUtils.isNotEmpty(request.getProcessInstanceBusinessKeyLikeIgnoreCase())) {
			historicTaskInstanceQuery.processInstanceBusinessKeyLikeIgnoreCase(request.getProcessInstanceBusinessKeyLikeIgnoreCase());
		}
		if (StringUtils.isNotEmpty(request.getExecutionId())) {
			historicTaskInstanceQuery.executionId(request.getExecutionId());
		}
		if (StringUtils.isNotEmpty(request.getTaskId())) {
			historicTaskInstanceQuery.taskId(request.getTaskId());
		}
		if (StringUtils.isNotEmpty(request.getTaskName())) {
			historicTaskInstanceQuery.taskName(request.getTaskName());
		}
		if (StringUtils.isNotEmpty(request.getTaskNameLike())) {
			historicTaskInstanceQuery.taskNameLike(request.getTaskNameLike());
		}
		if (StringUtils.isNotEmpty(request.getTaskNameLikeIgnoreCase())) {
			historicTaskInstanceQuery.taskNameLikeIgnoreCase(request.getTaskNameLikeIgnoreCase());
		}
		if (CollectionUtils.isEmpty(request.getTaskNameList())) {
			historicTaskInstanceQuery.taskNameIn(request.getTaskNameList());
		}
		if (CollectionUtils.isEmpty(request.getTaskNameListIgnoreCase())) {
			historicTaskInstanceQuery.taskNameInIgnoreCase(request.getTaskNameListIgnoreCase());
		}
		if (StringUtils.isNotEmpty(request.getTaskParentTaskId())) {
			historicTaskInstanceQuery.taskParentTaskId(request.getTaskParentTaskId());
		}
		if (StringUtils.isNotEmpty(request.getTaskDescription())) {
			historicTaskInstanceQuery.taskDescription(request.getTaskDescription());
		}
		if (StringUtils.isNotEmpty(request.getTaskDescriptionLike())) {
			historicTaskInstanceQuery.taskDescriptionLike(request.getTaskDescriptionLike());
		}
		if (StringUtils.isNotEmpty(request.getTaskDescriptionLikeIgnoreCase())) {
			historicTaskInstanceQuery.taskDescriptionLikeIgnoreCase(request.getTaskDescriptionLikeIgnoreCase());
		}
		if (StringUtils.isNotEmpty(request.getTaskDeleteReason())) {
			historicTaskInstanceQuery.taskDeleteReason(request.getTaskDeleteReason());
		}
		if (StringUtils.isNotEmpty(request.getTaskDeleteReasonLike())) {
			historicTaskInstanceQuery.taskDeleteReasonLike(request.getTaskDeleteReasonLike());
		}
		if (StringUtils.isNotEmpty(request.getTaskOwner())) {
			historicTaskInstanceQuery.taskOwner(request.getTaskOwner());
		}
		if (StringUtils.isNotEmpty(request.getTaskOwnerLike())) {
			historicTaskInstanceQuery.taskOwnerLike(request.getTaskOwnerLike());
		}
		if (StringUtils.isNotEmpty(request.getTaskOwnerLikeIgnoreCase())) {
			historicTaskInstanceQuery.taskOwnerLikeIgnoreCase(request.getTaskOwnerLikeIgnoreCase());
		}
		if (StringUtils.isNotEmpty(request.getTaskAssignee())) {
			historicTaskInstanceQuery.taskAssignee(request.getTaskAssignee());
		}
		if (StringUtils.isNotEmpty(request.getTaskAssigneeLike())) {
			historicTaskInstanceQuery.taskAssigneeLike(request.getTaskAssigneeLike());
		}
		if (StringUtils.isNotEmpty(request.getTaskAssigneeLikeIgnoreCase())) {
			historicTaskInstanceQuery.taskAssigneeLikeIgnoreCase(request.getTaskAssigneeLikeIgnoreCase());
		}
		if (CollectionUtils.isEmpty(request.getTaskAssigneeIds())) {
			historicTaskInstanceQuery.taskAssigneeIds(request.getTaskAssigneeIds());
		}
		if (StringUtils.isNotEmpty(request.getTaskDefinitionKey())) {
			historicTaskInstanceQuery.taskDefinitionKey(request.getTaskDefinitionKey());
		}
		if (StringUtils.isNotEmpty(request.getTaskDefinitionKeyLike())) {
			historicTaskInstanceQuery.taskDefinitionKeyLike(request.getTaskDefinitionKeyLike());
		}
		if (StringUtils.isNotEmpty(request.getCandidateUser())) {
			historicTaskInstanceQuery.taskCandidateUser(request.getCandidateUser());
		}
		if (StringUtils.isNotEmpty(request.getCandidateGroup())) {
			historicTaskInstanceQuery.taskCandidateGroup(request.getCandidateGroup());
		}
		if (CollectionUtils.isEmpty(request.getCandidateGroups())) {
			historicTaskInstanceQuery.taskCandidateGroupIn(request.getCandidateGroups());
		}
		if (StringUtils.isNotEmpty(request.getInvolvedUser())) {
			historicTaskInstanceQuery.taskInvolvedUser(request.getInvolvedUser());
		}
		if (CollectionUtils.isEmpty(request.getInvolvedGroups())) {
			historicTaskInstanceQuery.taskInvolvedGroupsIn(request.getInvolvedGroups());
		}
		if (null != request.getTaskPriority()) {
			historicTaskInstanceQuery.taskPriority(request.getTaskPriority());
		}
		if (null != request.getTaskMinPriority()) {
			historicTaskInstanceQuery.taskMinPriority(request.getTaskMinPriority());
		}
		if (null != request.getTaskMaxPriority()) {
			historicTaskInstanceQuery.taskMaxPriority(request.getTaskMaxPriority());
		}
		if (request.getFinished()) {
			historicTaskInstanceQuery.finished();
		}
		if (request.getUnfinished()) {
			historicTaskInstanceQuery.unfinished();
		}
		if (request.getProcessFinished()) {
			historicTaskInstanceQuery.processFinished();
		}
		if (request.getProcessUnfinished()) {
			historicTaskInstanceQuery.processUnfinished();
		}
		if (null != request.getDueDate()) {
			historicTaskInstanceQuery.taskDueDate(request.getDueDate());
		}
		if (null != request.getDueAfter()) {
			historicTaskInstanceQuery.taskDueAfter(request.getDueAfter());
		}
		if (null != request.getDueBefore()) {
			historicTaskInstanceQuery.taskDueBefore(request.getDueBefore());
		}
		if (request.getWithoutDueDate()) {
			historicTaskInstanceQuery.withoutTaskDueDate();
		}
		if (null != request.getCreationDate()) {
			historicTaskInstanceQuery.taskCreatedOn(request.getCreationDate());
		}
		if (null != request.getCreationAfterDate()) {
			historicTaskInstanceQuery.taskCreatedAfter(request.getCreationAfterDate());
		}
		if (null != request.getCreationBeforeDate()) {
			historicTaskInstanceQuery.taskCreatedBefore(request.getCreationBeforeDate());
		}
		if (null != request.getCompletedDate()) {
			historicTaskInstanceQuery.taskCompletedOn(request.getCompletedDate());
		}
		if (null != request.getCompletedAfterDate()) {
			historicTaskInstanceQuery.taskCompletedAfter(request.getCompletedAfterDate());
		}
		if (null != request.getCompletedBeforeDate()) {
			historicTaskInstanceQuery.taskCompletedBefore(request.getCompletedBeforeDate());
		}
		if (StringUtils.isNotEmpty(request.getCategory())) {
			historicTaskInstanceQuery.taskCategory(request.getCategory());
		}
		if (StringUtils.isNotEmpty(request.getTenantId())) {
			historicTaskInstanceQuery.taskTenantId(request.getTenantId());
		}
		if (StringUtils.isNotEmpty(request.getTenantIdLike())) {
			historicTaskInstanceQuery.taskTenantIdLike(request.getTenantIdLike());
		}
		if (request.getWithoutTenantId()) {
			historicTaskInstanceQuery.taskWithoutTenantId();
		}
		if (StringUtils.isNotEmpty(request.getLocale())) {
			historicTaskInstanceQuery.locale(request.getLocale());
		}
		if (request.getWithLocalizationFallback()) {
			historicTaskInstanceQuery.withLocalizationFallback();
		}
		if (request.getIncludeTaskLocalVariables()) {
			historicTaskInstanceQuery.includeTaskLocalVariables();
		}
		if (request.getIncludeProcessVariables()) {
			historicTaskInstanceQuery.includeProcessVariables();
		}
		if (null != request.getTaskVariablesLimit()) {
			historicTaskInstanceQuery.limitTaskVariables(request.getTaskVariablesLimit());
		}
		// 总记录数
		long total = historicTaskInstanceQuery.count();
		List<HistoricTaskInstance> historicTaskInstanceList =
				historicTaskInstanceQuery.orderByHistoricTaskInstanceStartTime().desc()
						.listPage(request.getFirstRow(), request.getPageSize());
		Leaf<HistoricTaskInstance> leaf = new Leaf(historicTaskInstanceList, total, request);
		return ResponseResult.success(leaf);
	}
	
}
