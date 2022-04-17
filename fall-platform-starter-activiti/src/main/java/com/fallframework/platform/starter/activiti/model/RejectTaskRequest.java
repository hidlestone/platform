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
@ApiModel("拒绝任务请求参数")
public class RejectTaskRequest extends BaseEntityRequest {

	private static final long serialVersionUID = 1275766839004806991L;

	@ApiModelProperty("分配到任务的人")
	private String assignee;

	@ApiModelProperty("任务ID")
	private String taskId;

	@ApiModelProperty("是否返回开始")
	private Boolean returnStartFlag;

}
