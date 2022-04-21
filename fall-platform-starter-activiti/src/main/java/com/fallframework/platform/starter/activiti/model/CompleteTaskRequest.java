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
@ApiModel("完成待处理任务请求参数")
public class CompleteTaskRequest extends BaseEntityRequest {

	private static final long serialVersionUID = 4467556248359897765L;

	@ApiModelProperty("分配到任务的人")
	private String assignee;

	@ApiModelProperty("任务ID")
	private String taskId;

}
