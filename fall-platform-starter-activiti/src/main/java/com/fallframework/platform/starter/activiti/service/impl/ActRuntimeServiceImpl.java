package com.fallframework.platform.starter.activiti.service.impl;

import com.fallframework.platform.starter.activiti.service.ActRuntimeService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhuangpf
 */
@Service
public class ActRuntimeServiceImpl implements ActRuntimeService {

	@Autowired
	private RuntimeService runtimeService;

	@Override
	public ResponseResult startProcessInstanceByKey(String procdefKey) {
		// 根据流程定义的key启动流程
		ProcessInstance instance = runtimeService.startProcessInstanceByKey(procdefKey);
		return ResponseResult.success();
	}

}
