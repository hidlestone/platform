package com.fallframework.platform.starter.activiti.service.impl;

import com.fallframework.platform.starter.activiti.model.ProcessDefinitionRequest;
import com.fallframework.platform.starter.activiti.model.ProcessDefinitionResponse;
import com.fallframework.platform.starter.activiti.service.ActRepositoryService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.runtime.api.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhuangpf
 */
@Service
public class ActRepositoryServiceImpl implements ActRepositoryService {

	@Autowired
	private RepositoryService repositoryService;

	@Override
	public ResponseResult<Page<ProcessDefinitionResponse>> getProcessDefinitionList(ProcessDefinitionRequest request) {
		// 查询对象
		ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();
		
		
		
		
		return null;
	}
}
