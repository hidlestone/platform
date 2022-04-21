package com.fallframework.platform.starter.activiti.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
@ApiModel("历史活动实例查询参数")
public class HistoricActivityInstanceQueryRequest extends BasePageRequest {

	private static final long serialVersionUID = -8180935181416520683L;

	@ApiModelProperty("ID")
	protected String activityInstanceId;

	@ApiModelProperty("流程实例ID")
	protected String processInstanceId;

	@ApiModelProperty("执行ID")
	protected String executionId;

	@ApiModelProperty("流程定义ID")
	protected String processDefinitionId;

	@ApiModelProperty("活动ID")
	protected String activityId;

	@ApiModelProperty("名称")
	protected String activityName;

	@ApiModelProperty("类型")
	protected String activityType;

	@ApiModelProperty("被指派的人")
	protected String assignee;

	@ApiModelProperty("租户ID")
	protected String tenantId;

	@ApiModelProperty("租户ID模糊")
	protected String tenantIdLike;

	@ApiModelProperty("排除租户ID")
	protected Boolean withoutTenantId;

	@ApiModelProperty("已结束")
	protected Boolean finished;

	@ApiModelProperty("未结束")
	protected Boolean unfinished;

	@ApiModelProperty("删除原因")
	protected String deleteReason;

	@ApiModelProperty("删除原因模糊")
	protected String deleteReasonLike;

}
