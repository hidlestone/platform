package com.fallframework.platform.starter.activiti.model;

import com.fallframework.platform.starter.api.response.BaseEntityResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.activiti.engine.form.FormProperty;

import java.util.List;

/**
 * @author zhuangpf
 */
@Getter
@Setter
@ApiModel("跳转流程开启页携带的表单信息")
public class StartProcessOutVo extends BaseEntityResponse {

	private static final long serialVersionUID = 7307907859920703125L;

	@ApiModelProperty("流程定义ID")
	private String procDefId;

	@ApiModelProperty("表单信息")
	private List<FormProperty> formProperties;

	public StartProcessOutVo(String procDefId, List<FormProperty> formProperties) {
		this.procDefId = procDefId;
		this.formProperties = formProperties;
	}
	
}
