package com.fallframework.platform.starter.activity;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 工作流启动类
 *
 * @author zengj
 * @date 2022/3/19 4:42 下午
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ActivityApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActivityApplication.class, args);
    }
}
