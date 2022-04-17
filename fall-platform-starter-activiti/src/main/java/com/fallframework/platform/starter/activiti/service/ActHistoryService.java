package com.fallframework.platform.starter.activiti.service;

import com.fallframework.platform.starter.activiti.model.HistoricActivityRequest;
import com.fallframework.platform.starter.activiti.model.HistoricActivityResponse;
import com.fallframework.platform.starter.activiti.model.HistoricTaskInstanceRequest;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.engine.history.HistoricTaskInstance;

public interface ActHistoryService {

	/**
	 * 根据流程实例ID分页查询历史活动信息
	 *
	 * @param request 请求参数
	 * @return 历史活动信息
	 */
	ResponseResult<Leaf<HistoricActivityResponse>> getHistoricActivityList(HistoricActivityRequest request);

	/**
	 * 分页查询历史任务列表
	 *
	 * @param request 请求参数
	 * @return 历史任务列表
	 */
	ResponseResult<Leaf<HistoricTaskInstance>> getHistoricTaskInstanceList(HistoricTaskInstanceRequest request);

}
