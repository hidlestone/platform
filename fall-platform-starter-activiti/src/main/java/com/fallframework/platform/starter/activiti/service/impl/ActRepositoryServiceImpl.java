package com.fallframework.platform.starter.activiti.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.fallframework.platform.starter.activiti.model.ProcessDefinitionRequest;
import com.fallframework.platform.starter.activiti.model.ProcessDefinitionResponse;
import com.fallframework.platform.starter.activiti.service.ActRepositoryService;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuangpf
 */
@Service
public class ActRepositoryServiceImpl implements ActRepositoryService {

	@Autowired
	private RepositoryService repositoryService;

	@Override
	public ResponseResult<Leaf<ProcessDefinitionResponse>> getProcessDefinitionList(ProcessDefinitionRequest request) {
		// 查询对象
		ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();
		// 查询条件
		// id
		if (StringUtils.isNotEmpty(request.getId())) {
			definitionQuery.processDefinitionId(request.getId());
		}
		// name
		if (StringUtils.isNotEmpty(request.getName())) {
			definitionQuery.processDefinitionNameLike(request.getName());
		}
		// key
		if (StringUtils.isNotEmpty(request.getKey())) {
			definitionQuery.processDefinitionKeyLike(request.getKey());
		}
		// version
		if (null != request.getVersion()) {
			definitionQuery.processDefinitionVersion(request.getVersion());
		}
		// category
		if (StringUtils.isNotEmpty(request.getCategory())) {
			definitionQuery.processDefinitionCategoryLike(request.getCategory());
		}
		// deploymentId
		if (StringUtils.isNotEmpty(request.getDeploymentId())) {
			definitionQuery.deploymentId(request.getDeploymentId());
		}
		// resourceName
		if (StringUtils.isNotEmpty(request.getResourceName())) {
			definitionQuery.processDefinitionResourceNameLike(request.getResourceName());
		}
		int start = (request.getPageNum() - 1) * request.getPageSize() + 1;
		// 总记录数
		long total = definitionQuery.count();
		definitionQuery.listPage(start, request.getPageSize());
		List<ProcessDefinition> processDefinitionList = definitionQuery
				.orderByDeploymentId().desc()                // 部署ID降序
				.orderByProcessDefinitionVersion().desc()    // 部署版本号降序
				.list();                                     // 获取全部
		List<ProcessDefinitionResponse> responseList = this.ProcessDefinitionToResponse(processDefinitionList);
		Leaf<ProcessDefinitionResponse> leaf = new Leaf<>(responseList, total, (total / request.getPageSize()) + 1, request.getPageNum());
		return ResponseResult.success(leaf);
	}

	/**
	 * @param processDefinitionList 流程定义列表
	 * @return 响应列表
	 */
	private List<ProcessDefinitionResponse> ProcessDefinitionToResponse(List<ProcessDefinition> processDefinitionList) {
		List<ProcessDefinitionResponse> responseList = new ArrayList<>();
		if (CollectionUtil.isNotEmpty(processDefinitionList)) {
			for (ProcessDefinition processDefinition : processDefinitionList) {
				ProcessDefinitionResponse response = new ProcessDefinitionResponse();
				response.setId(processDefinition.getId());
				response.setName(processDefinition.getName());
				response.setDescription(processDefinition.getDescription());
				response.setKey(processDefinition.getKey());
				response.setVersion(processDefinition.getVersion());
				response.setCategory(processDefinition.getCategory());
				response.setDeploymentId(processDefinition.getDeploymentId());
				response.setResourceName(processDefinition.getResourceName());
				response.setTenantId(processDefinition.getTenantId());
				response.setHistoryLevel(processDefinition.getVersion());
				response.setDiagramResourceName(processDefinition.getDiagramResourceName());
				response.setGraphicalNotationDefined(processDefinition.hasGraphicalNotation());
				response.setHasStartFormKey(processDefinition.hasStartFormKey());
				response.setEngineVersion(processDefinition.getEngineVersion());
				responseList.add(response);
			}
		}
		return responseList;
	}

}
