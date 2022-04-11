package com.fallframework.platform.starter;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhuangpf
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan(basePackages = {"com.fallframework.platform.starter.activiti.*"})
public class ActivitiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ActivitiApplication.class);
	}
	
}
