package com.fallframework.platform.starter.activiti.service.impl;

import com.fallframework.platform.starter.activiti.model.StartProcessInstanceDto;
import com.fallframework.platform.starter.activiti.service.ActRuntimeService;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhuangpf
 */
@Service
public class ActRuntimeServiceImpl implements ActRuntimeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActRuntimeServiceImpl.class);

	@Autowired
	private RuntimeService runtimeService;

	/**
	 * 启动流程，并获取流程实例。
	 * 流程可以启动多个，所以每启动一个流程都会在数据库中插入一条新版本的流程数据。
	 * 通过key启动的流程就是当前key下最新版本的流程。
	 */
	@Override
	public ResponseResult startProcessInstance(StartProcessInstanceDto request) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(request.getProcessDefinitionKey(), request.getVariables());
		LOGGER.debug(String.format("startProcessInstance : id:%s, activitiId:%s", processInstance.getId(), processInstance.getActivityId()));
		if (null == processInstance) {
			return ResponseResult.fail();
		}
		return ResponseResult.success();
	}

	@Override
	public Boolean isProcessFinished(String processInstanceId) {
		// 判断流程是否结束，查询正在执行的执行对象表
		long count = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).count();
		return 0 == count;
	}

}
