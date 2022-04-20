package com.fallframework.platform.starter.activiti.service.impl;

import com.fallframework.platform.starter.activiti.service.ActFormService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.engine.FormService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zhuangpf
 */
@Service
public class ActFormServiceImpl implements ActFormService {

	@Autowired
	private FormService formService;

	@Override
	public ResponseResult getStartFormKey(String procDefId) {
		String startFormKey = formService.getStartFormKey(procDefId);
		return ResponseResult.success(startFormKey);
	}

	@Override
	public ResponseResult<List<FormProperty>> getStartFormData(String procDefId) {
		StartFormData startFormData = formService.getStartFormData(procDefId);
		List<FormProperty> formProperties = startFormData.getFormProperties();
		return ResponseResult.success(formProperties);
	}

	@Override
	public ResponseResult submitStartFormData(String procDefId, Map<String, String> properties) {
		ProcessInstance processInstance = formService.submitStartFormData(procDefId, properties);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult<TaskFormData> getTaskFormData(String taskId) {
		TaskFormData taskFormData = formService.getTaskFormData(taskId);
		return ResponseResult.success(taskFormData);
	}

	@Override
	public ResponseResult submitTaskFormData(String taskId, Map<String, String> properties) {
		formService.submitTaskFormData(taskId, properties);
		return ResponseResult.success();
	}
	
}
