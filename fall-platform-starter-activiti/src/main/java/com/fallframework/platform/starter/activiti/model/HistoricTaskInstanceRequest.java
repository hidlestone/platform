package com.fallframework.platform.starter.activiti.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author zhuangpf
 */
@Getter
@Setter
@ApiModel("查询历史任务实例查询参数")
public class HistoricTaskInstanceRequest extends BasePageRequest {

	private static final long serialVersionUID = 896428922293078443L;

	@ApiModelProperty("流程定义ID")
	protected String processDefinitionId;

	@ApiModelProperty("流程定义key")
	protected String processDefinitionKey;

	@ApiModelProperty("流程定义key模糊")
	protected String processDefinitionKeyLike;

	@ApiModelProperty("流程定义key模糊忽略大小写")
	protected String processDefinitionKeyLikeIgnoreCase;

	@ApiModelProperty("流程定义keys")
	protected List<String> processDefinitionKeys;

	@ApiModelProperty("流程定义名称")
	protected String processDefinitionName;

	@ApiModelProperty("流程定义名称模糊")
	protected String processDefinitionNameLike;

	@ApiModelProperty("流程类别列表")
	protected List<String> processCategoryInList;

	@ApiModelProperty("流程类别排除列表")
	protected List<String> processCategoryNotInList;

	@ApiModelProperty("部署ID")
	protected String deploymentId;

	@ApiModelProperty("部署IDS")
	protected List<String> deploymentIds;

	@ApiModelProperty("流程实例ID")
	protected String processInstanceId;

	@ApiModelProperty("流程实例IDS")
	protected List<String> processInstanceIds;

	@ApiModelProperty("流程实例BusinessKey")
	protected String processInstanceBusinessKey;

	@ApiModelProperty("流程实例BusinessKey模糊")
	protected String processInstanceBusinessKeyLike;

	@ApiModelProperty("流程实例BusinessKey模糊忽略大小写")
	protected String processInstanceBusinessKeyLikeIgnoreCase;

	@ApiModelProperty("执行ID")
	protected String executionId;

	@ApiModelProperty("任务ID")
	protected String taskId;

	@ApiModelProperty("任务名称")
	protected String taskName;

	@ApiModelProperty("任务名称模糊")
	protected String taskNameLike;

	@ApiModelProperty("任务名称模糊忽略大小写")
	protected String taskNameLikeIgnoreCase;

	@ApiModelProperty("任务名称列表")
	protected List<String> taskNameList;

	@ApiModelProperty("任务名称列表忽略大小写")
	protected List<String> taskNameListIgnoreCase;

	@ApiModelProperty("父任务ID")
	protected String taskParentTaskId;

	@ApiModelProperty("任务描述")
	protected String taskDescription;

	@ApiModelProperty("任务描述模糊")
	protected String taskDescriptionLike;

	@ApiModelProperty("任务描述模糊忽略大小写")
	protected String taskDescriptionLikeIgnoreCase;

	@ApiModelProperty("任务删除原因")
	protected String taskDeleteReason;

	@ApiModelProperty("任务删除原因模糊")
	protected String taskDeleteReasonLike;

	@ApiModelProperty("任务拥有者")
	protected String taskOwner;

	@ApiModelProperty("任务拥有者模糊")
	protected String taskOwnerLike;

	@ApiModelProperty("任务拥有者模糊忽略大小写")
	protected String taskOwnerLikeIgnoreCase;

	@ApiModelProperty("任务分配到任务的人")
	protected String taskAssignee;

	@ApiModelProperty("任务分配到任务的人模糊")
	protected String taskAssigneeLike;

	@ApiModelProperty("任务分配到任务的人模糊忽略大小写")
	protected String taskAssigneeLikeIgnoreCase;

	@ApiModelProperty("任务分配到任务的人列表")
	protected List<String> taskAssigneeIds;

	@ApiModelProperty("任务定义key")
	protected String taskDefinitionKey;

	@ApiModelProperty("任务定义key模糊")
	protected String taskDefinitionKeyLike;

	@ApiModelProperty("候选用户")
	protected String candidateUser;

	@ApiModelProperty("候选组")
	protected String candidateGroup;

	@ApiModelProperty("候选组列表")
	private List<String> candidateGroups;

	@ApiModelProperty("参与用户")
	protected String involvedUser;

	@ApiModelProperty("参与组")
	protected List<String> involvedGroups;

	@ApiModelProperty("任务优先级")
	protected Integer taskPriority;

	@ApiModelProperty("任务最小优先级")
	protected Integer taskMinPriority;

	@ApiModelProperty("任务最大优先级")
	protected Integer taskMaxPriority;

	@ApiModelProperty("已结束")
	protected Boolean finished;

	@ApiModelProperty("未结束")
	protected Boolean unfinished;

	@ApiModelProperty("流程结束")
	protected Boolean processFinished;

	@ApiModelProperty("流程未结束")
	protected Boolean processUnfinished;

	@ApiModelProperty("截至日期")
	protected Date dueDate;

	@ApiModelProperty("在此日期后截止")
	protected Date dueAfter;

	@ApiModelProperty("在此日期前截止")
	protected Date dueBefore;

	@ApiModelProperty("流程定义")
	protected Boolean withoutDueDate;

	@ApiModelProperty("创建日期")
	protected Date creationDate;

	@ApiModelProperty("在此日期后创建")
	protected Date creationAfterDate;

	@ApiModelProperty("在此日期前创建")
	protected Date creationBeforeDate;

	@ApiModelProperty("完成日期")
	protected Date completedDate;

	@ApiModelProperty("在此日期后完成")
	protected Date completedAfterDate;

	@ApiModelProperty("在此日期前完成")
	protected Date completedBeforeDate;

	@ApiModelProperty("分类")
	protected String category;

	@ApiModelProperty("租户ID")
	protected String tenantId;

	@ApiModelProperty("租户ID模糊")
	protected String tenantIdLike;

	@ApiModelProperty("排除租户ID")
	protected Boolean withoutTenantId;

	@ApiModelProperty("流程定义")
	protected String locale;

	@ApiModelProperty("带本地化回退")
	protected Boolean withLocalizationFallback;

	@ApiModelProperty("包括任务本地化变量")
	protected Boolean includeTaskLocalVariables = false;

	@ApiModelProperty("包括流程变量")
	protected Boolean includeProcessVariables = false;

	@ApiModelProperty("任务变量限制数量")
	protected Integer taskVariablesLimit;

//	@ApiModelProperty("查询条件")
//	protected List<HistoricTaskInstanceQueryImpl> orQueryObjects = new ArrayList();

//	@ApiModelProperty("当前查询条件")
//	protected HistoricTaskInstanceQueryImpl currentOrQueryObject = null;

//	@ApiModelProperty("inOrStatement")
//	protected boolean inOrStatement = false;
	
}
