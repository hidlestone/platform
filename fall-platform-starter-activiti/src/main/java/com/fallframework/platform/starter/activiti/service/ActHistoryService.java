package com.fallframework.platform.starter.activiti.service;

import com.fallframework.platform.starter.activiti.model.HistoricTaskInstanceRequest;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.engine.history.HistoricTaskInstance;

public interface ActHistoryService {

	/**
	 * 分页查询历史任务列表
	 *
	 * @param request 请求参数
	 * @return 历史任务列表
	 */
	ResponseResult<Leaf<HistoricTaskInstance>> getHistoricTaskInstanceList(HistoricTaskInstanceRequest request);

}
