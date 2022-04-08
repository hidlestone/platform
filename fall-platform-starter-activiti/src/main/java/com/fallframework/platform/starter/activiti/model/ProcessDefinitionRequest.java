package com.fallframework.platform.starter.activiti.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
@ApiModel("流程信息请求参数")
public class ProcessDefinitionRequest extends BasePageRequest {

	private static final long serialVersionUID = 1968146285061897822L;

	private String id;
	private String name;
	private String description;
	private String key;
	private int version;
	private String category;
	private String deploymentId;
	private String resourceName;

}
