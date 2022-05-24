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
@ApiModel("用户查询请求参数")
public class UserQueryDto extends BasePageRequest {

	private static final long serialVersionUID = 7213946972085592535L;

	@ApiModelProperty("ID")
	private String id;

	@ApiModelProperty("firstName")
	private String firstName;

	@ApiModelProperty("firstName模糊")
	private String firstNameLike;

	@ApiModelProperty("lastName")
	private String lastName;

	@ApiModelProperty("lastName模糊")
	private String lastNameLike;

	@ApiModelProperty("fullName模糊")
	private String fullNameLike;

	@ApiModelProperty("邮箱")
	private String email;

	@ApiModelProperty("邮箱模糊")
	private String emailLike;

	@ApiModelProperty("用户组ID")
	private String groupId;

	@ApiModelProperty("流程定义ID")
	private String procDefId;

}
