package com.fallframework.platform.starter.hystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author zhuangpf
 */
@EnableHystrix
@EnableHystrixDashboard
@SpringBootApplication
public class HystrixdashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixdashboardApplication.class, args);
	}
	
}
