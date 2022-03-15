# fall-platform-starter-elasticsearch

## 一、简介
集成 `spring-boot-starter-data-elasticsearch` 完成对 ElasticSearch 的高级使用技巧，包括创建索引、配置映射、删除索引、增删改查基本操作、复杂查询、高级查询、聚合查询等。

## 二、安装
ElasticSearch版本为 `6.5.3`，使用 docker 运行，下面是所有步骤：

1. 下载镜像：`docker pull elasticsearch:6.5.3`
2. 运行容器：`docker run -d -p 9200:9200 -p 9300:9300 --name elasticsearch-6.5.3 elasticsearch:6.5.3`
3. 进入容器：`docker exec -it elasticsearch-6.5.3 /bin/bash`
4. 安装 ik 分词器：`./bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v6.5.3/elasticsearch-analysis-ik-6.5.3.zip`
5. 修改 es 配置文件：`vi ./config/elasticsearch.yml









## 参考
1. 历史版本下载地址：https://www.elastic.co/cn/downloads/past-releases
2. ElasticSearch 官方文档：https://www.elastic.co/guide/en/elasticsearch/reference/6.x/getting-started.html
3. spring-data-elasticsearch 官方文档：https://docs.spring.io/spring-data/elasticsearch/docs/3.1.2.RELEASE/reference/html/



https://blog.csdn.net/qq_36364955/article/details/115838058

cnblogs.com/houchen/p/15126647.html
