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
	 * 流程部署
	 *
	 * @param deploymentId 部署ID
	 * @return 是否部署成功
	 */
	ResponseResult bpmDeploy(String deploymentId);

	/**
	 * 分页查询流程定义列表
	 *
	 * @param request 请求参数
	 * @return 流程定义分页
	 */
	ResponseResult<Leaf<ProcessDefinitionResponse>> getProcessDefinitionList(ProcessDefinitionRequest request);

	/**
	 * 下载资源文件
	 *
	 * @param definitionKey 流程定义key
	 * @return 文件
	 */
	ResponseResult downloadBpmnFile(String definitionKey);

}
