# fall-platform-starter-flyway

flyway数据库管理方案：
```
1、fall-platform-starter-flyway 实现扩展功能
2、flyway-maven-plugin 插件实现
当前使用 flyway-maven-plugin 的方式实现数据库的管理，后续有需要多租户的相关功能再扩展fall-platform-starter-flyway。
```

## 一、fall-platform-starter-flyway
后续实现
```
spring.flyway.baseline-description对执行迁移时基准版本的描述.
spring.flyway.baseline-on-migrate当迁移时发现目标schema非空，而且带有没有元数据的表时，是否自动执行基准迁移，默认false.
spring.flyway.baseline-version开始执行基准迁移时对现有的schema的版本打标签，默认值为1.
spring.flyway.check-location检查迁移脚本的位置是否存在，默认false.
spring.flyway.clean-on-validation-error当发现校验错误时是否自动调用clean，默认false.
spring.flyway.enabled是否开启flywary，默认true.
spring.flyway.encoding设置迁移时的编码，默认UTF-8.
spring.flyway.ignore-failed-future-migration当读取元数据表时是否忽略错误的迁移，默认false.
spring.flyway.init-sqls当初始化好连接时要执行的SQL.
spring.flyway.locations迁移脚本的位置，默认db/migration.
spring.flyway.out-of-order是否允许无序的迁移，默认false.
spring.flyway.password目标数据库的密码.
spring.flyway.placeholder-prefix设置每个placeholder的前缀，默认${.
spring.flyway.placeholder-replacementplaceholders是否要被替换，默认true.
spring.flyway.placeholder-suffix设置每个placeholder的后缀，默认}.
spring.flyway.placeholders.[placeholder name]设置placeholder的value
spring.flyway.schemas设定需要flywary迁移的schema，大小写敏感，默认为连接默认的schema.
spring.flyway.sql-migration-prefix迁移文件的前缀，默认为V.
spring.flyway.sql-migration-separator迁移脚本的文件名分隔符，默认__
spring.flyway.sql-migration-suffix迁移脚本的后缀，默认为.sql
spring.flyway.tableflyway使用的元数据表名，默认为schema_version
spring.flyway.target迁移时使用的目标版本，默认为latest version
spring.flyway.url迁移时使用的JDBC URL，如果没有指定的话，将使用配置的主数据源
spring.flyway.user迁移数据库的用户名
spring.flyway.validate-on-migrate迁移时是否校验，默认为true.
```

## 二、flyway-maven-plugin
application.yml
```yaml
spring:
  first-datasource:
    skip: false
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/clouddb01?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
    username: root
    password: root
    table: flyway-schema-history
    location: db/migration/clouddb01
  second-datasource:
    skip: false
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/clouddb02?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
    username: root
    password: root
    table: flyway-schema-history
    location: db/migration/clouddb02
```
pom.xml
```
<build>
    <plugins>
        <plugin>
            <groupId>it.ozimov</groupId>
            <artifactId>yaml-properties-maven-plugin</artifactId>
            <executions>
                <execution>
                    <phase>initialize</phase>
                    <goals>
                        <goal>read-project-properties</goal>
                    </goals>
                    <configuration>
                        <files>
                            <file>src/main/resources/application.yml</file>
                        </files>
                    </configuration>
                </execution>
            </executions>
        </plugin>
        <plugin>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-maven-plugin</artifactId>
            <executions>
                <execution>
                    <id>first-execution</id>
                    <phase>compile</phase> <!--whatever phase you need-->
                    <goals>
                        <goal>migrate</goal>
                    </goals>
                    <configuration>
                        <!-- 配置目标数据库连接 -->
                        <!--suppress UnresolvedMavenProperty -->
                        <driver>${spring.first-datasource.driver-class-name}</driver>
                        <!--suppress UnresolvedMavenProperty -->
                        <url>${spring.first-datasource.url}</url>
                        <!--suppress UnresolvedMavenProperty -->
                        <user>${spring.first-datasource.username}</user>
                        <!--suppress UnresolvedMavenProperty -->
                        <password>${spring.first-datasource.password}</password>
                        <!-- 配置数据库脚本位置 -->
                        <locations>
                            <!--suppress UnresolvedMavenProperty -->
                            <location>${spring.first-datasource.location}</location>
                        </locations>
                        <!--suppress UnresolvedMavenProperty -->
                        <table>${spring.first-datasource.table}</table>
                        <!--suppress UnresolvedMavenProperty -->
                        <skip>${spring.first-datasource.skip}</skip>
                        <!-- 设置sql脚本文件的编码 -->
                        <encoding>UTF-8</encoding>
                        <ignoreMissingMigrations>true</ignoreMissingMigrations>
                        <outOfOrder>true</outOfOrder>
                        <cleanDisabled>true</cleanDisabled>
                        <baselineOnMigrate>true</baselineOnMigrate>
                        <placeholderReplacement>false</placeholderReplacement>
                    </configuration>
                </execution>
                <execution>
                    <id>second-execution</id>
                    <phase>compile</phase> <!--whatever phase you need-->
                    <goals>
                        <goal>migrate</goal>
                    </goals>
                    <configuration>
                        <!-- 配置目标数据库连接 -->
                        <!--suppress UnresolvedMavenProperty -->
                        <driver>${spring.second-datasource.driver-class-name}</driver>
                        <!--suppress UnresolvedMavenProperty -->
                        <url>${spring.second-datasource.url}</url>
                        <!--suppress UnresolvedMavenProperty -->
                        <user>${spring.second-datasource.username}</user>
                        <!--suppress UnresolvedMavenProperty -->
                        <password>${spring.second-datasource.password}</password>
                        <!-- 配置数据库脚本位置 -->
                        <locations>
                            <!--suppress UnresolvedMavenProperty -->
                            <location>${spring.second-datasource.location}</location>
                        </locations>
                        <!--suppress UnresolvedMavenProperty -->
                        <table>${spring.second-datasource.table}</table>
                        <!--suppress UnresolvedMavenProperty -->
                        <skip>${spring.second-datasource.skip}</skip>
                        <!-- 设置sql脚本文件的编码 -->
                        <encoding>UTF-8</encoding>
                        <ignoreMissingMigrations>true</ignoreMissingMigrations>
                        <outOfOrder>true</outOfOrder>
                        <baselineOnMigrate>true</baselineOnMigrate>
                        <placeholderReplacement>false</placeholderReplacement>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

maven package 就会执行flywayd的迁移migrate。
