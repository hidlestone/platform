package com.fallframework.platform.starter.activiti.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.activiti.engine.impl.persistence.entity.SuspensionState;

import java.util.Set;

/**
 * @author zhuangpf
 */
@Getter
@Setter
@ApiModel("流程信息请求参数")
public class ProcessDefinitionQueryDto extends BasePageRequest {

	private static final long serialVersionUID = 1968146285061897822L;

	@ApiModelProperty("ID")
	protected String id;

	@ApiModelProperty("IDS")
	protected Set<String> ids;

	@ApiModelProperty("分类")
	protected String category;

	@ApiModelProperty("分类模糊")
	protected String categoryLike;

	@ApiModelProperty("分类不等于")
	protected String categoryNotEquals;

	@ApiModelProperty("名称")
	protected String name;

	@ApiModelProperty("名称模糊")
	protected String nameLike;

	@ApiModelProperty("部署ID")
	protected String deploymentId;

	@ApiModelProperty("部署IDS")
	protected Set<String> deploymentIds;

	@ApiModelProperty("key")
	protected String key;

	@ApiModelProperty("key模糊")
	protected String keyLike;

	@ApiModelProperty("资源名称如：请假流程.bpmn20.xml")
	protected String resourceName;

	@ApiModelProperty("资源名称模糊")
	protected String resourceNameLike;

	@ApiModelProperty("版本号")
	protected Integer version;

	@ApiModelProperty("版本号大于")
	protected Integer versionGt;

	@ApiModelProperty("版本号大于等于")
	protected Integer versionGte;

	@ApiModelProperty("版本号小于")
	protected Integer versionLt;

	@ApiModelProperty("版本号小于等于")
	protected Integer versionLte;

	@ApiModelProperty("是否最新")
	protected Boolean latest;

	@ApiModelProperty("状态")
	protected SuspensionState suspensionState;

	@ApiModelProperty("流程定义ID")
	protected String procDefId;

	@ApiModelProperty("租户ID")
	protected String tenantId;

	@ApiModelProperty("租户ID模糊")
	protected String tenantIdLike;

	@ApiModelProperty("排除租户ID")
	protected Boolean withoutTenantId;

	@ApiModelProperty("事件订阅名称")
	protected String eventSubscriptionName;

//	@ApiModelProperty("事件订阅类型")
//	protected String eventSubscriptionType;

}
