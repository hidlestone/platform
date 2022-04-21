package com.fallframework.platform.starter.activiti.service.impl;

import com.fallframework.platform.starter.activiti.model.HistoricActivityInstanceQueryRequest;
import com.fallframework.platform.starter.activiti.model.HistoricActivityResponse;
import com.fallframework.platform.starter.activiti.model.HistoricTaskInstanceQueryRequest;
import com.fallframework.platform.starter.activiti.service.ActHistoryService;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuangpf
 */
@Service
public class ActHistoryServiceImpl implements ActHistoryService {

	@Autowired
	private HistoryService historyService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private ProcessEngineConfiguration processEngineConfiguration;

	@Override
	public ResponseResult<Leaf<HistoricActivityResponse>> getHistoricActivityList(HistoricActivityInstanceQueryRequest request) {
		HistoricActivityInstanceQuery historicActivityInstanceQuery = historyService.createHistoricActivityInstanceQuery();
		if (StringUtils.isNotEmpty(request.getActivityInstanceId())) {
			historicActivityInstanceQuery.activityInstanceId(request.getActivityInstanceId());
		}
		if (StringUtils.isNotEmpty(request.getProcessInstanceId())) {
			historicActivityInstanceQuery.processInstanceId(request.getProcessInstanceId());
		}
		if (StringUtils.isNotEmpty(request.getExecutionId())) {
			historicActivityInstanceQuery.executionId(request.getExecutionId());
		}
		if (StringUtils.isNotEmpty(request.getProcessDefinitionId())) {
			historicActivityInstanceQuery.processDefinitionId(request.getProcessDefinitionId());
		}
		if (StringUtils.isNotEmpty(request.getActivityId())) {
			historicActivityInstanceQuery.activityId(request.getActivityId());
		}
		if (StringUtils.isNotEmpty(request.getActivityName())) {
			historicActivityInstanceQuery.activityName(request.getActivityName());
		}
		if (StringUtils.isNotEmpty(request.getActivityType())) {
			historicActivityInstanceQuery.activityType(request.getActivityType());
		}
		if (StringUtils.isNotEmpty(request.getAssignee())) {
			historicActivityInstanceQuery.taskAssignee(request.getAssignee());
		}
		if (StringUtils.isNotEmpty(request.getTenantId())) {
			historicActivityInstanceQuery.activityTenantId(request.getTenantId());
		}
		if (StringUtils.isNotEmpty(request.getTenantIdLike())) {
			historicActivityInstanceQuery.activityTenantIdLike(request.getTenantIdLike());
		}
		if (request.getWithoutTenantId()) {
			historicActivityInstanceQuery.activityWithoutTenantId();
		}
		if (request.getFinished()) {
			historicActivityInstanceQuery.finished();
		}
		if (request.getUnfinished()) {
			historicActivityInstanceQuery.unfinished();
		}
		if (StringUtils.isNotEmpty(request.getDeleteReason())) {
			historicActivityInstanceQuery.deleteReason(request.getDeleteReason());
		}
		if (StringUtils.isNotEmpty(request.getDeleteReasonLike())) {
			historicActivityInstanceQuery.deleteReasonLike(request.getDeleteReasonLike());
		}
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
	public ResponseResult<Leaf<HistoricTaskInstance>> getHistoricTaskInstanceList(HistoricTaskInstanceQueryRequest request) {
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

	@Override
	public ResponseResult<String> generateProcessDiagram(String processInstanceId) throws IOException {
		// 获取历史流程实例
		HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		// 获取流程图
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());

		ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
		ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());
		// 获取流程中已经执行的节点，按照执行先后顺序排序
		List<HistoricActivityInstance> highLightedActivitList =
				historyService.createHistoricActivityInstanceQuery()
						.processInstanceId(processInstanceId)
						.orderByHistoricActivityInstanceId().asc()
						.list();
		// 高亮环节id集合
		List<String> highLightedActivitis = new ArrayList<String>();
		// 高亮线路id集合
		List<String> highLightedFlows = getHighLightedFlows(bpmnModel, highLightedActivitList);

		for (HistoricActivityInstance tempActivity : highLightedActivitList) {
			String activityId = tempActivity.getActivityId();
			highLightedActivitis.add(activityId);
		}
		// 配置字体
		InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis, highLightedFlows, "宋体", "微软雅黑", "黑体", null, 2.0);
		BufferedImage bi = ImageIO.read(imageStream);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bi, "png", bos);
		//转换成字节
		byte[] bytes = bos.toByteArray();
		BASE64Encoder encoder = new BASE64Encoder();
		//转换成base64串
		String png_base64 = encoder.encodeBuffer(bytes);
		//删除 \r\n
		png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");
		bos.close();
		imageStream.close();
		return ResponseResult.success(png_base64);
	}

	/**
	 * 获取高亮的线
	 */
	public List<String> getHighLightedFlows(
			BpmnModel bpmnModel,
			List<HistoricActivityInstance> historicActivityInstances) {
		// 高亮流程已发生流转的线id集合
		List<String> highLightedFlowIds = new ArrayList<>();
		// 全部活动节点
		List<FlowNode> historicActivityNodes = new ArrayList<>();
		// 已完成的历史活动节点
		List<HistoricActivityInstance> finishedActivityInstances = new ArrayList<>();

		for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
			FlowNode flowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicActivityInstance.getActivityId(), true);
			historicActivityNodes.add(flowNode);
			if (historicActivityInstance.getEndTime() != null) {
				finishedActivityInstances.add(historicActivityInstance);
			}
		}

		FlowNode currentFlowNode = null;
		FlowNode targetFlowNode = null;
		// 遍历已完成的活动实例，从每个实例的outgoingFlows中找到已执行的
		for (HistoricActivityInstance currentActivityInstance : finishedActivityInstances) {
			// 获得当前活动对应的节点信息及outgoingFlows信息
			currentFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(currentActivityInstance.getActivityId(), true);
			List<SequenceFlow> sequenceFlows = currentFlowNode.getOutgoingFlows();
			/**
			 * 遍历outgoingFlows并找到已已流转的 满足如下条件认为已已流转： 
			 * 1.当前节点是并行网关或兼容网关，则通过outgoingFlows能够在历史活动中找到的全部节点均为已流转 
			 * 2.当前节点是以上两种类型之外的，通过outgoingFlows查找到的时间最早的流转节点视为有效流转
			 */
			if ("parallelGateway".equals(currentActivityInstance.getActivityType()) || "inclusiveGateway".equals(currentActivityInstance.getActivityType())) {
				// 遍历历史活动节点，找到匹配流程目标节点的
				for (SequenceFlow sequenceFlow : sequenceFlows) {
					targetFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(sequenceFlow.getTargetRef(), true);
					if (historicActivityNodes.contains(targetFlowNode)) {
						highLightedFlowIds.add(targetFlowNode.getId());
					}
				}
			} else {
				List<Map<String, Object>> tempMapList = new ArrayList<>();
				for (SequenceFlow sequenceFlow : sequenceFlows) {
					for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
						if (historicActivityInstance.getActivityId().equals(sequenceFlow.getTargetRef())) {
							Map<String, Object> map = new HashMap<>();
							map.put("highLightedFlowId", sequenceFlow.getId());
							map.put("highLightedFlowStartTime", historicActivityInstance.getStartTime().getTime());
							tempMapList.add(map);
						}
					}
				}

				if (!CollectionUtils.isEmpty(tempMapList)) {
					// 遍历匹配的集合，取得开始时间最早的一个
					long earliestStamp = 0L;
					String highLightedFlowId = null;
					for (Map<String, Object> map : tempMapList) {
						long highLightedFlowStartTime = Long.valueOf(map.get("highLightedFlowStartTime").toString());
						if (earliestStamp == 0 || earliestStamp >= highLightedFlowStartTime) {
							highLightedFlowId = map.get("highLightedFlowId").toString();
							earliestStamp = highLightedFlowStartTime;
						}
					}
					highLightedFlowIds.add(highLightedFlowId);
				}
			}
		}
		return highLightedFlowIds;
	}

}
