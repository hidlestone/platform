# fallplatform补全计划
平台级框架 fall platform 架构设计。系统常用功能模块封装，实现功能的插拔和更换，通过简单的系统配置，可以定制简单且功能全面、架构合理完善的基础平台，使开发者可以更加专注于业务功能的实现。

参考 spring boot/cloud 的架构设计。各个模块设计如下，后续可能增加其他功能模块，各模块设计会在此专栏进行发布。

## 一、一览
```
fall-platform-dependencies
fall-platform-parent
fall-platform-starter-api-dependency
fall-platform-starter-cache-ehcache
fall-platform-starter-cache-mongodb
fall-platform-starter-cache-redis
fall-platform-starter-cache-simple
fall-platform-starter-config
fall-platform-starter-control-dependency
fall-platform-starter-core
fall-platform-starter-data
fall-platform-starter-doc
fall-platform-starter-elasticjob
fall-platform-starter-elasticsearch
fall-platform-starter-excel
fall-platform-starter-file
fall-platform-starter-file-ali
fall-platform-starter-file-qiniu
fall-platform-starter-flyway
fall-platform-starter-gateway
fall-platform-starter-graylog
fall-platform-starter-guard
fall-platform-starter-httpClient
fall-platform-starter-i18n
fall-platform-starter-mail
fall-platform-starter-mq
fall-platform-starter-mq-kafka
fall-platform-starter-mq-rabbit
fall-platform-starter-mq-rocket
fall-platform-starter-pay
fall-platform-starter-rbac
fall-platform-starter-security
fall-platform-starter-shiro
fall-platform-starter-sms
fall-platform-starter-sms-aliyun
fall-platform-starter-social
fall-platform-starter-task
fall-platform-starter-task-quartz
fall-platform-starter-task-xxljob
fall-platform-starter-uflo
fall-platform-starter-ureport
fall-platform-starter-urule
fall-platform-starter-webflux
fall-platform-starter-webservice
fall-platform-starter-websocket
fall-platform-starter-wechat
fall-platform-starters
```

spring cloud
```
spring-cloud-starter
spring-cloud-starter-alibaba
spring-cloud-starter-alibaba-nacos-discovery
spring-cloud-starter-archaius
spring-cloud-starter-bus-amqp
spring-cloud-starter-circuitbreaker
spring-cloud-starter-circuitbreaker-reactor-resilience4j
spring-cloud-starter-config
spring-cloud-starter-consul
spring-cloud-starter-consul-discovery
spring-cloud-starter-eureka
spring-cloud-starter-eureka-server
spring-cloud-starter-feign
spring-cloud-starter-gateway
spring-cloud-starter-hystrix
spring-cloud-starter-hystrix-dashboard
spring-cloud-starter-loadbalancer
spring-cloud-starter-netflix
spring-cloud-starter-netflix-archaius
spring-cloud-starter-netflix-eureka-client
spring-cloud-starter-netflix-eureka-server
spring-cloud-starter-netflix-hystrix
spring-cloud-starter-netflix-hystrix-dashboard
spring-cloud-starter-netflix-ribbon
spring-cloud-starter-netflix-zuul
spring-cloud-starter-oauth2
spring-cloud-starter-openfeign
spring-cloud-starter-ribbon
spring-cloud-starter-security
spring-cloud-starter-sleuth
spring-cloud-starter-stream-rabbit
spring-cloud-starter-zipkin
spring-cloud-starter-zookeeper
spring-cloud-starter-zookeeper-discovery
spring-cloud-starter-zuul 
```

spring boot
```
spring-boot-starter
spring-boot-starter-activemq
spring-boot-starter-actuator
spring-boot-starter-amqp
spring-boot-starter-aop
spring-boot-starter-cache
spring-boot-starter-data
spring-boot-starter-data-elasticsearch
spring-boot-starter-data-jdbc
spring-boot-starter-data-jpa
spring-boot-starter-data-ldap
spring-boot-starter-data-mongodb
spring-boot-starter-data-neo4j
spring-boot-starter-data-redis
spring-boot-starter-data-redis-reactive
spring-boot-starter-freemarker
spring-boot-starter-jdbc
spring-boot-starter-jersey
spring-boot-starter-jetty
spring-boot-starter-json
spring-boot-starter-logging
spring-boot-starter-mail
spring-boot-starter-parent
spring-boot-starter-quartz
spring-boot-starter-reactor-netty
spring-boot-starter-security
spring-boot-starter-test
spring-boot-starter-thymeleaf
spring-boot-starter-tomcat
spring-boot-starter-undertow
spring-boot-starter-validation
spring-boot-starter-web
spring-boot-starter-webflux
spring-boot-starter-websocket
spring-boot-starters
```

