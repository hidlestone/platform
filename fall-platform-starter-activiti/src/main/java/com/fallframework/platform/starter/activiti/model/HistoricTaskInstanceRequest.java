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
@ApiModel("查询历史任务请求参数")
public class HistoricTaskInstanceRequest extends BasePageRequest {

	private static final long serialVersionUID = 896428922293078443L;

	@ApiModelProperty("流程定义key")
	private String procdefKey;

	@ApiModelProperty("流程实例ID")
	private String processInstanceId;
	
}
