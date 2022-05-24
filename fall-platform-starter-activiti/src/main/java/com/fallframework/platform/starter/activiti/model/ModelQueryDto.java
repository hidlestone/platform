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
@ApiModel("模型查询请求参数")
public class ModelQueryDto extends BasePageRequest {

	private static final long serialVersionUID = -6873629971851226583L;

	@ApiModelProperty(value = "ID")
	protected String id;

	@ApiModelProperty(value = "分类")
	protected String category;

	@ApiModelProperty(value = "分类模糊")
	protected String categoryLike;

	@ApiModelProperty(value = "分类不为XX")
	protected String categoryNotEquals;

	@ApiModelProperty(value = "名称")
	protected String name;

	@ApiModelProperty(value = "名称模糊")
	protected String nameLike;

	@ApiModelProperty(value = "key")
	protected String key;

	@ApiModelProperty(value = "版本号")
	protected Integer version;

	@ApiModelProperty(value = "是否最新")
	protected Boolean latest;

	@ApiModelProperty(value = "部署ID")
	protected String deploymentId;

	@ApiModelProperty(value = "未部署标识")
	protected Boolean notDeployed;

	@ApiModelProperty(value = "已部署标识")
	protected Boolean deployed;

	@ApiModelProperty(value = "租户ID")
	protected String tenantId;

	@ApiModelProperty(value = "租户ID模糊")
	protected String tenantIdLike;

	@ApiModelProperty(value = "排除租户ID")
	protected Boolean withoutTenantId;

}
