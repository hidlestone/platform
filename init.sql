-- 初始化脚本


-- 系统参数组/系统参数明细项 数据量较少，不使用ID
CREATE TABLE `s_sys_param_group` (
	`code` VARCHAR (50) NOT NULL COMMENT '系统参数组编码',
	`desc` VARCHAR (200) DEFAULT NULL COMMENT '系统参数组描述',
	`status` CHAR (1) NOT NULL DEFAULT '1' COMMENT '是否启用：0-停用，1-启用',
	`create_user_id` BIGINT (20) DEFAULT NULL COMMENT '创建用户ID',
	`modify_user_id` BIGINT (20) DEFAULT NULL COMMENT '修改用户ID',
	`gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
	PRIMARY KEY (`code`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统参数组表';

CREATE TABLE `s_sys_param_item` (
	`code` VARCHAR (50) NOT NULL COMMENT '系统参数编码',
	`value` VARCHAR (2000) DEFAULT NULL COMMENT '系统参数值',
	`encrypted_value` VARCHAR (2000) DEFAULT NULL COMMENT '加密值',
	`desc` VARCHAR (200) DEFAULT NULL COMMENT '系统参数描述',
	`status` CHAR (1) NOT NULL DEFAULT '1' COMMENT '是否启用：0-停用，1-启用',
	`group_code` VARCHAR (50) NOT NULL COMMENT '系统参数组编码',
	`create_user_id` BIGINT (20) DEFAULT NULL COMMENT '创建用户ID',
	`modify_user_id` BIGINT (20) DEFAULT NULL COMMENT '修改用户ID',
	`gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
	PRIMARY KEY (`code`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统参数明细项表';


CREATE TABLE `s_i18n_resource` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`lang_code` VARCHAR (10) NOT NULL COMMENT '语言编码',
	`resource_key` VARCHAR (150) NOT NULL COMMENT '资源key',
	`resource_value` text COMMENT '资源value',
	`module_code` VARCHAR (255) DEFAULT 'common' COMMENT '所属module',
	`create_user_id` BIGINT (20) DEFAULT NULL COMMENT '创建用户ID',
	`modify_user_id` BIGINT (20) DEFAULT NULL COMMENT '修改用户ID',
	`gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '国际化资源表';


CREATE TABLE `s_permission` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`permission_code` VARCHAR (255) NOT NULL COMMENT '权限编码',
	`permission_name` VARCHAR (255) DEFAULT NULL COMMENT '权限名称即接口名称',
	`resource_value` VARCHAR (255) DEFAULT NULL COMMENT '资源值即URL',
	`order_num` INT (4) DEFAULT NULL COMMENT '顺序',
	`authc_type` VARCHAR (255) DEFAULT 'auth' COMMENT '认证类型：anon(允许匿名访问)，auth(登录即可访问)',
	`create_user_id` BIGINT (20) DEFAULT NULL COMMENT '创建用户ID',
	`modify_user_id` BIGINT (20) DEFAULT NULL COMMENT '修改用户ID',
	`gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统接口权限表';


CREATE TABLE `s_mail_sender_config` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`from` VARCHAR (200) DEFAULT NULL COMMENT '发送者',
	`host` VARCHAR (200) DEFAULT NULL COMMENT '如：smtp.163.com',
	`username` VARCHAR (200) DEFAULT NULL COMMENT '账号',
	`password` VARCHAR (1000) DEFAULT NULL COMMENT '密码',
	`properties` VARCHAR (1000) DEFAULT NULL COMMENT '配置',
	`create_user_id` BIGINT (20) DEFAULT NULL COMMENT '创建用户ID',
	`modify_user_id` BIGINT (20) DEFAULT NULL COMMENT '修改用户ID',
	`gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件发送配置表';

CREATE TABLE `s_mail_template` (
	`code` VARCHAR (50) COMMENT '邮件模板配置编码',
	`desc` VARCHAR (200) DEFAULT NULL COMMENT '邮件模板配置描述',
	`title` VARCHAR (200) DEFAULT NULL COMMENT '标题',
	`from` VARCHAR (200) DEFAULT NULL COMMENT '发送者',
	`content` text COMMENT '内容',
	`file_group_id` BIGINT (20) DEFAULT NULL COMMENT '文件组ID',
	`retry_count` TINYINT (3) UNSIGNED DEFAULT NULL COMMENT '重试次数',
	`create_user_id` BIGINT (20) DEFAULT NULL COMMENT '创建用户ID',
	`modify_user_id` BIGINT (20) DEFAULT NULL COMMENT '修改用户ID',
	`gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
	PRIMARY KEY (`code`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件模板表';

CREATE TABLE `s_mail_history` (
	`id` BIGINT (20) NOT NULL COMMENT '主键',
	`title` VARCHAR (200) DEFAULT NULL COMMENT '标题',
	`from` VARCHAR (200) DEFAULT NULL COMMENT '发送者',
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '用户ID',
	`receiver` VARCHAR (1000) DEFAULT NULL COMMENT '接收者',
	`cc` VARCHAR (1000) DEFAULT NULL COMMENT '抄送者',
	`bcc` VARCHAR (1000) DEFAULT NULL COMMENT '密送者',
	`content` text COMMENT '内容',
	`file_group_id` BIGINT (20) DEFAULT NULL COMMENT '文件组ID',
	`try_count` TINYINT (3) UNSIGNED DEFAULT NULL COMMENT '发送次数',
	`last_send_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次发送时间',
	`send_flag` TINYINT (3) UNSIGNED DEFAULT NULL COMMENT '0-失败，1-成功',
	`create_user_id` BIGINT (20) DEFAULT NULL COMMENT '创建用户ID',
	`modify_user_id` BIGINT (20) DEFAULT NULL COMMENT '修改用户ID',
	`gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件发送历史表';




-- 待定
CREATE TABLE `s_mq_trace_record` (
	`id` BIGINT (20) NOT NULL COMMENT '主键',
	`stage` VARCHAR (30) DEFAULT NULL COMMENT '阶段',
	`content` text DEFAULT NULL COMMENT '消息体JSON',
	`exchange` VARCHAR (255) DEFAULT NULL COMMENT '交换机',
	`route_key` VARCHAR (255) DEFAULT NULL COMMENT '路由',
	`delivery_tag` VARCHAR (255) DEFAULT NULL COMMENT '投递TAG',
	`consumer_tag` VARCHAR (255) DEFAULT NULL COMMENT '消费TAG',
	`ack_mode` VARCHAR (30) DEFAULT NULL COMMENT 'ACK模式',
	`publish_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
	`consume_time` datetime DEFAULT NULL COMMENT '消费时间',
	`subscriber` VARCHAR (255) DEFAULT NULL COMMENT '订阅者',
	`create_user_id` BIGINT (20) DEFAULT NULL COMMENT '创建用户ID',
	`modify_user_id` BIGINT (20) DEFAULT NULL COMMENT '修改用户ID',
	`gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = 'MQ消息记录表';





















