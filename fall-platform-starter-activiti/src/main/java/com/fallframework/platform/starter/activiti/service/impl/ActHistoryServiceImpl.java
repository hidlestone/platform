package com.fallframework.platform.starter.activiti.service.impl;

import com.fallframework.platform.starter.activiti.model.HistoricTaskInstanceRequest;
import com.fallframework.platform.starter.activiti.service.ActHistoryService;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuangpf
 */
@Service
public class ActHistoryServiceImpl implements ActHistoryService {

	@Autowired
	private HistoryService historyService;

	@Override
	public ResponseResult<Leaf<HistoricTaskInstance>> getHistoricTaskInstanceList(HistoricTaskInstanceRequest request) {
		// 查询条件
		HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery();
		if (StringUtils.isNotEmpty(request.getProcessInstanceId())) {
			historicTaskInstanceQuery.processInstanceId(request.getProcessInstanceId());
		}
		if (StringUtils.isNotEmpty(request.getProcdefKey())) {
			historicTaskInstanceQuery.processDefinitionKey(request.getProcdefKey());
		}
		long total = historicTaskInstanceQuery.count();
		List<HistoricTaskInstance> historicTaskInstanceList = historicTaskInstanceQuery.orderByHistoricTaskInstanceStartTime().asc()
				.listPage(request.getFirstRow(), request.getPageSize());
		Leaf<HistoricTaskInstance> leaf = new Leaf(historicTaskInstanceList, total, request);
		return ResponseResult.success(leaf);
	}

	
	
	
}
