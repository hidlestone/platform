package com.fallframework.platform.starter.activity;

import com.fallframework.platform.starter.ActivitiApplication;
import com.fallframework.platform.starter.activiti.model.PendingTaskRequest;
import com.fallframework.platform.starter.activiti.model.TaskResponse;
import com.fallframework.platform.starter.activiti.service.ActTaskService;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * 测试类
 *
 * @author zengj
 * @date 2022/3/20 9:49 上午
 */
@SpringBootTest(classes = ActivitiApplication.class)
public class ActivityApplicationTest {

	@Resource
	private RuntimeService runtimeService;

	@Resource
	private TaskService taskService;

	@Autowired
	private ActTaskService actTaskService;

	@Test
	public void test() {
		String key = "737372ieieiie";
		ProcessInstance instance = runtimeService.startProcessInstanceByKey("leave", key, new HashMap<>(0));
		System.out.println();
	}

	@Test
	public void testGetPendingTaskList() {
		PendingTaskRequest request = new PendingTaskRequest();
		request.setAssignee("admin");
//		request.setProcdefKey("TestProcess-01");
		request.setPageNum(2);
		request.setPageSize(1);
		ResponseResult<Leaf<Task>> pendingTaskList = actTaskService.getPendingTaskList(request);
		System.out.println(pendingTaskList);
	}


}
