package com.fallframework.platform.starter.activiti.model;

import com.fallframework.platform.starter.api.response.BaseEntityResponse;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.IdentityLinkEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.task.DelegationState;

import java.util.Date;
import java.util.List;

/**
 * @author zhuangpf
 */
@Getter
@Setter
@ApiModel("流程任务")
public class TaskResponse extends BaseEntityResponse {

	private static final long serialVersionUID = 7667466328412619321L;

	private String owner;
	private int assigneeUpdatedCount;
	private String originalAssignee;
	private String assignee;
	private DelegationState delegationState;
	private String parentTaskId;
	private String name;
	private String localizedName;
	private String description;
	private String localizedDescription;
	private int priority = 50;
	private Date createTime;
	private Date dueDate;
	private int suspensionState;
	private String category;
	private boolean isIdentityLinksInitialized;
	private List<IdentityLinkEntity> taskIdentityLinkEntities;
	private String executionId;
	private ExecutionEntity execution;
	private String processInstanceId;
	private ExecutionEntity processInstance;
	private String processDefinitionId;
	private String taskDefinitionKey;
	private String formKey;
	private boolean isDeleted;
	private boolean isCanceled;
	private String eventName;
	private ActivitiListener currentActivitiListener;
	private String tenantId;
	private List<VariableInstanceEntity> queryVariables;
	private boolean forcedUpdate;
	private Date claimTime;

}
