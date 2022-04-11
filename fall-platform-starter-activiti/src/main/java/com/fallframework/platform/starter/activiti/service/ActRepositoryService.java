package com.fallframework.platform.starter.activiti.service;

import com.fallframework.platform.starter.activiti.model.ProcessDefinitionRequest;
import com.fallframework.platform.starter.activiti.model.ProcessDefinitionResponse;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.bpmn.model.BpmnModel;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * @author zhuangpf
 */
public interface ActRepositoryService {

	/**
	 * 根据输入流部署流程
	 *
	 * @param resourceName 名称
	 * @param inputStream  输入流
	 * @return 是否部署成功
	 */
	ResponseResult deployByInputStream(String resourceName, InputStream inputStream);

	/**
	 * 根据跟路径下文件部署流程
	 *
	 * @param resource 文件名
	 * @return 是否部署成功
	 */
	ResponseResult deployByClasspathResource(String resource);

	/**
	 * 根据字符串部署流程
	 *
	 * @param resourceName 名称
	 * @param str          流程定义字符串
	 * @return 是否部署成功
	 */
	ResponseResult deployByString(String resourceName, String str);

	/**
	 * 根据zip输入流部署流程
	 *
	 * @param zipInputStream 输入流
	 * @return 是否部署成功
	 */
	ResponseResult deployByZipInputStream(ZipInputStream zipInputStream);

	/**
	 * 根据BpmnModel部署流程
	 *
	 * @param resourceName 名称
	 * @param bpmnModel    model
	 * @return 是否部署成功
	 */
	ResponseResult deployByBpmnModel(String resourceName, BpmnModel bpmnModel);

	/**
	 * 流程部署
	 *
	 * @param deploymentId 部署ID
	 * @return 是否部署成功
	 */
	ResponseResult bpmDeploy(String deploymentId);

	/**
	 * 删除流程定义
	 *
	 * @param deploymentId 部署ID
	 * @return 是否删除成功
	 */
	ResponseResult deleteDeployment(String deploymentId);

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
