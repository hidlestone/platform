package com.fallframework.platform.starter.wechatwork;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhuangpf
 */
@MapperScan(basePackages = {
		"com.fallframework.platform.starter", // 基础字段
})
@SpringBootApplication
@ComponentScan(basePackages = {"com.fallframework"})
public class WXWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(WXWorkApplication.class, args);
	}

}
