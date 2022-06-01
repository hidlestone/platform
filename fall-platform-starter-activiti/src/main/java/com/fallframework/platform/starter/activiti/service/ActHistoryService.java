package com.fallframework.platform.starter.activiti.service;

import com.fallframework.platform.starter.activiti.model.HistoricActivityInstanceQueryDto;
import com.fallframework.platform.starter.activiti.model.HistoricActivityOutVo;
import com.fallframework.platform.starter.activiti.model.HistoricTaskInstanceQueryDto;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.engine.history.HistoricTaskInstance;

import java.io.IOException;

public interface ActHistoryService {

	/**
	 * 分页查询历史活动信息
	 *
	 * @param request 请求参数
	 * @return 历史活动信息
	 */
	ResponseResult<Leaf<HistoricActivityOutVo>> getHistoricActivityList(HistoricActivityInstanceQueryDto request);

	/**
	 * 分页查询历史任务列表
	 *
	 * @param request 请求参数
	 * @return 历史任务列表
	 */
	ResponseResult<Leaf<HistoricTaskInstance>> getHistoricTaskInstanceList(HistoricTaskInstanceQueryDto request);

	/**
	 * 根据流程实例ID获取获取流程(跟踪)图(高亮显示)
	 *
	 * @param processInstanceId 流程实例ID
	 * @return 流程图base64格式
	 */
	ResponseResult<String> generateProcessDiagram(String processInstanceId) throws IOException;

}
