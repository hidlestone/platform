package com.fallframework.platform.starter.activiti.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author zhuangpf
 */
@Getter
@Setter
@ApiModel("启动流程示例请求参数")
public class StartProcessInstanceRequest extends BaseEntityRequest {

	private static final long serialVersionUID = 7366724410184806061L;

	@ApiModelProperty("流程定义key")
	private String processDefinitionKey;


	/**
	 * 参数明	类型	说明	是否必填	示例
	 * instanceKey	string	BPMN文件实例名称	是	process_core_ent_credit
	 * applyUserId	string	启动者ID流程发起人	是	资产经理
	 * params	object	表单参数	是	{"flowId":"2","param1":"内容1","param2":"内容2","param3":"内容3","param4":"内容4","param5":"内容5","applyUserId":"资产经理","assignee":"资产经理"}
	 * +assignee	string	审核人	是	资产经理
	 */
	@ApiModelProperty("变量")
	private Map variables;

}
