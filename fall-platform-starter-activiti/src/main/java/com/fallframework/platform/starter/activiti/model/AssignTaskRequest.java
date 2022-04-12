package com.fallframework.platform.starter.activiti.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
@ApiModel("指派任务请求参数")
public class AssignTaskRequest extends BaseEntityRequest {

	@ApiModelProperty(value = "任务ID")
	private String taskId;


	@ApiModelProperty(value = "审批人")
	private String assignee;

}
