package com.fallframework.platform.starter.activiti.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;

/**
 * @author zhuangpf
 */
@Getter
@Setter
@ApiModel("保存用户&用户组请求参数")
public class SaveUserAndGroupRequest extends BaseEntityRequest {

	private static final long serialVersionUID = 8444116516188293125L;

	@ApiModelProperty("用户")
	private User user;

	@ApiModelProperty("用户组")
	private Group group;

}
