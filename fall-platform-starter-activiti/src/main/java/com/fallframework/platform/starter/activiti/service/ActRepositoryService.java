package com.fallframework.platform.starter.activiti.service;

import com.fallframework.platform.starter.activiti.model.ModelQueryRequest;
import com.fallframework.platform.starter.activiti.model.ProcessDefinitionQueryRequest;
import com.fallframework.platform.starter.activiti.model.StartProcessResponse;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;

import java.io.IOException;
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
	 * 根据名称发布流程
	 *
	 * @param bpmnName 流程名称
	 * @return 是否部署成功
	 */
	ResponseResult deployByBpmnName(String bpmnName);

	/**
	 * 删除流程定义
	 *
	 * @param deploymentId 部署ID
	 * @return 是否删除成功
	 */
	ResponseResult deleteDeployment(String deploymentId);

	/**
	 * 分页查询((模型)已部署)流程定义列表
	 *
	 * @param request 请求参数
	 * @return 流程定义分页
	 */
	ResponseResult<Leaf<ProcessDefinition>> getProcessDefinitionList(ProcessDefinitionQueryRequest request);

	/**
	 * 下载资源文件
	 *
	 * @param definitionKey 流程定义key
	 * @return 文件
	 */
	ResponseResult downloadBpmnile(String definitionKey);

	/**
	 * 分页查询模型列表
	 *
	 * @param request 请求参数
	 * @return 模型分页
	 */
	ResponseResult<Leaf<Model>> getModelList(ModelQueryRequest request);

	/**
	 * 根据已设计好的模型进行部署
	 *
	 * @param modelId 模型ID
	 * @return 是否部署成功
	 */
	ResponseResult deployByModelId(String modelId) throws IOException;

	/**
	 * 跳转到流程开启页(如果有的话)携带的数据
	 *
	 * @param procDefId 流程定义ID
	 * @return 开启流程所需信息
	 */
	ResponseResult<StartProcessResponse> startProcessInfo(String procDefId);


}
