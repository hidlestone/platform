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















-- 支付存储文件
CREATE TABLE `pay_cert_file` (
	`id` BIGINT (20) NOT NULL COMMENT '主键',
	`file_id` varchar(32) DEFAULT NULL COMMENT '文件ID',
  `data` mediumblob DEFAULT NULL COMMENT '文件数据',
  `name` varchar(100)DEFAULT NULL COMMENT '文件名称',
  `size` bigint(11) DEFAULT NULL COMMENT '文件大小',
  `file_type` varchar(50) DEFAULT NULL COMMENT '文件类型',
  `create_user_id` BIGINT (20) DEFAULT NULL COMMENT '创建用户ID',
	`modify_user_id` BIGINT (20) DEFAULT NULL COMMENT '修改用户ID',
	`gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '支付凭证文件';

-- 支付类型
CREATE TABLE `pay_type` (
  `code` varchar(255) DEFAULT NULL  COMMENT '支付类型编码',
  `name` varchar(255) DEFAULT NULL  COMMENT '支付类型名称',
  `desc` varchar(255) DEFAULT NULL COMMENT ' 支付描述',
  `status` tinyint(1) unsigned DEFAULT NULL COMMENT '状态',
  `create_user_id` BIGINT (20) DEFAULT NULL COMMENT '创建用户ID',
	`modify_user_id` BIGINT (20) DEFAULT NULL COMMENT '修改用户ID',
	`gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`code`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '支持支付类型表';

-- 微信支付配置信息
CREATE TABLE `pay_wechat_config` (
  `id` BIGINT (20) NOT NULL COMMENT '主键',
  `pay_type_code` varchar(255) DEFAULT NULL  COMMENT '支付类型编码',
  `encrypt_type` varchar(255) DEFAULT NULL  COMMENT '加密方式',
  `cert_file_id` varchar(32) DEFAULT NULL COMMENT '仅微信使用，凭证文件Id',
  `api_key` varchar(255) DEFAULT NULL COMMENT '仅微信使用，开通微信支付后，会把 微信支付的账号，密码，以及 apikey发给开发者。用于签名',
  `app_id` varchar(255) NOT NULL COMMENT '微信为公众账号Id，  支付宝为20开头的一串数字（管理中心-我的应用）',
  `mch_id` varchar(255) NOT NULL COMMENT '商户Id/合作伙伴Id， 例如 微信为12开头的一串数字(账户信息-微信支付商户号)，支付宝为（从我的应用-查看-使用者管理-使用者Id）',
  `status` tinyint(1) unsigned DEFAULT NULL COMMENT '状态',
	`gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '微信支付配置表';


CREATE TABLE `pay_ali_config` (
  `id` BIGINT (20) NOT NULL COMMENT '主键',
  `pay_type_code` varchar(255) DEFAULT NULL  COMMENT '支付类型编码',
  `pay_channel_desc` varchar(255) DEFAULT NULL COMMENT ' 支付描述，如：官方app支付， 扫码支付等',
  `sign_type` varchar(255) DEFAULT NULL  COMMENT '加密方式，如 MD5 微信,  RSA 支付宝',
  `cert_file_id` varchar(32) DEFAULT NULL COMMENT '仅微信使用，凭证文件Id，对应 FileResources',
  `api_key` varchar(255) DEFAULT NULL COMMENT '仅微信使用，开通微信支付后，会把 微信支付的账号，密码，以及 apikey发给开发者。用于签名',
  `app_id` varchar(255) NOT NULL COMMENT '微信为公众账号Id，  支付宝为20开头的一串数字（管理中心-我的应用）',
  `mch_id` varchar(255) NOT NULL COMMENT '商户Id/合作伙伴Id， 例如 微信为12开头的一串数字(账户信息-微信支付商户号)，支付宝为（从我的应用-查看-使用者管理-使用者Id）',
  `status` smallint(6) NOT NULL COMMENT '1正常，2 不可用',
  `mch_key` varchar(1024) DEFAULT NULL  COMMENT '商户私钥，  商户公钥需要在支付宝开放平台设置',
  `platform_key` varchar(1024) DEFAULT NULL COMMENT '支付宝公钥，  又支付宝开放平台提供',
  `query_channel_id` int(11) DEFAULT '0' COMMENT '对应PayChannel,0表示本身， 在调用支付宝查询订单(AliPayService.synchronize)功能时候，对应的开放平台秘钥Id。',
	`gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '支付宝配置表';


CREATE TABLE `pay_channel` (
 `id` BIGINT (20) NOT NULL COMMENT '主键',
 `pay_type_id` BIGINT (20) DEFAULT NULL COMMENT '支付类型ID',
  `pay_type_name` varchar(255) DEFAULT NULL  COMMENT '支付类型名称',
  `pay_type_code` varchar(255) DEFAULT NULL  COMMENT '支付类型编码',
  `pay_channel_desc` varchar(255) DEFAULT NULL COMMENT ' 支付描述，如：官方app支付， 扫码支付等',
  `sign_type` varchar(255) DEFAULT NULL  COMMENT '加密方式，如 MD5 微信,  RSA 支付宝',
  `cert_file_id` varchar(32) DEFAULT NULL COMMENT '仅微信使用，凭证文件Id，对应 FileResources',
  `api_key` varchar(255) DEFAULT NULL COMMENT '仅微信使用，开通微信支付后，会把 微信支付的账号，密码，以及 apikey发给开发者。用于签名',
  `app_id` varchar(255) NOT NULL COMMENT '微信为公众账号Id，  支付宝为20开头的一串数字（管理中心-我的应用）',
  `mch_id` varchar(255) NOT NULL COMMENT '商户Id/合作伙伴Id， 例如 微信为12开头的一串数字(账户信息-微信支付商户号)，支付宝为（从我的应用-查看-使用者管理-使用者Id）',
  `status` smallint(6) NOT NULL COMMENT '1正常，2 不可用',
  `mch_key` varchar(1024) DEFAULT NULL  COMMENT '商户私钥，  商户公钥需要在支付宝开放平台设置',
  `platform_key` varchar(1024) DEFAULT NULL COMMENT '支付宝公钥，  又支付宝开放平台提供',
  `query_channel_id` int(11) DEFAULT '0' COMMENT '对应PayChannel,0表示本身， 在调用支付宝查询订单(AliPayService.synchronize)功能时候，对应的开放平台秘钥Id。',
	`gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '支付存储文件';

-- 商户
DROP TABLE IF EXISTS `pay_merchant`;
CREATE TABLE `pay_merchant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `merchant_id` varchar(19) NOT NULL,
  `api_key` varchar(32) NOT NULL,
  `status` smallint(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `pay_merchant_channel`;
CREATE TABLE `pay_merchant_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_type_id` int(11) DEFAULT NULL,
  `pay_merchant_id` int(11) DEFAULT NULL,
  `pay_channel_id` int(11) NOT NULL,
  `trade_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pay_merchant_id` (`pay_merchant_id`),
  KEY `pay_channel_id` (`pay_channel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `pay_order`;
CREATE TABLE `pay_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_type_code` varchar(50) DEFAULT NULL,
  `pay_order_no` varchar(255) NOT NULL,
  `trade_pay_no` varchar(255) DEFAULT NULL,
  `pre_pay_id` varchar(100) DEFAULT NULL,
  `pay_id` varchar(100) DEFAULT NULL,
  `user_ip` varchar(50) DEFAULT NULL,
  `pay_amount` int(11) DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `error_code` varchar(32) DEFAULT NULL,
  `error_msg` varchar(128) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `open_id` varchar(255) DEFAULT NULL,
  `buyer_logon_id` varchar(255) DEFAULT NULL,
  `notify_url` varchar(255) DEFAULT NULL,
  `extra` varchar(100) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `detail` varchar(500) DEFAULT NULL,
  `code_url` varchar(255) DEFAULT NULL,
  `merchant_id` varchar(255) DEFAULT NULL,
  `trade_type` varchar(255) DEFAULT NULL,
  `return_url` varchar(255) DEFAULT NULL,
  `refund_amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `pay_order_no` (`pay_order_no`) USING BTREE,
  KEY `trade_pay_no` (`trade_pay_no`),
  KEY `pay_id` (`pay_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12478 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `refund_order`;
CREATE TABLE `refund_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_channel_id` int(255) DEFAULT NULL,
  `pay_type_code` varchar(255) DEFAULT NULL,
  `pay_order_no` varchar(255) DEFAULT NULL,
  `trade_pay_no` varchar(255) DEFAULT NULL,
  `refund_order_no` varchar(255) DEFAULT NULL,
  `trade_refund_no` varchar(255) DEFAULT NULL,
  `pay_id` varchar(255) DEFAULT NULL,
  `pay_amount` int(11) DEFAULT NULL,
  `refund_id` varchar(255) DEFAULT NULL,
  `refund_amount` int(11) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `error_code` varchar(255) DEFAULT NULL,
  `error_msg` varchar(255) DEFAULT NULL,
  `refund_time` datetime DEFAULT NULL,
  `merchant_id` varchar(255) DEFAULT NULL,
  `trade_type` varchar(255) DEFAULT NULL,
  `notify_url` varchar(255) DEFAULT NULL,
  `refund_reason` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `refund_order_no` (`refund_order_no`),
  UNIQUE KEY `trade_refund_no_mid` (`trade_refund_no`,`merchant_id`) USING BTREE,
  KEY `trade_pay_no` (`trade_pay_no`),
  KEY `pay_order_no` (`pay_order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=246 DEFAULT CHARSET=utf8;




