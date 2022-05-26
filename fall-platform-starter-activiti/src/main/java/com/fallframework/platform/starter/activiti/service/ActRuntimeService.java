package com.fallframework.platform.starter.activiti.service;

import com.fallframework.platform.starter.activiti.model.StartProcessInstanceDto;
import com.fallframework.platform.starter.api.response.ResponseResult;

public interface ActRuntimeService {

	/**
	 * 启动流程实例
	 *
	 * @param dto 请求参数
	 * @return 是否启动成功
	 */
	ResponseResult startProcessInstance(StartProcessInstanceDto dto);

	/**
	 * 查询流程实例是否已经结束
	 *
	 * @param processInstanceId 流程实例ID
	 * @return 是否已经结束
	 */
	Boolean isProcessFinished(String processInstanceId);

}
