package com.fallframework.platform.starter.activity;

import com.fallframework.platform.starter.ActivitiApplication;
import com.fallframework.platform.starter.activiti.model.ModelQueryRequest;
import com.fallframework.platform.starter.activiti.service.ActRepositoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhuangpf
 */
@SpringBootTest(classes = ActivitiApplication.class)
public class ActRepositoryServiceTest {

	@Autowired
	private ActRepositoryService actRepositoryService;

	@Test
	public void getModelListTest() {
		ModelQueryRequest request = new ModelQueryRequest();
		request.setPageNum(1);
		request.setPageSize(2);
		request.setNameLike("modelertest-01");
		actRepositoryService.getModelList(request);
	}


}
