package com.fallframework.platform.starter.activiti.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.activiti.engine.impl.persistence.entity.SuspensionState;
import org.activiti.engine.task.DelegationState;

import java.util.Date;
import java.util.List;

/**
 * @author zhuangpf
 */
@Getter
@Setter
@ApiModel("任务查询请求参数")
public class TaskQueryDto extends BasePageRequest {

	private static final long serialVersionUID = 4495696399691684044L;

	@ApiModelProperty("任务ID")
	private String taskId;

	@ApiModelProperty("任务名称")
	private String name;

	@ApiModelProperty("任务名称模糊")
	private String nameLike;

	@ApiModelProperty("任务名称模糊忽略大小写")
	private String nameLikeIgnoreCase;

	@ApiModelProperty("任务名称列表")
	private List<String> nameList;

	@ApiModelProperty("任务名称列表忽略大小写")
	private List<String> nameListIgnoreCase;

	@ApiModelProperty("描述")
	private String description;

	@ApiModelProperty("描述模糊")
	private String descriptionLike;

	@ApiModelProperty("描述模糊忽略大小写")
	private String descriptionLikeIgnoreCase;

	@ApiModelProperty("优先级")
	private Integer priority;

	@ApiModelProperty("最小优先级")
	private Integer minPriority;

	@ApiModelProperty("最大优先级")
	private Integer maxPriority;

	@ApiModelProperty("被分配任务的人")
	private String assignee;

	@ApiModelProperty("被分配任务的人模糊")
	private String assigneeLike;

	@ApiModelProperty("被分配任务的人模糊忽略大小写")
	private String assigneeLikeIgnoreCase;

	@ApiModelProperty("被分配任务的人列表")
	private List<String> assigneeIds;

	@ApiModelProperty("参与的用户")
	private String involvedUser;

	@ApiModelProperty("参与的用户组")
	private List<String> involvedGroups;

	@ApiModelProperty("拥有者")
	private String owner;

	@ApiModelProperty("拥有者模糊")
	private String ownerLike;

	@ApiModelProperty("拥有者模糊忽略大小写")
	private String ownerLikeIgnoreCase;

	@ApiModelProperty("未分配")
	private Boolean unassigned;

	@ApiModelProperty("无代理状态")
	private Boolean noDelegationState;

	@ApiModelProperty("代理状态")
	private DelegationState delegationState;

	@ApiModelProperty("候选用户")
	private String candidateUser;

	@ApiModelProperty("候选用户组")
	private String candidateGroup;

	@ApiModelProperty("候选用户组列表")
	private List<String> candidateGroups;

	@ApiModelProperty("租户ID")
	private String tenantId;

	@ApiModelProperty("租户ID模糊")
	private String tenantIdLike;

	@ApiModelProperty("排除租户ID")
	private Boolean withoutTenantId;

	@ApiModelProperty("流程实例ID")
	private String processInstanceId;

	@ApiModelProperty("流程实例ID列表")
	private List<String> processInstanceIds;

	@ApiModelProperty("执行ID")
	private String executionId;

	@ApiModelProperty("创建日期")
	private Date createTime;

	@ApiModelProperty("在此日期前创建")
	private Date createTimeBefore;

	@ApiModelProperty("在此日期后创建")
	private Date createTimeAfter;

	@ApiModelProperty("分类")
	private String category;

	@ApiModelProperty("key")
	private String key;

	@ApiModelProperty("key模糊")
	private String keyLike;

	@ApiModelProperty("流程定义key")
	private String processDefinitionKey;

	@ApiModelProperty("流程定义key模糊")
	private String processDefinitionKeyLike;

	@ApiModelProperty("流程定义key模糊忽略大小写")
	private String processDefinitionKeyLikeIgnoreCase;

	@ApiModelProperty("流程定义key列表")
	private List<String> processDefinitionKeys;

	@ApiModelProperty("流程定义ID")
	private String processDefinitionId;

	@ApiModelProperty("流程定义名称")
	private String processDefinitionName;

	@ApiModelProperty("流程定义名称模糊")
	private String processDefinitionNameLike;

	@ApiModelProperty("流程分类列表")
	private List<String> processCategoryInList;

	@ApiModelProperty("排除的流程分类列表")
	private List<String> processCategoryNotInList;

	@ApiModelProperty("部署ID")
	private String deploymentId;

	@ApiModelProperty("部署ID列表")
	private List<String> deploymentIds;

	@ApiModelProperty("流程实例BusinessKey")
	private String processInstanceBusinessKey;

	@ApiModelProperty("流程实例BusinessKey模糊")
	private String processInstanceBusinessKeyLike;

	@ApiModelProperty("流程实例BusinessKey模糊忽略大小写")
	private String processInstanceBusinessKeyLikeIgnoreCase;

	@ApiModelProperty("截止日期")
	private Date dueDate;

	@ApiModelProperty("在此日期前截止")
	private Date dueBefore;

	@ApiModelProperty("在此日期后截止")
	private Date dueAfter;

	@ApiModelProperty("无截止日期")
	private Boolean withoutDueDate;

	@ApiModelProperty("状态")
	private SuspensionState suspensionState;

	@ApiModelProperty("排除子任务")
	private Boolean excludeSubtasks;

	@ApiModelProperty("包括任务本地变量")
	private Boolean includeTaskLocalVariables;

	@ApiModelProperty("包括流程本地变量")
	private Boolean includeProcessVariables;

	@ApiModelProperty("任务变量限制")
	private Integer taskVariablesLimit;

	@ApiModelProperty("用户ID")
	private String userIdForCandidateAndAssignee;

	@ApiModelProperty("是否候选用户&被分配任务的人")
	private Boolean bothCandidateAndAssigned;

	@ApiModelProperty("本地化")
	private String locale;

	@ApiModelProperty("本地化")
	private Boolean withLocalizationFallback;

//	private Boolean orActive;
//	private List<TaskQueryImpl> orQueryObjects = new ArrayList();
//	private TaskQueryImpl currentOrQueryObject = null;

}
