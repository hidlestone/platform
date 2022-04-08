package com.fallframework.platform.starter.activiti.service;

import com.fallframework.platform.starter.activiti.model.ProcessDefinitionRequest;
import com.fallframework.platform.starter.activiti.model.ProcessDefinitionResponse;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.runtime.api.query.Page;

/**
 * @author zhuangpf
 */
public interface ActRepositoryService {

	/**
	 * @param request 请求参数
	 * @return 流程定义分页
	 */
	ResponseResult<Page<ProcessDefinitionResponse>> getProcessDefinitionList(ProcessDefinitionRequest request);

}
