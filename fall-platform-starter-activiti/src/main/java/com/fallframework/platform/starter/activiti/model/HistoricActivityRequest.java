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
@ApiModel("历史活动查询参数")
public class HistoricActivityRequest extends BasePageRequest {

	private static final long serialVersionUID = -8180935181416520683L;

	@ApiModelProperty("流程实例ID")
	private String processInstanceId;

}
