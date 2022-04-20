package com.fallframework.platform.starter.activiti.service;

import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;

import java.util.List;
import java.util.Map;

/**
 * Activiti中总共有三种表单，动态表单，普通表单和外置表单
 *
 * @author zhuangpf
 */
public interface ActFormService {

	/**
	 * startFormKey对应流程文件中的startEvent中的属性activiti:formKey
	 *
	 * @param procDefId 流程定义ID
	 * @return startFormKey
	 */
	ResponseResult getStartFormKey(String procDefId);

	/**
	 * 获取表单信息
	 *
	 * @param procDefId 流程定义ID
	 * @return 表单信息
	 */
	ResponseResult<List<FormProperty>> getStartFormData(String procDefId);

	/**
	 * 提交开始的表单数据
	 *
	 * @param procDefId  流程定义ID
	 * @param properties 表单数据
	 * @return 是否提交成功
	 */
	ResponseResult submitStartFormData(String procDefId, Map<String, String> properties);

	/**
	 * 根据任务ID获取表单数据
	 *
	 * @param taskId 任务ID
	 * @return 任务表单数据
	 */
	ResponseResult<TaskFormData> getTaskFormData(String taskId);

	/**
	 * 提交任务的表单数据
	 *
	 * @param taskId     任务ID
	 * @param properties 表单数据
	 * @return 是否提交成功
	 */
	ResponseResult submitTaskFormData(String taskId, Map<String, String> properties);

}
