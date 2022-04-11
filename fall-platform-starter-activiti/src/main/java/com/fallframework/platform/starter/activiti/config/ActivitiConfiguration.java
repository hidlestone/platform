package com.fallframework.platform.starter.activiti.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 测试用
 */
@Configuration
public class ActivitiConfiguration {
    @Bean
    @Primary
    public DataSource database() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/wordplay_test?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8")
                .username("root")
                .password("root")
                .driverClassName("com.mysql.jdbc.Driver")
                .build();
    }
}