## 二、功能
```
fall-platform-dependencies              【DONE】全局依赖管理
fall-platform-parent                    【DONE】父工程：dependencies->parent->staters->xxxstarter
fall-platform-starter-api-dependency    【DONE】后续应用工程API包的依赖管理
fall-platform-starter-cache-ehcache     【DONE】EhCache应用
fall-platform-starter-cache-mongodb
fall-platform-starter-cache-redis       【DONE】Redis应用
fall-platform-starter-cache-simple      【DONE】java缓存实现
fall-platform-starter-config            【DONE】系统全局配置
fall-platform-starter-control-dependency【DONE】后续应用工程CONTROL包的依赖管理
fall-platform-starter-core              【优化】核心包
fall-platform-starter-data              【优化】数据访问层，ORM，mybatisplus风爪昂
fall-platform-starter-doc                文件转换等
fall-platform-starter-elasticjob         elasticjob分布式任务调度
fall-platform-starter-elasticsearch      elasticsearch搜索引擎
fall-platform-starter-excel             【优化】Excel功能封装
fall-platform-starter-file              【优化】系统简单文件上传封装
fall-platform-starter-file-ali           ALI云文件服务器功能封装
fall-platform-starter-file-qiniu        【优化】七牛云文件服务器功能封装
fall-platform-starter-flyway             Flyway功能封装
fall-platform-starter-gateway            gateway网关功能封装
fall-platform-starter-graylog            graylog日志管理功能封装
fall-platform-starter-guard              webmvc功能封装，自己实现的安全框架，与fall-platform-starter-shiro/security类似
fall-platform-starter-httpClient         httpClient功能封装
fall-platform-starter-i18n               【DONE】I18N，中英、简繁翻译功能等
fall-platform-starter-mail               【DONE】邮件功能封装
fall-platform-starter-mq                 【DONE】MQ高可用幂等性策略实现封装设计
fall-platform-starter-mq-kafka
fall-platform-starter-mq-rabbit          【DONE】RabbitMq功能封装，整合fall-platform-starter-mq   
fall-platform-starter-mq-rocket
fall-platform-starter-pay                【TODO】微信支付、支付宝、银联支付功能封装
fall-platform-starter-rbac               【DONE】RBAC验证授权功能设计
fall-platform-starter-security           【DONE】基于RBAC的spring-security功能实现
fall-platform-starter-shiro              【DONE】基于RBAC的apache-shiro功能实现
fall-platform-starter-sms                【DONE】系统短信功能设计
fall-platform-starter-sms-aliyun         【DONE】阿里云SMS功能封装，整合fall-platform-starter-sms  
fall-platform-starter-social             【TODO】社交媒体相关功能整合
fall-platform-starter-task               【DONE】系统定时任务调度设计
fall-platform-starter-task-quartz        【DONE】quartz功能封装，整合fall-platform-starter-task  
fall-platform-starter-task-xxljob        【优化】xxljob功能封装，整合fall-platform-starter-task  
fall-platform-starter-uflo               【TODO】uflo工作流引擎功能封装
fall-platform-starter-ureport            【优化】ureport功能封装
fall-platform-starter-urule              【TODO】
fall-platform-starter-webflux            【TODO】
fall-platform-starter-webservice         【优化】
fall-platform-starter-websocket          【优化】
fall-platform-starter-wechat             【TODO】
fall-platform-starters                   【DONE】所有starter的父工程  
```

## 三、起步
选中 fall-platform-dependencies 右键
run maven -> install 将所有模块install到maven仓库，便于后续引入。
或者用到哪个模块直接install。【注意模块之间的层级关系，从最底层开始install】

## 四、开发
- 初始化脚本：doc/db/init.sql
- 启动rabbitmq服务端
- 启动redis

开发过程中，可以在项目 platform-demo 中进行功能测试。

