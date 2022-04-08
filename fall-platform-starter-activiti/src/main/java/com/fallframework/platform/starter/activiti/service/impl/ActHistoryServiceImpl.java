package com.fallframework.platform.starter.activiti.service.impl;

import com.fallframework.platform.starter.activiti.service.ActHistoryService;
import org.activiti.engine.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhuangpf
 */
@Service
public class ActHistoryServiceImpl implements ActHistoryService {

	@Autowired
	private HistoryService historyService;
	
}
