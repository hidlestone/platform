package com.fallframework.platform.starter.activiti.model;

import com.fallframework.platform.starter.api.response.BaseEntityResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * @author zhuangpf
 */
@Getter
@Setter
@ApiModel("任务详细信息")
public class TaskDetailOutVo extends BaseEntityResponse {

	private static final long serialVersionUID = 4741799794566009527L;

	@ApiModelProperty("任务")
	private Task task;

	@ApiModelProperty("表单信息")
	private List<FormProperty> formProperties;

}
