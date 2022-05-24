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
@ApiModel("待处理任务查询请求参数")
public class PendingTaskDto extends BasePageRequest {

	private static final long serialVersionUID = -8319995691866984757L;

	@ApiModelProperty("流程定义key")
	private String procdefKey;

	@ApiModelProperty("分配到任务的人")
	private String assignee;

	@ApiModelProperty(value = "候选用户")
	private String candidateUser;

	@ApiModelProperty(value = "候选用户组")
	private String candidateGroup;

}
