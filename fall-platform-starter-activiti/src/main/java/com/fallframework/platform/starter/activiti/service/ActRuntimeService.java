package com.fallframework.platform.starter.activiti.service;

import com.fallframework.platform.starter.api.response.ResponseResult;

public interface ActRuntimeService {

	/**
	 * 启动流程实例
	 *
	 * @param procdefKey 流程定义key
	 * @return 是否启动成功
	 */
	ResponseResult startProcessInstanceByKey(String procdefKey);

	
	
}
