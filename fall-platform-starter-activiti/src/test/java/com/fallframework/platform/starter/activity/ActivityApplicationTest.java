package com.fallframework.platform.starter.activity;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 测试类
 *
 * @author zengj
 * @date 2022/3/20 9:49 上午
 */
//@SpringBootTest(classes = ActivityApplication.class)
public class ActivityApplicationTest {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Test
    public void test() {
        String key = "737372ieieiie";
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("leave", key, new HashMap<>(0));
        System.out.println();
    }

}
