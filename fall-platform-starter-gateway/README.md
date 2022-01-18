## wordplay-platform-gateway

### 一、概述
搭建 wordplay-platform 平台网关。实现基础网关功能。

主要实现功能：
```
反向代理
全局日志记录 [TODO：集成ELK]
统一网关鉴权 [TODO]
报文结构转换

整合Hystrix其他功能 [TODO]
服务熔断  [TODO]
服务降级  
实时的监控  [TODO]

服务限流 [TODO]
```

### 二、文件配置说明：    
应用启动加载配置文件顺序：bootstrap.yml -> application.yml

bootstrap.yml
```yaml
spring:
  application: 
    name: wordplay-platform-gateway  # 应用名称
  main:
    allow-bean-definition-overriding: true  # 是否覆盖
```

application.yml
```yaml
spring:
  profiles:
    active:
      - local  # 使用的profile 
#------------------------------本机------------------------------#
---
server:
  port: 9527
spring:
  profiles: local
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true # 启用路由访问
      routes: # 后续根据系统的应用进行配置
        - id: path_route
          # 指定域名
          uri: http://localhost:8081
          predicates:
            - Path=/jar/**
          filters:
            # 熔断配置
            - name: Hystrix
              args:
                name: default
                fallbackUri: forward:/fallback
        - id: path_route2
          # 指定域名
          uri: http://localhost:8082
          predicates:
            - Path=/war/**
          filters:
            # 熔断配置
            - name: Hystrix
              args:
                name: hystrix1
                fallbackUri: forward:/fallback
  redis:
    database: 0
    host: localhost
    port: 6379
    password: 123456
    lettuce:
      pool:
        max-active: 300
        max-idle: 8
        max-wait: -1ms
        min-idle: 0
  session:
    store-type: redis
  mvc:
    throw-exception-if-no-handler-found: true

# 默认熔断超时时间30s
hystrix:
  command:  #用于控制HystrixCommand的行为
    default:
      execution:
        isolation:
          strategy: THREAD # 控制HystrixCommand的隔离策略，THREAD->线程池隔离策略(默认)，SEMAPHORE->信号量隔离策略
          thread:
            timeoutInMilliseconds: 3000  # 配置HystrixCommand执行的超时时间，执行超过该时间会进行服务降级处理
            interruptOnTimeout: true # 配置HystrixCommand执行超时的时候是否要中断
            interruptOnCancel: true # 配置HystrixCommand执行被取消的时候是否要中断
          timeout:
            enabled: true # 配置HystrixCommand的执行是否启用超时时间  
          semaphore:
            maxConcurrentRequests: 10 # 当使用信号量隔离策略时，用来控制并发量的大小，超过该并发量的请求会被拒绝  
          fallback:
            enabled: true # 用于控制是否启用服务降级  
        fallback:
          enabled: true # 用于控制是否启用服务降级   
        circuitBreaker: # 用于控制HystrixCircuitBreaker的行为  
          enabled: true # 用于控制断路器是否跟踪健康状况以及熔断请求
          requestVolumeThreshold: 20 #超过该请求数的请求会被拒绝
          forceOpen: false # 强制打开断路器，拒绝所有请求
          forceClosed: false # 强制关闭断路器，接收所有请求
        requestCache:
          enabled: true # 用于控制是否开启请求缓存  
  collapser: #用于控制HystrixCollapser的执行行为
    default:
      maxRequestsInBatch: 100 #控制一次合并请求合并的最大请求数
      timerDelayinMilliseconds: 10 #控制多少毫秒内的请求会被合并成一个
      requestCache:
        enabled: true #控制合并请求是否开启缓存        
  threadpool: #用于控制HystrixCommand执行所在线程池的行为
    default:
      coreSize: 10 #线程池的核心线程数
      maximumSize: 10 #线程池的最大线程数，超过该线程数的请求会被拒绝
      maxQueueSize: -1 #用于设置线程池的最大队列大小，-1采用SynchronousQueue，其他正数采用LinkedBlockingQueue
      queueSizeRejectionThreshold: 5 #用于设置线程池队列的拒绝阀值，由于LinkedBlockingQueue不能动态改版大小，使用时需要用该参数来控制线程数      
    hystrix1:
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 1000
# 报文转换配置
trans:
  url-list:
    - paths: /jar/api/cockpit
      content-type: application/json
      fields:
        # 新字段:老字段，若新老字段一致，可以只配置新字段
        - code:rs
        - msg:rsdesp
        - data:resultMessage
    - paths: /war/api/delivertool
      fields:
        - code:rs
        - msg:rsdesp
        - data:resultMessage


#------------------------------测试------------------------------#        
---
```

### 三、测试
记录的请求日志信息
```json
{
	"logType": "REQUEST",
	"level": "INFO",
	"appEnv": null,
	"applicationName": "wordplay-platform-gateway[dev]",
	"hostName": "ZHUANG",
	"ip": "0:0:0:0:0:0:0:1",
	"handleTime": null,
	"timeStamp": "2021-06-06T15:36:30+08:00",
	"requestUrl": "/jar/test",
	"userName": null,
	"account": null,
	"requestBody": null,
	"responseBody": null,
	"requestId": "851122386353917952",
	"requestMethod": "GET",
	"status": null,
	"serverIp": "10.50.109.109",
	"sessionId": "76f91135-cf0c-43c9-bbc9-2695d3850d11",
	"_class": null
}
```


### 四、其他
Gateway 和 Hystrix 整合   
https://blog.csdn.net/qq_43184345/article/details/111226005

