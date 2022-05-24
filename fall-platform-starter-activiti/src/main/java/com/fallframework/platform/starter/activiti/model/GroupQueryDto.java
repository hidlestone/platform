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
@ApiModel("用户组查询请求参数")
public class GroupQueryDto extends BasePageRequest {

	private static final long serialVersionUID = 948470878650186549L;

	@ApiModelProperty("ID")
	protected String id;

	@ApiModelProperty("用户组名称")
	protected String name;

	@ApiModelProperty("用户组名称模糊")
	protected String nameLike;

	@ApiModelProperty("类型")
	protected String type;

	@ApiModelProperty("用户ID")
	protected String userId;

	@ApiModelProperty("流程定义ID")
	protected String procDefId;

}
