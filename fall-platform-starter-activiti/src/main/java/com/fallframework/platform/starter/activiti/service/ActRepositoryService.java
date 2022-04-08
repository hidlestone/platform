package com.fallframework.platform.starter.activiti.service;

import com.fallframework.platform.starter.activiti.model.ProcessDefinitionRequest;
import com.fallframework.platform.starter.activiti.model.ProcessDefinitionResponse;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;

/**
 * @author zhuangpf
 */
public interface ActRepositoryService {

	/**
	 * 分页查询流程定义列表
	 *
	 * @param request 请求参数
	 * @return 流程定义分页
	 */
	ResponseResult<Leaf<ProcessDefinitionResponse>> getProcessDefinitionList(ProcessDefinitionRequest request);

}
