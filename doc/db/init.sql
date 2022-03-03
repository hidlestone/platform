/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : fallplatform

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2022-03-03 11:22:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for clientdetails
-- ----------------------------
DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE `clientdetails` (
  `appId` varchar(256) NOT NULL,
  `resourceIds` varchar(256) DEFAULT NULL,
  `appSecret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `grantTypes` varchar(256) DEFAULT NULL,
  `redirectUrl` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) DEFAULT NULL,
  `autoApproveScopes` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of clientdetails
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------

-- ----------------------------
-- Table structure for pay_ali_config
-- ----------------------------
DROP TABLE IF EXISTS `pay_ali_config`;
CREATE TABLE `pay_ali_config` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `pay_type_code` varchar(255) DEFAULT NULL COMMENT '支付类型编码',
  `encrypt_type` varchar(255) DEFAULT NULL COMMENT '加密方式',
  `app_id` varchar(255) NOT NULL COMMENT '支付宝为20开头的一串数字（管理中心-我的应用）',
  `mch_id` varchar(255) NOT NULL COMMENT '支付宝为（从我的应用-查看-使用者管理-使用者Id）',
  `status` tinyint(1) unsigned DEFAULT NULL COMMENT '状态',
  `private_key` varchar(1024) DEFAULT NULL COMMENT '商户私钥，商户公钥需要在支付宝开放平台设置，',
  `public_key` varchar(1024) DEFAULT NULL COMMENT '支付宝公钥，由支付宝开放平台提供',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付宝支付配置';

-- ----------------------------
-- Records of pay_ali_config
-- ----------------------------

-- ----------------------------
-- Table structure for pay_cert_file
-- ----------------------------
DROP TABLE IF EXISTS `pay_cert_file`;
CREATE TABLE `pay_cert_file` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `data` mediumblob COMMENT '文件数据',
  `name` varchar(100) DEFAULT NULL COMMENT '文件名称',
  `size` bigint(11) DEFAULT NULL COMMENT '文件大小',
  `file_type` varchar(50) DEFAULT NULL COMMENT '文件类型',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付凭证文件';

-- ----------------------------
-- Records of pay_cert_file
-- ----------------------------

-- ----------------------------
-- Table structure for pay_order
-- ----------------------------
DROP TABLE IF EXISTS `pay_order`;
CREATE TABLE `pay_order` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `pay_type_code` varchar(50) DEFAULT NULL COMMENT '支付类型：PayTypeCode',
  `pay_order_no` varchar(255) NOT NULL COMMENT '业务方，支付订单号',
  `trade_pay_no` varchar(255) DEFAULT NULL COMMENT '微信/支付宝的商户交易流水号',
  `pre_pay_id` varchar(100) DEFAULT NULL COMMENT '微信/支付宝返回的给app或者网页的支付凭证，客户端通过此信息调起支付界面。',
  `pay_id` varchar(100) DEFAULT NULL COMMENT '微信/支付宝的商户交易流水号',
  `user_ip` varchar(50) DEFAULT NULL COMMENT '微信/支付宝的商户交易流水号',
  `pay_amount` int(11) DEFAULT NULL COMMENT '支付金额，精确到分',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `status` smallint(6) DEFAULT NULL COMMENT '支付状态：PayProcessStatus',
  `error_code` varchar(32) DEFAULT NULL COMMENT '如果创建订单失败，则保存第三方返回的失败错误码',
  `error_msg` varchar(128) DEFAULT NULL COMMENT '错误信息',
  `start_time` datetime DEFAULT NULL COMMENT '支付申请时间',
  `expire_time` datetime DEFAULT NULL COMMENT '支付过期时间，默认为2小时',
  `open_id` varchar(255) DEFAULT NULL COMMENT '微信为用户的openId，支付宝为buyer_id买家支付宝用户号',
  `buyer_logon_id` varchar(255) DEFAULT NULL COMMENT '支付宝中：买家支付宝账号',
  `notify_url` varchar(255) DEFAULT NULL COMMENT '回调业务方的url',
  `extra` varchar(100) DEFAULT NULL COMMENT '附加信息，支付完成后通知时候会原封不动返回业务方',
  `subject` varchar(100) DEFAULT NULL COMMENT '订单标题，微信中对应body字段',
  `detail` varchar(500) DEFAULT NULL COMMENT '订单描述，微信中对应detail字段，为json格式。支付宝中对应body字段，表示描述',
  `code_url` varchar(255) DEFAULT NULL COMMENT '二维码链接',
  `merchant_id` varchar(255) DEFAULT NULL COMMENT '业务方商户号：PayMerchant',
  `trade_type` varchar(255) DEFAULT NULL COMMENT '支付类型，如扫码、app支付、wap支付等：TradeTypeCode',
  `return_url` varchar(255) DEFAULT NULL COMMENT '支付成功页，支付宝：页面跳转同步通知页面路径，需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问。微信需要在前端自己设置',
  `refund_amount` int(11) DEFAULT NULL COMMENT '退款额度，精确到分',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pay_order_no` (`pay_order_no`) USING BTREE,
  KEY `trade_pay_no` (`trade_pay_no`),
  KEY `pay_id` (`pay_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付订单';

-- ----------------------------
-- Records of pay_order
-- ----------------------------

-- ----------------------------
-- Table structure for pay_type
-- ----------------------------
DROP TABLE IF EXISTS `pay_type`;
CREATE TABLE `pay_type` (
  `code` varchar(255) NOT NULL COMMENT '支付类型编码',
  `name` varchar(255) DEFAULT NULL COMMENT '支付类型名称',
  `desc` varchar(255) DEFAULT NULL COMMENT ' 支付描述',
  `status` tinyint(1) unsigned DEFAULT NULL COMMENT '状态',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支持支付类型表';

-- ----------------------------
-- Records of pay_type
-- ----------------------------

-- ----------------------------
-- Table structure for pay_wechat_config
-- ----------------------------
DROP TABLE IF EXISTS `pay_wechat_config`;
CREATE TABLE `pay_wechat_config` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `pay_type_code` varchar(255) DEFAULT NULL COMMENT '支付类型编码：PayTypeEnum',
  `encrypt_type` varchar(255) DEFAULT NULL COMMENT '加密类型：EncryptTypeEnum',
  `cert_file_id` varchar(32) DEFAULT NULL COMMENT '仅微信使用，凭证文件Id',
  `api_key` varchar(255) DEFAULT NULL COMMENT '仅微信使用，开通微信支付后，会把微信支付的账号，密码，以及apikey发给开发者用于签名',
  `app_id` varchar(255) NOT NULL COMMENT '微信为公众账号Id',
  `mch_id` varchar(255) NOT NULL COMMENT '商户Id/合作伙伴Id，例如微信为12开头的一串数字(账户信息-微信支付商户号)',
  `status` tinyint(1) unsigned DEFAULT NULL COMMENT '状态',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信支付配置';

-- ----------------------------
-- Records of pay_wechat_config
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '调度名称',
  `TRIGGER_NAME` varchar(190) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `TRIGGER_GROUP` varchar(190) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `BLOB_DATA` blob COMMENT '一个blob字段，存放持久化Trigger对象',
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Trigger 作为 Blob 类型存储(用于 Quartz 用户用 JDBC 创建他们自己定制的 Trigger 类型，JobStore 并不知道如何存储实例的时候)';

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '调度名称',
  `CALENDAR_NAME` varchar(190) NOT NULL COMMENT '日历名称',
  `CALENDAR` blob NOT NULL COMMENT '一个blob字段，存放持久化calendar对象',
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='以 Blob 类型存储存放日历信息， quartz可配置一个日历来指定一个时间范围';

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '调度名称',
  `TRIGGER_NAME` varchar(190) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `TRIGGER_GROUP` varchar(190) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `CRON_EXPRESSION` varchar(120) NOT NULL COMMENT 'cron表达式',
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL COMMENT '时区',
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='触发器的cron表达式表';

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('schedulerFactoryBean', 'testName', 'testGroup', '0 */1 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '调度名称',
  `ENTRY_ID` varchar(95) NOT NULL COMMENT '调度器实例id',
  `TRIGGER_NAME` varchar(190) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `TRIGGER_GROUP` varchar(190) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `INSTANCE_NAME` varchar(190) NOT NULL COMMENT '调度器实例名',
  `FIRED_TIME` bigint(13) NOT NULL COMMENT '触发的时间',
  `SCHED_TIME` bigint(13) NOT NULL COMMENT '定时器制定的时间',
  `PRIORITY` int(11) NOT NULL COMMENT '优先级',
  `STATE` varchar(16) NOT NULL COMMENT '状态',
  `JOB_NAME` varchar(190) DEFAULT NULL COMMENT '集群中job的名字',
  `JOB_GROUP` varchar(190) DEFAULT NULL COMMENT '集群中job的所属组的名字',
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL COMMENT '是否并发',
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL COMMENT '是否接受恢复执行，默认为false，设置了RequestsRecovery为true，则会被重新执行',
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='存储与已触发的 Trigger 相关的状态信息，以及相联 Job 的执行信息';

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------
INSERT INTO `qrtz_fired_triggers` VALUES ('schedulerFactoryBean', 'ZHUANG16430131083301643013108318', 'testName', 'testGroup', 'ZHUANG1643013108330', '1643013296074', '1643013300000', '5', 'ACQUIRED', null, null, '0', '0');

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '调度名称',
  `JOB_NAME` varchar(190) NOT NULL COMMENT '集群中job的名字',
  `JOB_GROUP` varchar(190) NOT NULL COMMENT '集群中job的所属组的名字',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT '详细描述信息',
  `JOB_CLASS_NAME` varchar(250) NOT NULL COMMENT '集群中个notejob实现类的全限定名，quartz就是根据这个路径到classpath找到该job类',
  `IS_DURABLE` varchar(1) NOT NULL COMMENT '是否持久化，把该属性设置为1，quartz会把job持久化到数据库中',
  `IS_NONCONCURRENT` varchar(1) NOT NULL COMMENT '是否并发执行',
  `IS_UPDATE_DATA` varchar(1) NOT NULL COMMENT '是否更新数据',
  `REQUESTS_RECOVERY` varchar(1) NOT NULL COMMENT '是否接受恢复执行，默认为false，设置了RequestsRecovery为true，则该job会被重新执行',
  `JOB_DATA` blob COMMENT '存放持久化job对象',
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='已配置任务详细信息表';

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('schedulerFactoryBean', 'testName', 'testGroup', '测试工作任务内容述说明', 'com.fallframework.platform.test.starters.quartz.TestJob', '0', '1', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '调度名称',
  `LOCK_NAME` varchar(40) NOT NULL COMMENT '存储程序的悲观锁的信息(假如使用了悲观锁)',
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='存储程序的悲观锁的信息(假如使用了悲观锁)';

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('schedulerFactoryBean', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('schedulerFactoryBean', 'TRIGGER_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('SpringClusterTest', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('SpringClusterTest', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '调度名称',
  `TRIGGER_GROUP` varchar(190) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='存储已暂停的 Trigger 组的信息表';

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '调度名称',
  `INSTANCE_NAME` varchar(190) NOT NULL COMMENT '之前配置文件中org.quartz.scheduler.instanceId配置的名字，就会写入该字段',
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL COMMENT '上次检查时间',
  `CHECKIN_INTERVAL` bigint(13) NOT NULL COMMENT '检查间隔时间',
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='存储集群中note实例信息，quartz会定时读取该表的信息判断集群中每个实例的当前状态';

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('schedulerFactoryBean', 'ZHUANG1643013108330', '1643013291234', '7500');
INSERT INTO `qrtz_scheduler_state` VALUES ('SpringClusterTest', 'ZHUANG1643073662106', '1643073816103', '7500');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '调度名称',
  `TRIGGER_NAME` varchar(190) NOT NULL COMMENT 'qrtz_triggers表trigger_ name的外键',
  `TRIGGER_GROUP` varchar(190) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `REPEAT_COUNT` bigint(7) NOT NULL COMMENT '重复的次数统计',
  `REPEAT_INTERVAL` bigint(12) NOT NULL COMMENT '	重复的间隔时间',
  `TIMES_TRIGGERED` bigint(10) NOT NULL COMMENT '已经触发的次数',
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='简单触发器信息表';

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '调度名称',
  `TRIGGER_NAME` varchar(190) NOT NULL COMMENT 'qrtz_triggers表trigger_ name的外键',
  `TRIGGER_GROUP` varchar(190) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `STR_PROP_1` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第一个参数',
  `STR_PROP_2` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第二个参数',
  `STR_PROP_3` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第三个参数',
  `INT_PROP_1` int(11) DEFAULT NULL COMMENT 'int类型的trigger的第一个参数',
  `INT_PROP_2` int(11) DEFAULT NULL COMMENT 'int类型的trigger的第二个参数',
  `LONG_PROP_1` bigint(20) DEFAULT NULL COMMENT 'long类型的trigger的第一个参数',
  `LONG_PROP_2` bigint(20) DEFAULT NULL COMMENT 'long类型的trigger的第二个参数',
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL COMMENT 'decimal类型的trigger的第一个参数',
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL COMMENT 'decimal类型的trigger的第二个参数',
  `BOOL_PROP_1` varchar(1) DEFAULT NULL COMMENT 'Boolean类型的trigger的第一个参数',
  `BOOL_PROP_2` varchar(1) DEFAULT NULL COMMENT 'Boolean类型的trigger的第二个参数',
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='存储CalendarIntervalTrigger和DailyTimeIntervalTrigger表';

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '调度名称',
  `TRIGGER_NAME` varchar(190) NOT NULL COMMENT '触发器的名字',
  `TRIGGER_GROUP` varchar(190) NOT NULL COMMENT '触发器所属组的名字',
  `JOB_NAME` varchar(190) NOT NULL COMMENT 'qrtz_job_details表job_name的外键',
  `JOB_GROUP` varchar(190) NOT NULL COMMENT 'qrtz_job_details表job_group的外键',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT '详细描述信息',
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL COMMENT '下一次触发时间，默认为-1，意味不会自动触发',
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL COMMENT '上一次触发时间（毫秒）',
  `PRIORITY` int(11) DEFAULT NULL COMMENT '优先级',
  `TRIGGER_STATE` varchar(16) NOT NULL COMMENT '当前触发器状态，设置为ACQUIRED,如果设置为WAITING，则job不会触发（WAITING:等待，PAUSED:暂停，ACQUIRED:正常执行，BLOCKED：阻塞，ERROR：错误）',
  `TRIGGER_TYPE` varchar(8) NOT NULL COMMENT '触发器的类型，使用cron表达式',
  `START_TIME` bigint(13) NOT NULL COMMENT '开始时间',
  `END_TIME` bigint(13) DEFAULT NULL COMMENT '结束时间',
  `CALENDAR_NAME` varchar(190) DEFAULT NULL COMMENT '日程表名称，表qrtz_calendars的calendar_name字段外键',
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL COMMENT '日程表名称，表qrtz_calendars的calendar_name字段外键',
  `JOB_DATA` blob COMMENT '存放持久化job对象',
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='触发器基本信息表';

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('schedulerFactoryBean', 'testName', 'testGroup', 'testName', 'testGroup', '触发器描述说明', '1643013300000', '1643013240000', '5', 'ACQUIRED', 'CRON', '1643010521000', '0', null, '2', '');

-- ----------------------------
-- Table structure for s_file_group
-- ----------------------------
DROP TABLE IF EXISTS `s_file_group`;
CREATE TABLE `s_file_group` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` tinyint(1) DEFAULT '1' COMMENT '文件组状态',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件组信息表';

-- ----------------------------
-- Records of s_file_group
-- ----------------------------
INSERT INTO `s_file_group` VALUES ('1484459087419056129', '一个测试的文件组', '1', null, null, '2022-01-21 17:33:22', null);
INSERT INTO `s_file_group` VALUES ('1486897758764199937', '测试文件组', '1', null, null, '2022-01-28 11:03:45', null);

-- ----------------------------
-- Table structure for s_file_info
-- ----------------------------
DROP TABLE IF EXISTS `s_file_info`;
CREATE TABLE `s_file_info` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `file_group_id` bigint(20) DEFAULT NULL COMMENT '文件组ID',
  `name` varchar(500) DEFAULT NULL COMMENT '文件名',
  `nonsense_name` varchar(255) DEFAULT NULL COMMENT '无意义名称',
  `extension` varchar(64) DEFAULT NULL COMMENT '文件扩展名',
  `file_type` tinyint(1) DEFAULT NULL COMMENT '文件类型',
  `storage_type` tinyint(1) DEFAULT NULL COMMENT '存储类型',
  `content_type` varchar(255) DEFAULT NULL COMMENT 'contentType',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `url` varchar(2000) DEFAULT NULL COMMENT '文件url',
  `category` varchar(64) DEFAULT NULL COMMENT '文件业务类型',
  `status` tinyint(1) DEFAULT '1' COMMENT '文件状态',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件信息表';

-- ----------------------------
-- Records of s_file_info
-- ----------------------------
INSERT INTO `s_file_info` VALUES ('1484459169493196802', '1484459087419056129', '测试文件1.png', null, 'png', null, null, null, 'demoData', 'demoData', 'demoData', '1', null, null, '2022-01-21 17:33:40', '2022-01-27 10:00:13');
INSERT INTO `s_file_info` VALUES ('1484459169543528450', '1484459087419056129', '测试文件2.png', null, 'png', null, null, null, 'demoData', 'demoData', 'demoData', '1', null, null, '2022-01-21 17:33:40', '2022-01-27 10:00:13');
INSERT INTO `s_file_info` VALUES ('1486897759716306945', '1486897758764199937', '102 成功.XML', '56354189-6020-40f6-abc0-5d5385de85ee.XML', 'XML', null, null, 'application/xml', null, null, null, '1', null, null, '2022-01-28 11:03:45', null);
INSERT INTO `s_file_info` VALUES ('1486897759728889857', '1486897758764199937', '122 成功.XML', '106bd318-30e3-4aec-a4d3-7e79aa6bce8a.XML', 'XML', null, null, 'application/xml', null, null, null, '1', null, null, '2022-01-28 11:03:45', null);

-- ----------------------------
-- Table structure for s_i18n_resource
-- ----------------------------
DROP TABLE IF EXISTS `s_i18n_resource`;
CREATE TABLE `s_i18n_resource` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `lang_code` varchar(10) NOT NULL COMMENT '语言编码',
  `resource_key` varchar(150) NOT NULL COMMENT '资源key',
  `resource_value` text COMMENT '资源value',
  `module_code` varchar(255) DEFAULT 'common' COMMENT '所属module',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='国际化资源表';

-- ----------------------------
-- Records of s_i18n_resource
-- ----------------------------
INSERT INTO `s_i18n_resource` VALUES ('1', 'en', 'returnStock.valid.bookkeepingflag.isBlank', 'Bookkeeping label cannot be empty, please check.', 'Common', null, null, '2022-01-19 21:40:46', null);
INSERT INTO `s_i18n_resource` VALUES ('2', 'zh_CN', 'returnStock.valid.bookkeepingflag.isBlank', '记账标识不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:46', null);
INSERT INTO `s_i18n_resource` VALUES ('3', 'zh_TW', 'returnStock.valid.bookkeepingflag.isBlank', '記賬標識不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('4', 'en', 'returnStock.valid.certificatedate.isBlank', 'Voucher date cannot be empty, please check.', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('5', 'zh_CN', 'returnStock.valid.certificatedate.isBlank', '凭证日期不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('6', 'zh_TW', 'returnStock.valid.certificatedate.isBlank', '憑證日期不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('7', 'en', 'returnStock.valid.customercode.isBlank', 'Customer code cannot be empty, please check.', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('8', 'zh_CN', 'returnStock.valid.customercode.isBlank', '客户编码不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('9', 'zh_TW', 'returnStock.valid.customercode.isBlank', '客戶編碼不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('10', 'en', 'returnStock.valid.customername.isBlank', 'Customer name cannot be empty, please check.', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('11', 'zh_CN', 'returnStock.valid.customername.isBlank', '客户名称不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('12', 'zh_TW', 'returnStock.valid.customername.isBlank', '客戶名稱不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('13', 'en', 'returnStock.valid.directdeliveryflag.isBlank', 'The direct send identifier cannot be empty. Please check.', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('14', 'zh_CN', 'returnStock.valid.directdeliveryflag.isBlank', '直送标识不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('15', 'zh_TW', 'returnStock.valid.directdeliveryflag.isBlank', '直送標識不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('16', 'en', 'returnStock.valid.postingdate.isBlank', 'Posting date is not allowed to be blank, please check.', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('17', 'zh_CN', 'returnStock.valid.postingdate.isBlank', '过账日期不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('18', 'zh_TW', 'returnStock.valid.postingdate.isBlank', '過賬日期不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('19', 'en', 'returnStock.valid.returnstockno.isBlank', 'Return receipt number cannot be empty, please check.', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('20', 'zh_CN', 'returnStock.valid.returnstockno.isBlank', '退库单号不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('21', 'zh_TW', 'returnStock.valid.returnstockno.isBlank', '退庫單號不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('22', 'en', 'returnStock.valid.returnstocktypecode.isBlank', 'Return type code cannot be empty, please check.', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('23', 'zh_CN', 'returnStock.valid.returnstocktypecode.isBlank', '退库类型编码不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('24', 'zh_TW', 'returnStock.valid.returnstocktypecode.isBlank', '退庫類型編碼不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('25', 'en', 'returnStock.valid.sourcebilltypecode.isBlank', 'The source document type code cannot be empty, please check.', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('26', 'zh_CN', 'returnStock.valid.sourcebilltypecode.isBlank', '来源单据类型编码不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('27', 'zh_TW', 'returnStock.valid.sourcebilltypecode.isBlank', '來源單據類型編碼不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('28', 'en', 'returnStock.valid.supplementaryflag.isBlank', 'Fill mark cannot be empty, please check.', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('29', 'zh_CN', 'returnStock.valid.supplementaryflag.isBlank', '补录标识不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('30', 'zh_TW', 'returnStock.valid.supplementaryflag.isBlank', '補錄標識不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('31', 'en', 'returnStockDtl.valid.actualReturnQty.isBlank', 'The actual returned quantity cannot be empty, please check', 'Common', null, null, '2022-01-19 21:40:47', null);
INSERT INTO `s_i18n_resource` VALUES ('32', 'zh_CN', 'returnStockDtl.valid.actualReturnQty.isBlank', '实际退库数量不允许为空，请检查', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('33', 'zh_TW', 'returnStockDtl.valid.actualReturnQty.isBlank', '實際退庫數量不允許為空，請檢查', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('34', 'en', 'returnStockDtl.valid.baseunitcode.isBlank', 'Base unit codes are not allowed to be null, please check.', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('35', 'zh_CN', 'returnStockDtl.valid.baseunitcode.isBlank', '基本单位编码不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('36', 'zh_TW', 'returnStockDtl.valid.baseunitcode.isBlank', '基本單位編碼不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('37', 'en', 'returnStockDtl.valid.batchNo.isBlank', 'The batch number is not allowed to be empty. Please check.', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('38', 'zh_CN', 'returnStockDtl.valid.batchNo.isBlank', '批次号不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('39', 'zh_TW', 'returnStockDtl.valid.batchNo.isBlank', '批次號不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('40', 'en', 'returnStockDtl.valid.labelQty.isBlank', 'The number of labels cannot be empty. Please check.', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('41', 'zh_CN', 'returnStockDtl.valid.labelQty.isBlank', '标签数量不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('42', 'zh_TW', 'returnStockDtl.valid.labelQty.isBlank', '標簽數量不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('43', 'en', 'returnStockDtl.valid.labelReturnQty.isBlank', 'Label returnable quantity cannot be empty, please check.', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('44', 'zh_CN', 'returnStockDtl.valid.labelReturnQty.isBlank', '标签可退库数量不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('45', 'zh_TW', 'returnStockDtl.valid.labelReturnQty.isBlank', '標簽可退庫數量不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('46', 'en', 'returnStockDtl.valid.labelType.isBlank', 'The label type cannot be empty. Check it.', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('47', 'zh_CN', 'returnStockDtl.valid.labelType.isBlank', '标签类型不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('48', 'zh_TW', 'returnStockDtl.valid.labelType.isBlank', '標簽類型不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('49', 'en', 'returnStockDtl.valid.lableCode.isBlank', 'Tag code cannot be empty. Please check.', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('50', 'zh_CN', 'returnStockDtl.valid.lableCode.isBlank', '标签编码不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('51', 'zh_TW', 'returnStockDtl.valid.lableCode.isBlank', '標簽編碼不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('52', 'en', 'returnStockDtl.valid.materialcode.isBlank', 'The BOM number cannot be empty. Check it.', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('53', 'zh_CN', 'returnStockDtl.valid.materialcode.isBlank', '物料编码不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('54', 'zh_TW', 'returnStockDtl.valid.materialcode.isBlank', '物料編碼不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('55', 'en', 'returnStockDtl.valid.materialname.isBlank', 'The material name cannot be empty. Please check.', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('56', 'zh_CN', 'returnStockDtl.valid.materialname.isBlank', '物料名称不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('57', 'zh_TW', 'returnStockDtl.valid.materialname.isBlank', '物料名稱不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('58', 'en', 'returnStockDtl.valid.outbounddeliveryno.isBlank', 'Outgoing delivery order number is not allowed to be empty, please check.', 'Common', null, null, '2022-01-19 21:40:48', null);
INSERT INTO `s_i18n_resource` VALUES ('59', 'zh_CN', 'returnStockDtl.valid.outbounddeliveryno.isBlank', '外向交货单号不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('60', 'zh_TW', 'returnStockDtl.valid.outbounddeliveryno.isBlank', '外向交貨單號不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('61', 'en', 'returnStockDtl.valid.outbounddeliveryno.isBlank02', 'An entry', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('62', 'zh_CN', 'returnStockDtl.valid.outbounddeliveryno.isBlank02', '一个词条', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('63', 'zh_TW', 'returnStockDtl.valid.outbounddeliveryno.isBlank02', '一個詞條', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('64', 'en', 'returnStockDtl.valid.outbounddeliveryrowno.isBlank', 'Outgoing delivery line number is not allowed to be empty, please check.', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('65', 'zh_CN', 'returnStockDtl.valid.outbounddeliveryrowno.isBlank', '外向交货单行号不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('66', 'zh_TW', 'returnStockDtl.valid.outbounddeliveryrowno.isBlank', '外向交貨單行號不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('67', 'en', 'returnStockDtl.valid.outstockno.isBlank', 'The outbound order number is not allowed to be empty, please check.', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('68', 'zh_CN', 'returnStockDtl.valid.outstockno.isBlank', '出库单号不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('69', 'zh_TW', 'returnStockDtl.valid.outstockno.isBlank', '出庫單號不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('70', 'en', 'returnStockDtl.valid.outstockrowno.isBlank', 'The outbound line number is not allowed to be empty, please check.', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('71', 'zh_CN', 'returnStockDtl.valid.outstockrowno.isBlank', '出库单行号不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('72', 'zh_TW', 'returnStockDtl.valid.outstockrowno.isBlank', '出庫單行號不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('73', 'en', 'returnStockDtl.valid.plantcode.isBlank', 'Factory code cannot be empty, please check.', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('74', 'zh_CN', 'returnStockDtl.valid.plantcode.isBlank', '工厂编码不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('75', 'zh_TW', 'returnStockDtl.valid.plantcode.isBlank', '工廠編碼不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('76', 'en', 'returnStockDtl.valid.plantname.isBlank', 'Factory name cannot be empty, please check.', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('77', 'zh_CN', 'returnStockDtl.valid.plantname.isBlank', '工厂名称不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('78', 'zh_TW', 'returnStockDtl.valid.plantname.isBlank', '工廠名稱不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('79', 'en', 'returnStockDtl.valid.prebaseqty.isBlank', 'Pre-order quantity (basic unit) is not allowed to be empty, please check.', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('80', 'zh_CN', 'returnStockDtl.valid.prebaseqty.isBlank', '前序订单数量(基本单位)不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('81', 'zh_TW', 'returnStockDtl.valid.prebaseqty.isBlank', '前序訂單數量(基本單位)不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('82', 'en', 'returnStockDtl.valid.prebasereturnqty.isBlank', 'Pre-order returnable quantity (basic unit) is not allowed to be empty, please check.', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('83', 'zh_CN', 'returnStockDtl.valid.prebasereturnqty.isBlank', '前序订单可退库数量(基本单位)不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('84', 'zh_TW', 'returnStockDtl.valid.prebasereturnqty.isBlank', '前序訂單可退庫數量(基本單位)不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('85', 'en', 'returnStockDtl.valid.returnstockno.isBlank', 'Return receipt number cannot be empty, please check.', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('86', 'zh_CN', 'returnStockDtl.valid.returnstockno.isBlank', '退库单号不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('87', 'zh_TW', 'returnStockDtl.valid.returnstockno.isBlank', '退庫單號不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:49', null);
INSERT INTO `s_i18n_resource` VALUES ('88', 'en', 'returnStockDtl.valid.rowno.isBlank', 'The line number cannot be empty. Please check.', 'Common', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('89', 'zh_CN', 'returnStockDtl.valid.rowno.isBlank', '行号不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('90', 'zh_TW', 'returnStockDtl.valid.rowno.isBlank', '行號不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('91', 'en', 'returnStockDtl.valid.skuqty.isBlank', 'This return quantity (basic unit quantity) is not allowed to be empty, please check.', 'Common', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('92', 'zh_CN', 'returnStockDtl.valid.skuqty.isBlank', '本次退库数量（基本单位数量）不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('93', 'zh_TW', 'returnStockDtl.valid.skuqty.isBlank', '本次退庫數量（基本單位數量）不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('94', 'en', 'returnStockDtl.valid.stocklocationcode.isBlank', 'Stock location code is not allowed to be empty, please check.', 'Common', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('95', 'zh_CN', 'returnStockDtl.valid.stocklocationcode.isBlank', '库存地点编码不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('96', 'zh_TW', 'returnStockDtl.valid.stocklocationcode.isBlank', '庫存地點編碼不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('97', 'en', 'returnStockDtl.valid.stocklocationname.isBlank', 'Inventory location name cannot be empty, please check.', 'Common', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('98', 'zh_CN', 'returnStockDtl.valid.stocklocationname.isBlank', '库存地点名称不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('99', 'zh_TW', 'returnStockDtl.valid.stocklocationname.isBlank', '庫存地點名稱不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('100', 'en', 'returnStockDtl.valid.warehousecode.isBlank', 'Warehouse code cannot be empty, please check.', 'Common', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('101', 'zh_CN', 'returnStockDtl.valid.warehousecode.isBlank', '仓库编码不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('102', 'zh_TW', 'returnStockDtl.valid.warehousecode.isBlank', '倉庫編碼不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('103', 'en', 'returnStockDtl.valid.warehousename.isBlank', 'Warehouse name cannot be empty, please check.', 'Common', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('104', 'zh_CN', 'returnStockDtl.valid.warehousename.isBlank', '仓库名称不允许为空，请检查。', 'Common', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('105', 'zh_TW', 'returnStockDtl.valid.warehousename.isBlank', '倉庫名稱不允許為空，請檢查。', 'Common', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('106', 'en', 'WM.tip.retrunStock.returnStockIsDraftCheck', 'Only documents in draft state can be edited', 'WM', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('107', 'zh_CN', 'WM.tip.retrunStock.returnStockIsDraftCheck', '只有状态为草稿的单据才可以编辑', 'WM', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('108', 'zh_TW', 'WM.tip.retrunStock.returnStockIsDraftCheck', '只有狀態為草稿的單據才可以編輯', 'WM', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('109', 'en', 'WM.tip.retrunStock.returnStockIsExistCheck', 'Bill {0} does not exist, please check', 'WM', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('110', 'zh_CN', 'WM.tip.retrunStock.returnStockIsExistCheck', '单据{0}不存在，请检查', 'WM', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('111', 'zh_TW', 'WM.tip.retrunStock.returnStockIsExistCheck', '單據{0}不存在，請檢查', 'WM', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('112', 'en', 'WM.tip.returnStock.allocateCanNotReturnRepeated', 'Disbursement order A disbursement order has been created and cannot be reused', 'WM', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('113', 'zh_CN', 'WM.tip.returnStock.allocateCanNotReturnRepeated', '调拨出库单已经创建调拨入库单，不可重复入库', 'WM', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('114', 'zh_TW', 'WM.tip.returnStock.allocateCanNotReturnRepeated', '調撥出庫單已經創建調撥入庫單，不可重復入庫', 'WM', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('115', 'en', 'WM.tip.returnStock.atLeastOneNotNull', 'At least one of the request parameters is not null', 'WM', null, null, '2022-01-19 21:40:50', null);
INSERT INTO `s_i18n_resource` VALUES ('116', 'zh_CN', 'WM.tip.returnStock.atLeastOneNotNull', '请求参数中，至少存在一个非空', 'WM', null, null, '2022-01-19 21:40:51', null);
INSERT INTO `s_i18n_resource` VALUES ('117', 'zh_TW', 'WM.tip.returnStock.atLeastOneNotNull', '請求參數中，至少存在一個非空', 'WM', null, null, '2022-01-19 21:40:51', null);
INSERT INTO `s_i18n_resource` VALUES ('118', 'en', 'WM.tip.returnStock.chargeOffCanNotReturn', 'The outbound line number {0} has been written off and cannot be stored', 'WM', null, null, '2022-01-19 21:40:51', null);
INSERT INTO `s_i18n_resource` VALUES ('119', 'zh_CN', 'WM.tip.returnStock.chargeOffCanNotReturn', '出库单行号{0}已冲销，不可入库', 'WM', null, null, '2022-01-19 21:40:51', null);
INSERT INTO `s_i18n_resource` VALUES ('120', 'zh_TW', 'WM.tip.returnStock.chargeOffCanNotReturn', '出庫單行號{0}已沖銷，不可入庫', 'WM', null, null, '2022-01-19 21:40:51', null);
INSERT INTO `s_i18n_resource` VALUES ('121', 'en', 'WM.tip.returnStock.dtlExistCheck', 'Add details', 'WM', null, null, '2022-01-19 21:40:51', null);
INSERT INTO `s_i18n_resource` VALUES ('122', 'zh_CN', 'WM.tip.returnStock.dtlExistCheck', '请添加明细信息', 'WM', null, null, '2022-01-19 21:40:51', null);
INSERT INTO `s_i18n_resource` VALUES ('123', 'zh_TW', 'WM.tip.returnStock.dtlExistCheck', '請添加明細信息', 'WM', null, null, '2022-01-19 21:40:51', null);
INSERT INTO `s_i18n_resource` VALUES ('124', 'en', 'WM.tip.returnStock.dtlWarehouseNotSame', 'The detailed warehouse is inconsistent, please check', 'WM', null, null, '2022-01-19 21:40:51', null);
INSERT INTO `s_i18n_resource` VALUES ('125', 'zh_CN', 'WM.tip.returnStock.dtlWarehouseNotSame', '明细仓库不一致，请检查', 'WM', null, null, '2022-01-19 21:40:51', null);
INSERT INTO `s_i18n_resource` VALUES ('126', 'zh_TW', 'WM.tip.returnStock.dtlWarehouseNotSame', '明細倉庫不一致，請檢查', 'WM', null, null, '2022-01-19 21:40:51', null);
INSERT INTO `s_i18n_resource` VALUES ('127', 'en', 'WM.tip.returnStock.greaterCanOutQty', 'Line {0} of outbound order cannot be returned in excess', 'WM', null, null, '2022-01-19 21:40:51', null);
INSERT INTO `s_i18n_resource` VALUES ('128', 'zh_CN', 'WM.tip.returnStock.greaterCanOutQty', '出库单{0}行不可超量退库', 'WM', null, null, '2022-01-19 21:40:51', null);
INSERT INTO `s_i18n_resource` VALUES ('129', 'zh_TW', 'WM.tip.returnStock.greaterCanOutQty', '出庫單{0}行不可超量退庫', 'WM', null, null, '2022-01-19 21:40:51', null);
INSERT INTO `s_i18n_resource` VALUES ('130', 'en', 'WM.tip.returnStock.hasNoLabelCode', 'Please enter the label code', 'WM', null, null, '2022-01-19 21:40:51', null);
INSERT INTO `s_i18n_resource` VALUES ('131', 'zh_CN', 'WM.tip.returnStock.hasNoLabelCode', '请输入标签编码', 'WM', null, null, '2022-01-19 21:40:51', null);
INSERT INTO `s_i18n_resource` VALUES ('132', 'zh_TW', 'WM.tip.returnStock.hasNoLabelCode', '請輸入標簽編碼', 'WM', null, null, '2022-01-19 21:40:51', null);
INSERT INTO `s_i18n_resource` VALUES ('133', 'en', 'WM.tip.returnStock.hasNoMaterialCodeCheck', 'This return does not contain material {0}, please check', 'WM', null, null, '2022-01-19 21:40:51', null);
INSERT INTO `s_i18n_resource` VALUES ('134', 'zh_CN', 'WM.tip.returnStock.hasNoMaterialCodeCheck', '本次退库不包含物料{0}，请检查', 'WM', null, null, '2022-01-19 21:40:52', null);
INSERT INTO `s_i18n_resource` VALUES ('135', 'zh_TW', 'WM.tip.returnStock.hasNoMaterialCodeCheck', '本次退庫不包含物料{0}，請檢查', 'WM', null, null, '2022-01-19 21:40:52', null);
INSERT INTO `s_i18n_resource` VALUES ('136', 'en', 'WM.tip.returnStock.hasOnShelvesWork', 'The request sheet {0} of the return sheet exists in the shelving sheet and cannot be deleted', 'WM', null, null, '2022-01-19 21:40:52', null);
INSERT INTO `s_i18n_resource` VALUES ('137', 'zh_CN', 'WM.tip.returnStock.hasOnShelvesWork', '退库单的请求单{0}存在上架作业单，不可删除', 'WM', null, null, '2022-01-19 21:40:52', null);
INSERT INTO `s_i18n_resource` VALUES ('138', 'zh_TW', 'WM.tip.returnStock.hasOnShelvesWork', '退庫單的請求單{0}存在上架作業單，不可刪除', 'WM', null, null, '2022-01-19 21:40:52', null);
INSERT INTO `s_i18n_resource` VALUES ('139', 'en', 'WM.tip.returnStock.invalidLabelCode', 'Invalid tag code, please rescan', 'WM', null, null, '2022-01-19 21:40:52', null);
INSERT INTO `s_i18n_resource` VALUES ('140', 'zh_CN', 'WM.tip.returnStock.invalidLabelCode', '无效的标签编码，请重新扫描', 'WM', null, null, '2022-01-19 21:40:52', null);
INSERT INTO `s_i18n_resource` VALUES ('141', 'zh_TW', 'WM.tip.returnStock.invalidLabelCode', '無效的標簽編碼，請重新掃描', 'WM', null, null, '2022-01-19 21:40:52', null);
INSERT INTO `s_i18n_resource` VALUES ('142', 'en', 'WM.tip.returnStock.labelCanNotReturnRepeated', 'The tag {0} is already in the library and cannot be re-entered', 'WM', null, null, '2022-01-19 21:40:52', null);
INSERT INTO `s_i18n_resource` VALUES ('143', 'zh_CN', 'WM.tip.returnStock.labelCanNotReturnRepeated', '标签{0}已在库，不可重复入库', 'WM', null, null, '2022-01-19 21:40:52', null);
INSERT INTO `s_i18n_resource` VALUES ('144', 'zh_TW', 'WM.tip.returnStock.labelCanNotReturnRepeated', '標簽{0}已在庫，不可重復入庫', 'WM', null, null, '2022-01-19 21:40:52', null);
INSERT INTO `s_i18n_resource` VALUES ('145', 'en', 'WM.tip.returnStock.notReturnStockDtlLabelScan', 'Please scan labels that are not returned by this return order separately', 'WM', null, null, '2022-01-19 21:40:52', null);
INSERT INTO `s_i18n_resource` VALUES ('146', 'zh_CN', 'WM.tip.returnStock.notReturnStockDtlLabelScan', '非本退库单退库的标签，请另外扫描', 'WM', null, null, '2022-01-19 21:40:52', null);
INSERT INTO `s_i18n_resource` VALUES ('147', 'zh_TW', 'WM.tip.returnStock.notReturnStockDtlLabelScan', '非本退庫單退庫的標簽，請另外掃描', 'WM', null, null, '2022-01-19 21:40:52', null);
INSERT INTO `s_i18n_resource` VALUES ('148', 'en', 'WM.tip.returnStock.notSameOutStockNoScan', 'Please scan the label of the warehouse receipt if it is not the same', 'WM', null, null, '2022-01-19 21:40:52', null);
INSERT INTO `s_i18n_resource` VALUES ('149', 'zh_CN', 'WM.tip.returnStock.notSameOutStockNoScan', '非同一领料出库单标签，请另外扫描', 'WM', null, null, '2022-01-19 21:40:52', null);
INSERT INTO `s_i18n_resource` VALUES ('150', 'zh_TW', 'WM.tip.returnStock.notSameOutStockNoScan', '非同一領料出庫單標簽，請另外掃描', 'WM', null, null, '2022-01-19 21:40:52', null);
INSERT INTO `s_i18n_resource` VALUES ('151', 'en', 'WM.tip.returnStock.oneWorehouseCheck', 'The warehouse entry details are inconsistent, please check', 'WM', null, null, '2022-01-19 21:40:52', null);
INSERT INTO `s_i18n_resource` VALUES ('152', 'zh_CN', 'WM.tip.returnStock.oneWorehouseCheck', '入库明细仓库不一致，请检查', 'WM', null, null, '2022-01-19 21:40:52', null);
INSERT INTO `s_i18n_resource` VALUES ('153', 'zh_TW', 'WM.tip.returnStock.oneWorehouseCheck', '入庫明細倉庫不一致，請檢查', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('154', 'en', 'WM.tip.returnStock.outBoundDeliveryHasReturnStock', 'The delivery order has been returned {0}', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('155', 'zh_CN', 'WM.tip.returnStock.outBoundDeliveryHasReturnStock', '交货单已存在退库单{0}', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('156', 'zh_TW', 'WM.tip.returnStock.outBoundDeliveryHasReturnStock', '交貨單已存在退庫單{0}', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('157', 'en', 'WM.tip.returnStock.outStockRowNoGreaterSkuQty', 'Line {0} of outbound order cannot be returned in excess', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('158', 'zh_CN', 'WM.tip.returnStock.outStockRowNoGreaterSkuQty', '出库单{0}行不可超量退库', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('159', 'zh_TW', 'WM.tip.returnStock.outStockRowNoGreaterSkuQty', '出庫單{0}行不可超量退庫', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('160', 'en', 'WM.tip.returnStock.returnNotAllowed', 'The outbound order status is {0} and cannot be returned', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('161', 'zh_CN', 'WM.tip.returnStock.returnNotAllowed', '出库单状态为{0}，不可退库', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('162', 'zh_TW', 'WM.tip.returnStock.returnNotAllowed', '出庫單狀態為{0}，不可退庫', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('163', 'en', 'WM.tip.returnStock.rowNoLocationIsNullCheck', 'The details about the unmaintained database locations exist in line {0}. Please maintain the information', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('164', 'zh_CN', 'WM.tip.returnStock.rowNoLocationIsNullCheck', '行号{0}下存在未维护入库库位的明细，请维护', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('165', 'zh_TW', 'WM.tip.returnStock.rowNoLocationIsNullCheck', '行號{0}下存在未維護入庫庫位的明細，請維護', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('166', 'en', 'WM.tip.returnStock.rowNoRevokedCanNotReturn', 'The outbound line number {0} has been written off and cannot be returned', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('167', 'zh_CN', 'WM.tip.returnStock.rowNoRevokedCanNotReturn', '出库单行号{0}已冲销，不可退库', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('168', 'zh_TW', 'WM.tip.returnStock.rowNoRevokedCanNotReturn', '出庫單行號{0}已沖銷，不可退庫', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('169', 'en', 'WM.tip.returnStock.rowNoSkuQtyIsNullCheck', 'Line {0} does not maintain the details of the number of entries. Please check', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('170', 'zh_CN', 'WM.tip.returnStock.rowNoSkuQtyIsNullCheck', '行号{0}未维护本次入库数量的明细，请检查', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('171', 'zh_TW', 'WM.tip.returnStock.rowNoSkuQtyIsNullCheck', '行號{0}未維護本次入庫數量的明細，請檢查', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('172', 'en', 'WM.tip.returnStock.sameCustomerCodeCheck', 'If the label is not associated with this order, please scan it separately', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('173', 'zh_CN', 'WM.tip.returnStock.sameCustomerCodeCheck', '非本单关联退库标签，请另外扫描', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('174', 'zh_TW', 'WM.tip.returnStock.sameCustomerCodeCheck', '非本單關聯退庫標簽，請另外掃描', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('175', 'en', 'WM.tip.returnStock.skuQtyGreaterOrderQty', 'Line {0} This return quantity is greater than the order quantity, please check', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('176', 'zh_CN', 'WM.tip.returnStock.skuQtyGreaterOrderQty', '行{0}本次退库数量大于订单数量，请检查', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('177', 'zh_TW', 'WM.tip.returnStock.skuQtyGreaterOrderQty', '行{0}本次退庫數量大於訂單數量，請檢查', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('178', 'en', 'WM.tip.returnStock.skuQtyIsNull', 'Line {0} contains details about the number of unmaintained returned libraries, please check', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('179', 'zh_CN', 'WM.tip.returnStock.skuQtyIsNull', '行号{0}存在未维护退库数量的明细，请检查', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('180', 'zh_TW', 'WM.tip.returnStock.skuQtyIsNull', '行號{0}存在未維護退庫數量的明細，請檢查', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('181', 'en', 'WM.tip.returnStock.sumLabelQtyNotSame', 'The total number of labels in line {0} is inconsistent with that in the line', 'WM', null, null, '2022-01-19 21:40:53', null);
INSERT INTO `s_i18n_resource` VALUES ('182', 'zh_CN', 'WM.tip.returnStock.sumLabelQtyNotSame', '行号{0}的标签信息合计入库数量与所在行的不一致，请检查', 'WM', null, null, '2022-01-19 21:40:54', null);
INSERT INTO `s_i18n_resource` VALUES ('183', 'zh_TW', 'WM.tip.returnStock.sumLabelQtyNotSame', '行號{0}的標簽信息合計入庫數量與所在行的不一致，請檢查', 'WM', null, null, '2022-01-19 21:40:54', null);
INSERT INTO `s_i18n_resource` VALUES ('1483974948160974850', 'demoData', 'demoData', 'demoData', 'demoData', null, null, '2022-01-20 09:29:33', null);

-- ----------------------------
-- Table structure for s_mail_history
-- ----------------------------
DROP TABLE IF EXISTS `s_mail_history`;
CREATE TABLE `s_mail_history` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `template_id` bigint(20) DEFAULT NULL COMMENT '邮件模板ID',
  `config_id` bigint(20) DEFAULT NULL COMMENT '邮件配置ID',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `from` varchar(200) DEFAULT NULL COMMENT '发送者',
  `receive_user_id` bigint(20) DEFAULT NULL COMMENT '接收用户ID',
  `receive_user_name` varchar(200) DEFAULT NULL COMMENT '接收用户名称',
  `receive_mail` varchar(1000) DEFAULT NULL COMMENT '接收邮箱',
  `cc` varchar(1000) DEFAULT NULL COMMENT '抄送者',
  `bcc` varchar(1000) DEFAULT NULL COMMENT '密送者',
  `content` text COMMENT '内容',
  `file_group_id` bigint(20) DEFAULT NULL COMMENT '文件组ID',
  `try_count` tinyint(3) unsigned DEFAULT NULL COMMENT '发送次数',
  `msg` text COMMENT '失败原因',
  `last_send_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次发送时间',
  `send_flag` tinyint(1) unsigned DEFAULT NULL COMMENT '0-失败，1-成功',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='邮件发送历史表';

-- ----------------------------
-- Records of s_mail_history
-- ----------------------------
INSERT INTO `s_mail_history` VALUES ('1484343211827048449', '1484036940857409537', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '这是一封普通的SpringBoot测试邮件', '1', '1', null, '2022-01-21 09:52:54', '1', null, null, '2022-01-21 09:52:54', null);
INSERT INTO `s_mail_history` VALUES ('1484350238116761601', '1484036940857409537', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '这是一封普通的SpringBoot测试邮件', '1', '1', null, '2022-01-21 10:20:49', '0', null, null, '2022-01-21 10:20:49', null);
INSERT INTO `s_mail_history` VALUES ('1484353280920707074', '1484036940857409537', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '这是一封普通的SpringBoot测试邮件', '1', '1', null, '2022-01-21 10:32:55', '0', null, null, '2022-01-21 10:32:54', null);
INSERT INTO `s_mail_history` VALUES ('1484357231422357505', '1484036940857409537', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '这是一封普通的SpringBoot测试邮件', '1', '1', null, '2022-01-21 10:48:37', '0', null, null, '2022-01-21 10:48:36', null);
INSERT INTO `s_mail_history` VALUES ('1484357336317706241', '1484036940857409537', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '这是一封普通的SpringBoot测试邮件', '1', '1', null, '2022-01-21 10:49:02', '1', null, null, '2022-01-21 10:49:01', null);
INSERT INTO `s_mail_history` VALUES ('1484357818864058370', '1484036940857409537', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '这是一封普通的SpringBoot测试邮件', '1', '1', null, '2022-01-21 10:50:57', '1', null, null, '2022-01-21 10:50:56', null);
INSERT INTO `s_mail_history` VALUES ('1484385755051032578', '1484036940857409537', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '这是一封普通的SpringBoot测试邮件', '1', '1', null, '2022-01-21 12:41:57', '0', null, null, '2022-01-21 12:41:57', null);
INSERT INTO `s_mail_history` VALUES ('1484386108391784450', '1484036940857409537', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '这是一封普通的SpringBoot测试邮件', '1', '1', null, '2022-01-21 12:43:21', '0', null, null, '2022-01-21 12:43:21', null);
INSERT INTO `s_mail_history` VALUES ('1484386681220452353', '1484036940857409537', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '这是一封普通的SpringBoot测试邮件', '1', '1', null, '2022-01-21 12:45:38', '0', null, null, '2022-01-21 12:45:38', null);
INSERT INTO `s_mail_history` VALUES ('1484401042001428481', '1484400481734688769', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '<p style=\'color:#42b983\'>使用Spring Boot发送HTML格式邮件。</p>', '1', '1', 'Could not connect to SMTP host: smtp.qq.com, port: 587', '2022-01-21 13:42:42', '0', null, null, '2022-01-21 13:42:41', null);
INSERT INTO `s_mail_history` VALUES ('1484401452875440129', '1484400481734688769', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '<p style=\'color:#42b983\'>使用Spring Boot发送HTML格式邮件。</p>', '1', '1', null, '2022-01-21 13:44:20', '1', null, null, '2022-01-21 13:44:19', null);
INSERT INTO `s_mail_history` VALUES ('1484405104058998785', '1484400481734688769', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '<p style=\'color:#42b983\'>使用Spring Boot发送HTML格式邮件。</p>', '1', '1', 'Could not connect to SMTP host: smtp.qq.com, port: 587', '2022-01-21 13:58:50', '0', null, null, '2022-01-21 13:58:50', null);
INSERT INTO `s_mail_history` VALUES ('1484405252545748993', '1484400481734688769', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '<p style=\'color:#42b983\'>使用Spring Boot发送HTML格式邮件。</p>', '1', '1', 'Could not connect to SMTP host: smtp.qq.com, port: 587', '2022-01-21 13:59:26', '0', null, null, '2022-01-21 13:59:25', null);
INSERT INTO `s_mail_history` VALUES ('1484410896493543425', '1484400481734688769', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '<p style=\'color:#42b983\'>使用Spring Boot发送HTML格式邮件。</p>', '1', '1', 'org.springframework.mail.MailSendException: Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 502 Invalid input from 220.160.108.30 to newxmesmtplogicsvrsza5.qq.com.\n; message exceptions (1) are:\nFailed message 1: com.sun.mail.smtp.SMTPSendFailedException: 502 Invalid input from 220.160.108.30 to newxmesmtplogicsvrsza5.qq.com.\n', '2022-01-21 14:21:51', '0', null, null, '2022-01-21 14:21:51', null);
INSERT INTO `s_mail_history` VALUES ('1484411602478792706', '1484400481734688769', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '<p style=\'color:#42b983\'>使用Spring Boot发送HTML格式邮件。</p>', '1', '1', 'org.springframework.mail.MailSendException: Failed messages: com.sun.mail.smtp.SMTPSendFailedException: 502 Invalid input from 220.160.108.30 to newxmesmtplogicsvrszc10.qq.com.\n; message exceptions (1) are:\nFailed message 1: com.sun.mail.smtp.SMTPSendFailedException: 502 Invalid input from 220.160.108.30 to newxmesmtplogicsvrszc10.qq.com.\n', '2022-01-21 14:24:40', '0', null, null, '2022-01-21 14:24:39', null);
INSERT INTO `s_mail_history` VALUES ('1484413731234844674', '1484400481734688769', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '<p style=\'color:#42b983\'>使用Spring Boot发送HTML格式邮件。</p>', '1', '1', null, '2022-01-21 14:33:07', '1', null, null, '2022-01-21 14:33:07', null);
INSERT INTO `s_mail_history` VALUES ('1484414291396726785', '1484402605688283137', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '<html><body>博客图：<img src=\'cid:img\'/></body></html>', '1', '1', 'org.springframework.mail.MailSendException: Failed messages: javax.mail.MessagingException: IOException while sending message;\n  nested exception is:\n	java.io.FileNotFoundException: src\\main\\resources\\static\\img\\sunshine.png; message exceptions (1) are:\nFailed message 1: javax.mail.MessagingException: IOException while sending message;\n  nested exception is:\n	java.io.FileNotFoundException: src\\main\\resources\\static\\img\\sunshine.png', '2022-01-21 14:35:21', '0', null, null, '2022-01-21 14:35:20', null);
INSERT INTO `s_mail_history` VALUES ('1484414938095513602', '1484402605688283137', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '<html><body>博客图：<img src=\'cid:img\'/></body></html>', '1', '1', 'org.springframework.mail.MailSendException: Failed messages: javax.mail.MessagingException: IOException while sending message;\n  nested exception is:\n	java.io.FileNotFoundException: src\\main\\resources\\static\\img\\sunshine.png; message exceptions (1) are:\nFailed message 1: javax.mail.MessagingException: IOException while sending message;\n  nested exception is:\n	java.io.FileNotFoundException: src\\main\\resources\\static\\img\\sunshine.png', '2022-01-21 14:37:55', '0', null, null, '2022-01-21 14:37:55', null);
INSERT INTO `s_mail_history` VALUES ('1484415851371593729', '1484402605688283137', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '<html><body>博客图：<img src=\'cid:img\'/></body></html>', '1', '1', 'org.springframework.mail.MailSendException: Failed messages: javax.mail.MessagingException: IOException while sending message;\n  nested exception is:\n	java.io.FileNotFoundException: src\\main\\resources\\static\\img\\sunshine.png; message exceptions (1) are:\nFailed message 1: javax.mail.MessagingException: IOException while sending message;\n  nested exception is:\n	java.io.FileNotFoundException: src\\main\\resources\\static\\img\\sunshine.png', '2022-01-21 14:41:33', '0', null, null, '2022-01-21 14:41:32', null);
INSERT INTO `s_mail_history` VALUES ('1484417235361918978', '1484402605688283137', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '<html><body>博客图：<img src=\'cid:img\'/></body></html>', '1', '1', 'org.springframework.mail.MailSendException: Failed messages: javax.mail.MessagingException: IOException while sending message;\n  nested exception is:\n	java.io.FileNotFoundException: \\static\\img\\sunshine.png; message exceptions (1) are:\nFailed message 1: javax.mail.MessagingException: IOException while sending message;\n  nested exception is:\n	java.io.FileNotFoundException: \\static\\img\\sunshine.png', '2022-01-21 14:47:03', '0', null, null, '2022-01-21 14:47:02', null);
INSERT INTO `s_mail_history` VALUES ('1484418219375009794', '1484402605688283137', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '<html><body>博客图：<img src=\'cid:img\'/></body></html>', '1', '1', 'java.lang.NullPointerException', '2022-01-21 14:50:57', '0', null, null, '2022-01-21 14:50:57', null);
INSERT INTO `s_mail_history` VALUES ('1484420584069111809', '1484402605688283137', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '<html><body>博客图：<img src=\'cid:img\'/></body></html>', '1', '1', 'org.springframework.mail.MailSendException: Failed messages: javax.mail.MessagingException: IOException while sending message;\n  nested exception is:\n	java.io.FileNotFoundException: src\\main\\resources\\static\\img\\sunshine.png; message exceptions (1) are:\nFailed message 1: javax.mail.MessagingException: IOException while sending message;\n  nested exception is:\n	java.io.FileNotFoundException: src\\main\\resources\\static\\img\\sunshine.png', '2022-01-21 15:00:21', '0', null, null, '2022-01-21 15:00:21', null);
INSERT INTO `s_mail_history` VALUES ('1484421250422353922', '1484402605688283137', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '<html><body>博客图：<img src=\'cid:img\'/></body></html>', '1', '1', 'org.springframework.mail.MailSendException: Failed messages: javax.mail.MessagingException: IOException while sending message;\n  nested exception is:\n	java.nio.file.AccessDeniedException: E:\\CODESPACE\\PhoenixPlan\\platform-demo\\starter-test-service\\src\\main\\resources\\static\\img; message exceptions (1) are:\nFailed message 1: javax.mail.MessagingException: IOException while sending message;\n  nested exception is:\n	java.nio.file.AccessDeniedException: E:\\CODESPACE\\PhoenixPlan\\platform-demo\\starter-test-service\\src\\main\\resources\\static\\img', '2022-01-21 15:03:00', '0', null, null, '2022-01-21 15:02:59', null);
INSERT INTO `s_mail_history` VALUES ('1484422807268278274', '1484402605688283137', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '<html><body>博客图：<img src=\'cid:img\'/></body></html>', '1', '1', 'java.nio.file.InvalidPathException: Illegal char <:> at index 4: http:\\wordplay.work\\usr\\uploads\\2022\\01\\1439745844.png', '2022-01-21 15:09:11', '0', null, null, '2022-01-21 15:09:11', null);
INSERT INTO `s_mail_history` VALUES ('1484423522317422593', '1484402605688283137', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '<html><body>博客图：<img src=\'cid:img\'/></body></html>', '1', '1', null, '2022-01-21 15:12:02', '1', null, null, '2022-01-21 15:12:01', null);
INSERT INTO `s_mail_history` VALUES ('1484424631182389250', '1484402605688283137', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '<html><body>博客图：<img src=\'cid:img\'/></body></html>', '1', '1', null, '2022-01-21 15:16:26', '1', null, null, '2022-01-21 15:16:26', null);
INSERT INTO `s_mail_history` VALUES ('1484427000418824193', '1484402605688283137', '1484339081028984833', '测试模板', '809566095@qq.com', '1', 'demoData', '809566095@qq.com', '809566095@qq.com', '809566095@qq.com', '<html><body>博客图：<img src=\'cid:img\'/></body></html>', '1', '1', null, '2022-01-21 15:25:51', '1', null, null, '2022-01-21 15:25:50', null);

-- ----------------------------
-- Table structure for s_mail_sender_config
-- ----------------------------
DROP TABLE IF EXISTS `s_mail_sender_config`;
CREATE TABLE `s_mail_sender_config` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `host` varchar(200) DEFAULT NULL COMMENT '如：smtp.163.com',
  `port` int(11) DEFAULT NULL COMMENT '端口',
  `username` varchar(255) DEFAULT NULL COMMENT '账号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `protocol` varchar(255) DEFAULT NULL COMMENT '协议',
  `default_encoding` varchar(255) DEFAULT NULL COMMENT '默认编码',
  `properties` text COMMENT '其他的参数配置(JSON格式)',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='邮件发送配置表';

-- ----------------------------
-- Records of s_mail_sender_config
-- ----------------------------
INSERT INTO `s_mail_sender_config` VALUES ('1484339081028984833', 'smtp.qq.com', '587', '809566095@qq.com', 'wsnjcihniszibfge', 'smtp', 'UTF-8', '{}', null, null, '2022-01-21 09:36:29', '2022-01-21 13:59:32');

-- ----------------------------
-- Table structure for s_mail_template
-- ----------------------------
DROP TABLE IF EXISTS `s_mail_template`;
CREATE TABLE `s_mail_template` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `code` varchar(50) NOT NULL COMMENT '邮件模板配置编码，即模板文件名',
  `desc` varchar(255) DEFAULT NULL COMMENT '邮件模板配置描述',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `from` varchar(255) DEFAULT NULL COMMENT '发送者，即配置中的用户名',
  `content` text COMMENT '内容',
  `file_group_id` bigint(20) DEFAULT NULL COMMENT '文件组ID',
  `retry_count` tinyint(1) unsigned DEFAULT NULL COMMENT '重试次数',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='邮件模板表';

-- ----------------------------
-- Records of s_mail_template
-- ----------------------------
INSERT INTO `s_mail_template` VALUES ('1484036940857409537', 'test', '测试模板', '测试模板', '809566095@qq.com', '这是一封普通的SpringBoot测试邮件', '1', '1', null, null, '2022-01-20 13:35:53', '2022-01-21 14:28:59');
INSERT INTO `s_mail_template` VALUES ('1484400481734688769', 'test_template', '测试模板', '测试模板', '809566095@qq.com', '<p style=\'color:#42b983\'>使用Spring Boot发送HTML格式邮件。</p>', '1', '1', null, null, '2022-01-21 13:40:28', '2022-01-21 14:28:59');
INSERT INTO `s_mail_template` VALUES ('1484402605688283137', 'emailTemplate', '测试模板', '测试模板', '809566095@qq.com', '<html><body>博客图：<img src=\'cid:img\'/></body></html>', '1', '1', null, null, '2022-01-21 13:48:54', '2022-01-21 15:23:00');

-- ----------------------------
-- Table structure for s_menu
-- ----------------------------
DROP TABLE IF EXISTS `s_menu`;
CREATE TABLE `s_menu` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级菜单ID',
  `menu_code` varchar(255) DEFAULT NULL COMMENT '菜单编码',
  `menu_name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `menu_desc` varchar(255) DEFAULT NULL COMMENT '菜单描述',
  `func_link` varchar(255) DEFAULT NULL COMMENT '功能链接如：/platform/msg-log',
  `open_type` tinyint(3) unsigned DEFAULT NULL COMMENT '打开方式，inner-link：通过链接打开tab，outter-link：通过链接打开浏览器新窗口，默认空表示:调用/console/open读取元数据打开',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `order` tinyint(3) unsigned DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_show` tinyint(3) unsigned DEFAULT NULL COMMENT '是否显示',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- Records of s_menu
-- ----------------------------

-- ----------------------------
-- Table structure for s_mq_trace_log
-- ----------------------------
DROP TABLE IF EXISTS `s_mq_trace_log`;
CREATE TABLE `s_mq_trace_log` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `stage` tinyint(1) unsigned DEFAULT NULL COMMENT '阶段',
  `content` text COMMENT '消息体JSON',
  `exchange` varchar(255) DEFAULT NULL COMMENT '交换机',
  `route_key` varchar(255) DEFAULT NULL COMMENT '路由',
  `delivery_tag` varchar(255) DEFAULT NULL COMMENT '投递TAG',
  `consumer_tag` varchar(255) DEFAULT NULL COMMENT '消费TAG',
  `ack_mode` varchar(255) DEFAULT NULL COMMENT 'ACK模式',
  `publish_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `consume_time` datetime DEFAULT NULL COMMENT '消费时间',
  `subscriber` varchar(255) DEFAULT NULL COMMENT '订阅者',
  `request_id` bigint(20) DEFAULT NULL COMMENT '请求ID',
  `status` tinyint(1) unsigned DEFAULT '0' COMMENT '状态：0-失败，1-成功',
  `reply_code` varchar(255) DEFAULT NULL COMMENT '返回码',
  `reply_text` varchar(255) DEFAULT NULL COMMENT '返回信息',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='MQ消息投递日志表';

-- ----------------------------
-- Records of s_mq_trace_log
-- ----------------------------
INSERT INTO `s_mq_trace_log` VALUES ('1484906921864482818', '5', 'demoData', 'demoData', 'demoData', 'demoData', 'demoData', 'demoData', '2022-01-22 23:12:53', null, 'demoData', '1', '0', null, null, null, null, '2022-01-22 23:12:53', '2022-01-22 23:12:53');

-- ----------------------------
-- Table structure for s_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_permission`;
CREATE TABLE `s_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_code` varchar(255) NOT NULL COMMENT '权限编码',
  `permission_name` varchar(255) DEFAULT NULL COMMENT '权限名称即接口名称',
  `resource_value` varchar(255) DEFAULT NULL COMMENT '资源值即URL',
  `order_num` int(4) DEFAULT NULL COMMENT '顺序',
  `authc_type` tinyint(1) DEFAULT '1' COMMENT '认证类型：anon(允许匿名访问)，auth(登录即可访问)',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统接口权限表';

-- ----------------------------
-- Records of s_permission
-- ----------------------------
INSERT INTO `s_permission` VALUES ('1', 'shiro:test01', '测试', '/shiro/test01', null, '1', null, null, '2022-02-12 17:56:59', '2022-02-28 11:28:44');

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `role_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色编码',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `role_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('1', 'admin', '管理员', '管理员', null, null, '2022-02-12 17:56:16', null);

-- ----------------------------
-- Table structure for s_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `s_role_menu`;
CREATE TABLE `s_role_menu` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单表';

-- ----------------------------
-- Records of s_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for s_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_role_permission`;
CREATE TABLE `s_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

-- ----------------------------
-- Records of s_role_permission
-- ----------------------------
INSERT INTO `s_role_permission` VALUES ('1', '1', '1', null, null, '2022-02-12 17:57:28', null);
INSERT INTO `s_role_permission` VALUES ('2', '2', '2', null, null, '2022-02-12 22:41:55', null);

-- ----------------------------
-- Table structure for s_sms_config
-- ----------------------------
DROP TABLE IF EXISTS `s_sms_config`;
CREATE TABLE `s_sms_config` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `product_type` varchar(255) DEFAULT NULL COMMENT '使用的短信产品类型',
  `region_id` varchar(255) DEFAULT NULL COMMENT '区域ID',
  `access_key_id` varchar(255) DEFAULT NULL COMMENT 'accessKeyId',
  `access_key_secret` varchar(255) DEFAULT NULL COMMENT 'accessKeySecret',
  `properties` text COMMENT '其他的参数配置(JSON格式)',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短信发送配置表';

-- ----------------------------
-- Records of s_sms_config
-- ----------------------------

-- ----------------------------
-- Table structure for s_sms_history
-- ----------------------------
DROP TABLE IF EXISTS `s_sms_history`;
CREATE TABLE `s_sms_history` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `template_id` bigint(20) DEFAULT NULL COMMENT '短信模板ID',
  `config_id` bigint(20) DEFAULT NULL COMMENT '邮件配置ID',
  `from` varchar(200) DEFAULT NULL COMMENT '发送者',
  `receive_user_id` bigint(20) DEFAULT NULL COMMENT '接收用户ID',
  `receive_user_name` varchar(200) DEFAULT NULL COMMENT '接收用户名称',
  `receive_phone_number` varchar(1000) DEFAULT NULL COMMENT '接收号码',
  `content` text COMMENT '内容',
  `file_group_id` bigint(20) DEFAULT NULL COMMENT '文件组ID',
  `try_count` tinyint(3) unsigned DEFAULT NULL COMMENT '发送次数',
  `msg` text COMMENT '失败原因',
  `last_send_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次发送时间',
  `status` tinyint(1) unsigned DEFAULT NULL COMMENT '0-失败，1-成功',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短信发送历史表';

-- ----------------------------
-- Records of s_sms_history
-- ----------------------------

-- ----------------------------
-- Table structure for s_sms_template
-- ----------------------------
DROP TABLE IF EXISTS `s_sms_template`;
CREATE TABLE `s_sms_template` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `desc` varchar(255) DEFAULT NULL COMMENT '短信模板配置描述',
  `from` varchar(255) DEFAULT NULL COMMENT '发送号码',
  `content_type` tinyint(1) unsigned DEFAULT '0' COMMENT '0-简单，1-模板',
  `content` text COMMENT '内容',
  `file_group_id` bigint(20) DEFAULT NULL COMMENT '文件组ID',
  `retry_count` tinyint(1) unsigned DEFAULT NULL COMMENT '重试次数',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短信模板表';

-- ----------------------------
-- Records of s_sms_template
-- ----------------------------

-- ----------------------------
-- Table structure for s_sys_param_group
-- ----------------------------
DROP TABLE IF EXISTS `s_sys_param_group`;
CREATE TABLE `s_sys_param_group` (
  `code` varchar(50) NOT NULL COMMENT '系统参数组编码',
  `desc` varchar(200) DEFAULT NULL COMMENT '系统参数组描述',
  `status` tinyint(1) unsigned DEFAULT NULL COMMENT '是否启用：0-停用，1-启用',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统参数组表';

-- ----------------------------
-- Records of s_sys_param_group
-- ----------------------------
INSERT INTO `s_sys_param_group` VALUES ('APPLICATION_SIGNATURE', '系统签名配置', '1', null, null, '2021-11-28 19:48:17', null);
INSERT INTO `s_sys_param_group` VALUES ('ASYNC_SUPPORT_CONFIG', '异步请求(Async-Support)配置', '1', null, null, '2021-11-27 20:06:18', null);
INSERT INTO `s_sys_param_group` VALUES ('CORS_CONFIG', '跨域请求配置', '1', null, null, '2021-11-27 22:01:30', null);
INSERT INTO `s_sys_param_group` VALUES ('DISTRIBUTED_TX', '分布式事务配置', '1', null, null, '2021-11-27 14:18:13', null);
INSERT INTO `s_sys_param_group` VALUES ('FEIGN_CONFIG', 'FEIGN 配置', '1', null, null, '2021-11-27 14:38:30', null);
INSERT INTO `s_sys_param_group` VALUES ('FILTER_ORDER', 'FILTER 过滤器执行顺序', '1', null, null, '2021-11-27 21:44:27', null);
INSERT INTO `s_sys_param_group` VALUES ('JWT', 'JWT 配置', '1', null, null, '2021-11-27 13:28:56', '2021-11-27 13:33:28');
INSERT INTO `s_sys_param_group` VALUES ('LICENSE', 'LICENSE 配置', '1', null, null, '2021-11-27 13:34:43', '2021-11-27 13:34:49');
INSERT INTO `s_sys_param_group` VALUES ('PLATFORM_INIT_CONFIG', '平台初始化配置', '1', null, null, '2021-11-27 14:40:35', null);
INSERT INTO `s_sys_param_group` VALUES ('RSA', 'RSA 配置', '1', null, null, '2021-11-27 13:32:31', '2021-11-27 13:33:30');
INSERT INTO `s_sys_param_group` VALUES ('SCHEDULER_CONFIG', '定时任务配置', '1', null, null, '2021-11-27 14:43:14', null);
INSERT INTO `s_sys_param_group` VALUES ('SECURITY', 'SECURITY 配置', '1', null, null, '2021-11-27 14:45:14', null);
INSERT INTO `s_sys_param_group` VALUES ('SHIRO', 'SHIRO 配置', '1', null, null, '2022-02-15 09:07:31', null);
INSERT INTO `s_sys_param_group` VALUES ('SPRING_SECURITY', 'SPRING SECURITY配置', '1', null, null, '2022-02-15 09:19:00', '2022-02-15 09:19:05');
INSERT INTO `s_sys_param_group` VALUES ('THREADPOOL_CONFIG', '线程池配置', '1', null, null, '2021-11-27 14:49:49', null);
INSERT INTO `s_sys_param_group` VALUES ('XSS_CONFIG', 'XSS 配置', '1', null, null, '2021-11-29 09:40:26', null);

-- ----------------------------
-- Table structure for s_sys_param_item
-- ----------------------------
DROP TABLE IF EXISTS `s_sys_param_item`;
CREATE TABLE `s_sys_param_item` (
  `code` varchar(50) NOT NULL COMMENT '系统参数编码',
  `value` varchar(2000) DEFAULT NULL COMMENT '系统参数值',
  `encrypted_value` varchar(2000) DEFAULT NULL COMMENT '加密值',
  `desc` varchar(200) DEFAULT NULL COMMENT '系统参数描述',
  `status` tinyint(1) unsigned DEFAULT NULL COMMENT '是否启用：0-停用，1-启用',
  `group_code` varchar(50) DEFAULT NULL COMMENT '系统参数组编码',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统参数明细项表';

-- ----------------------------
-- Records of s_sys_param_item
-- ----------------------------
INSERT INTO `s_sys_param_item` VALUES ('ALLOWED_HEADER', '*', null, '表示我服务器支持的所有头字段，不限于预检请求中的头字段。', '1', 'CORS_CONFIG', null, null, '2021-11-27 22:05:17', '2021-11-27 22:05:47');
INSERT INTO `s_sys_param_item` VALUES ('ALLOWED_METHOD', '*', null, '支持的请求方法', '1', 'CORS_CONFIG', null, null, '2021-11-27 22:05:35', '2021-11-27 22:05:48');
INSERT INTO `s_sys_param_item` VALUES ('ALLOWED_ORIGIN', '*', null, '该响应头是服务器必须返回的。它的值要么是请求时Origin的值（可从request里获取），要么是*这样浏览器才会接受服务器的返回结果。', '1', 'CORS_CONFIG', null, null, '2021-11-27 22:04:50', '2021-11-27 22:05:46');
INSERT INTO `s_sys_param_item` VALUES ('ALLOW_ACCESS_WITHOUT_TOKEN', 'true', null, '是否允许无 token 访问', '1', 'SECURITY', null, null, '2021-11-27 14:49:16', '2021-11-28 13:04:13');
INSERT INTO `s_sys_param_item` VALUES ('ALLOW_CLIENTS', 'pc,app,wx', null, '系统允许的访问客户端', '1', 'SECURITY', null, null, '2021-11-27 14:47:19', '2021-11-27 14:47:25');
INSERT INTO `s_sys_param_item` VALUES ('ALLOW_CREDENTIALS', 'true', null, '该响应头非必须，值是bool类型，表示是否允许发送Cookie。', '1', 'CORS_CONFIG', null, null, '2021-11-27 22:04:01', '2021-11-27 22:12:15');
INSERT INTO `s_sys_param_item` VALUES ('APPLICATION_DEFAULT_STIME_TIMEOUT', '0', null, '带签名的请求默认超时时间', '1', 'APPLICATION_SIGNATURE', null, null, '2021-11-28 22:21:53', null);
INSERT INTO `s_sys_param_item` VALUES ('APPLICATION_SIGNATURE_CONTENT_TYPES', 'application/x-www-form-urlencoded,application/json', null, '签名的 content-type', '1', 'APPLICATION_SIGNATURE', null, null, '2021-11-28 19:50:02', '2022-01-18 23:33:27');
INSERT INTO `s_sys_param_item` VALUES ('APPLICATION_SIGNATURE_ENABLE', 'true', null, '是否开启签名', '1', 'APPLICATION_SIGNATURE', null, null, '2021-11-28 19:49:25', '2021-11-28 19:49:30');
INSERT INTO `s_sys_param_item` VALUES ('APPLICATION_SIGNATURE_EXCLUDE_PARAM_NAMES', null, null, '签名无需校验的参数', '1', 'APPLICATION_SIGNATURE', null, null, '2021-11-28 19:51:56', '2021-11-28 19:52:04');
INSERT INTO `s_sys_param_item` VALUES ('APPLICATION_SIGNATURE_EXCLUDE_SERVICE_NAMES', null, null, '不需要签名的服务名称', '1', 'APPLICATION_SIGNATURE', null, null, '2021-11-28 19:49:50', null);
INSERT INTO `s_sys_param_item` VALUES ('APPLICATION_SIGNATURE_EXCLUDE_URLS', null, null, '不需要签名的url', '1', 'APPLICATION_SIGNATURE', null, null, '2021-11-28 19:50:37', null);
INSERT INTO `s_sys_param_item` VALUES ('APPLICATION_SIGNATURE_METHOD_NAME', null, null, '带签名的请求方法', '1', 'APPLICATION_SIGNATURE', null, null, '2021-11-28 19:50:56', null);
INSERT INTO `s_sys_param_item` VALUES ('APPLICATION_SIGNATURE_NONCE_TIMEOUT', null, null, 'nonce超时时间', '1', 'APPLICATION_SIGNATURE', null, null, '2021-11-28 19:51:42', null);
INSERT INTO `s_sys_param_item` VALUES ('APPLICATION_SIGNATURE_REPEAT_CHECK_ENABLE', 'true', null, '是否开启表单重复提交检查', '1', 'APPLICATION_SIGNATURE', null, null, '2021-11-28 19:49:05', '2021-11-28 19:49:11');
INSERT INTO `s_sys_param_item` VALUES ('APPLICATION_SIGNATURE_STIME_TIMEOUT', null, null, '带签名的请求超时时间', '1', 'APPLICATION_SIGNATURE', null, null, '2021-11-28 19:51:15', null);
INSERT INTO `s_sys_param_item` VALUES ('ASYNC_SUPPORT_CORE_POOL_SIZE', '50', null, 'ASYNC_SUPPORT 核心线程数', '1', 'ASYNC_SUPPORT_CONFIG', null, null, '2021-11-27 20:07:10', '2021-11-27 20:07:36');
INSERT INTO `s_sys_param_item` VALUES ('ASYNC_SUPPORT_MAX_POOL_SIZE', '100', null, 'ASYNC_SUPPORT 最大线程数', '1', 'ASYNC_SUPPORT_CONFIG', null, null, '2021-11-27 20:07:48', '2021-11-27 20:07:53');
INSERT INTO `s_sys_param_item` VALUES ('ASYNC_SUPPORT_QUEUE_CAPACITY', '100', null, 'ASYNC_SUPPORT 队列大小', '1', 'ASYNC_SUPPORT_CONFIG', null, null, '2021-11-27 20:08:18', '2021-11-27 20:08:24');
INSERT INTO `s_sys_param_item` VALUES ('ASYNC_SUPPORT_THREAD_NAME_PREFIX', 'ASYNC-SUPPORT-THREAD-', null, 'ASYNC_SUPPORT 线程名称前缀', '1', 'ASYNC_SUPPORT_CONFIG', null, null, '2021-11-27 20:08:56', '2021-11-27 20:09:06');
INSERT INTO `s_sys_param_item` VALUES ('ASYNC_SUPPORT_TIMEOUT', '6000', null, '处理异步请求的超时时间(ms)', '1', 'ASYNC_SUPPORT_CONFIG', null, null, '2021-11-27 20:06:59', null);
INSERT INTO `s_sys_param_item` VALUES ('AUTH_FILTER_EXCLUDES', null, null, '授权过滤排除的资源', '1', 'SECURITY', null, null, '2021-11-27 14:48:09', null);
INSERT INTO `s_sys_param_item` VALUES ('CACHE_EXPIRE_TIME', '300', null, 'shiro缓存过期时间(一般设置与AccessToken过期时间一致)', '1', 'SHIRO', null, null, '2022-02-16 09:17:13', '2022-02-16 09:18:15');
INSERT INTO `s_sys_param_item` VALUES ('CLIENT_APP_PERMISSION_VERIFICATION', 'true', null, 'APP 权限校验', '1', 'SECURITY', null, null, '2021-11-27 14:48:27', '2021-11-28 13:04:09');
INSERT INTO `s_sys_param_item` VALUES ('CONNECT_TIMEOUT_SEC', '10', null, '连接超时时间(s)', '1', 'FEIGN_CONFIG', null, null, '2021-11-27 14:39:13', null);
INSERT INTO `s_sys_param_item` VALUES ('COOKIE_CIPHER_KEY', '4AvVhmFLUs0KTA3Kprsdag==', null, 'cookie 加密密钥', '1', 'SHIRO', null, null, '2022-02-17 15:47:36', '2022-02-17 15:48:23');
INSERT INTO `s_sys_param_item` VALUES ('corePoolSize', '10', null, '核心线程数', '1', 'THREADPOOL_CONFIG', null, null, '2021-11-27 14:50:22', '2021-11-27 14:50:50');
INSERT INTO `s_sys_param_item` VALUES ('CURRENT_CONTEXT_FILTER_ORDER', '-2147483448', null, '当前请求上下文过滤器', '1', 'FILTER_ORDER', null, null, '2021-11-27 21:45:38', '2021-11-27 21:46:34');
INSERT INTO `s_sys_param_item` VALUES ('DEFAULT_EXCLUDE_PARAM_NAMES', 'sign,_dc,seq', null, '默认签名无需校验的参数', '1', 'APPLICATION_SIGNATURE', null, null, '2021-11-28 19:52:50', null);
INSERT INTO `s_sys_param_item` VALUES ('DEFAULT_ISSUER', 'FALL-PLATFORM', null, 'JWT 发行者', '1', 'JWT', null, null, '2021-11-27 13:29:52', '2021-11-27 13:33:23');
INSERT INTO `s_sys_param_item` VALUES ('DEFAULT_MAGIC_NUMBER', '01-00-00-00', null, '默认证书 magic number', '1', 'LICENSE', null, null, '2021-11-27 13:35:16', null);
INSERT INTO `s_sys_param_item` VALUES ('DEFAULT_NEED_CHECK_METHOD_NAMES', 'save,update,submit', null, '默认需要检查的提交/保存方法', '1', 'APPLICATION_SIGNATURE', null, null, '2021-11-28 19:52:22', null);
INSERT INTO `s_sys_param_item` VALUES ('DISTRIBUTED_TX_RESERVED_SECONDS', '86400', null, '分布式锁保持时间(ms)', '1', 'DISTRIBUTED_TX', null, null, '2021-11-27 14:18:45', null);
INSERT INTO `s_sys_param_item` VALUES ('ENABLE_DATABASE_PASSWORD_ENCRYPTION', 'true', null, '是否启用数据库密码加密', '1', 'SECURITY', null, null, '2021-11-27 14:48:56', '2021-11-28 13:04:12');
INSERT INTO `s_sys_param_item` VALUES ('EXCLUDE_PATTERN', null, null, '排除URL规则', '1', 'XSS_CONFIG', null, null, '2021-11-29 09:42:31', null);
INSERT INTO `s_sys_param_item` VALUES ('FILTER_CHAIN_DEFINITION', '{\"/fonts/**\":\"anon\",\"/css/**\":\"anon\",\"/druid/**\":\"anon\",\"/logout\":\"logout\",\"/shiro/login\":\"anon\",\"/js/**\":\"anon\",\"/img/**\":\"anon\",\"/shiro/**\":\"jwt\"}', null, '过滤器链定义', '1', 'SHIRO', null, null, '2022-02-15 09:15:25', '2022-02-28 11:04:10');
INSERT INTO `s_sys_param_item` VALUES ('INCLUDE_PATTERN', null, null, '包括URL规则', '1', 'XSS_CONFIG', null, null, '2021-11-29 09:43:09', null);
INSERT INTO `s_sys_param_item` VALUES ('INIT_GLOBAL_PARAMETERS_ON_STARTUP', '0', null, '启动初始化全局系统参数', '1', 'PLATFORM_INIT_CONFIG', null, null, '2021-11-27 14:41:05', null);
INSERT INTO `s_sys_param_item` VALUES ('INIT_I18N_RESOURCE_ON_STARTUP', '0', null, '启动加载国际化资源', '1', 'PLATFORM_INIT_CONFIG', null, null, '2021-11-27 14:42:06', null);
INSERT INTO `s_sys_param_item` VALUES ('INIT_NOTIFY_TEMPLATE_CACHE_ON_STARTUP', '0', null, '启动初始化加载通知模板', '1', 'PLATFORM_INIT_CONFIG', null, null, '2021-11-27 14:41:50', null);
INSERT INTO `s_sys_param_item` VALUES ('INIT_PERMISSION_CACHE_ON_STARTUP', '0', null, '启动初始化加载权限数据缓存', '1', 'PLATFORM_INIT_CONFIG', null, null, '2021-11-27 14:41:27', null);
INSERT INTO `s_sys_param_item` VALUES ('INTERNAL_ACCESS_DEVIATION', '300000', null, '内部请求超时时间', '1', 'SECURITY', null, null, '2021-11-27 22:53:30', '2021-11-27 22:53:51');
INSERT INTO `s_sys_param_item` VALUES ('INTERNAL_CALL_SIGN_SALT', 'INTERNALCALL', null, '内部请求加密盐', '1', 'SECURITY', null, null, '2021-11-27 23:01:06', null);
INSERT INTO `s_sys_param_item` VALUES ('JWT_SECRET_KEY', 'FALL-PLATFORM', null, 'JWT secret key 密钥', '1', 'JWT', null, null, '2021-11-27 13:30:34', '2021-11-27 13:30:45');
INSERT INTO `s_sys_param_item` VALUES ('JWT_TTL', '900000', null, 'JWT 默认过期时间(s)', '1', 'JWT', null, null, '2021-11-27 13:31:44', '2021-11-27 14:18:50');
INSERT INTO `s_sys_param_item` VALUES ('keepAliveTime', '30', null, '存活时间(s)', '1', 'THREADPOOL_CONFIG', null, null, '2021-11-27 14:51:41', null);
INSERT INTO `s_sys_param_item` VALUES ('LANGUAGE_FILTER_ORDER', '-2147483548', null, '多语言过滤器', '1', 'FILTER_ORDER', null, null, '2021-11-27 21:45:02', null);
INSERT INTO `s_sys_param_item` VALUES ('LOGIN_URL', '/shiro/login', null, '登录URL', '1', 'SHIRO', null, null, '2022-02-15 09:08:36', '2022-02-19 22:12:18');
INSERT INTO `s_sys_param_item` VALUES ('maxPoolSize', '10', null, '最大线程数', '1', 'THREADPOOL_CONFIG', null, null, '2021-11-27 14:50:40', '2021-11-27 14:50:51');
INSERT INTO `s_sys_param_item` VALUES ('MAX_AGE', '3600', null, 'MAX_AGE(s)', '1', 'CORS_CONFIG', null, null, '2021-11-27 22:06:38', '2021-11-27 22:07:44');
INSERT INTO `s_sys_param_item` VALUES ('MAX_AUTO_RETRY_TIME', '3', null, '最大自动重试次数', '1', 'DISTRIBUTED_TX', null, null, '2021-11-27 14:19:25', null);
INSERT INTO `s_sys_param_item` VALUES ('MAX_RETRY_COUNT', '3', null, '最大重试次数', '1', 'SCHEDULER_CONFIG', null, null, '2021-11-27 14:44:03', null);
INSERT INTO `s_sys_param_item` VALUES ('queueCapacity', '500', null, '队列容量', '1', 'THREADPOOL_CONFIG', null, null, '2021-11-27 14:51:07', null);
INSERT INTO `s_sys_param_item` VALUES ('READ_TIMEOUT_SEC', '60', null, '读取超时时间(s)', '1', 'FEIGN_CONFIG', null, null, '2021-11-27 14:39:35', null);
INSERT INTO `s_sys_param_item` VALUES ('REDIS_LOCK_AUTO_RELEASE_TIME', '300000', null, 'redis 分布式锁自动释放时间(ms)', '1', 'DISTRIBUTED_TX', null, null, '2021-11-27 14:19:55', '2021-11-27 14:21:31');
INSERT INTO `s_sys_param_item` VALUES ('REDIS_LOCK_BUFFER', '100', null, 'redis 分布式锁缓冲时间(ms)', '1', 'DISTRIBUTED_TX', null, null, '2021-11-27 14:21:04', null);
INSERT INTO `s_sys_param_item` VALUES ('REDIS_LOCK_BUFFER_ENABLED', '1', null, '是否允许缓冲', '1', 'DISTRIBUTED_TX', null, null, '2021-11-27 14:21:19', null);
INSERT INTO `s_sys_param_item` VALUES ('REDIS_LOCK_TIMEOUT', '300000', null, 'redis 分布式锁加锁超时时间(ms)', '1', 'DISTRIBUTED_TX', null, null, '2021-11-27 14:20:34', null);
INSERT INTO `s_sys_param_item` VALUES ('REGISTER_PATH', '/**', null, 'REGISTER_PATH', '1', 'CORS_CONFIG', null, null, '2021-11-27 22:07:15', null);
INSERT INTO `s_sys_param_item` VALUES ('REMEMBER_ME_COOKIE_MAX_AGE', '86400', null, 'REMEMBER_ME最大时长(s)', '1', 'SHIRO', null, null, '2022-02-15 09:17:29', '2022-02-15 09:17:36');
INSERT INTO `s_sys_param_item` VALUES ('RETRY', '1', null, '是否重试', '1', 'SCHEDULER_CONFIG', null, null, '2021-11-27 14:43:40', null);
INSERT INTO `s_sys_param_item` VALUES ('RETRY_INTEGER_ERVAL', '30', null, '重试时间间隔(s)', '1', 'SCHEDULER_CONFIG', null, null, '2021-11-27 14:44:31', null);
INSERT INTO `s_sys_param_item` VALUES ('RSA_PRIVATE_KEY', 'MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKgfsquhEPN5XHnNaPVWM/cqGPJ3F/spO5X2PIhkPe9bDN/EXnXBVys/ZxlbT15KKK8kemwwhHzDcC64yP9wa+s/eJYJicymJqjwqdUhC4al3/TQboyRtucpo7eDixgL46ZXzkUL9zDeAPtdvkJOnSRo4uUAb9RtkcTAo8YhJJwpAgMBAAECgYBLnj45re82434xlq8/se8bm4au/9v8btZLI/6kBhpJXzWSK/ukkTqIzDKuGADB0y8rX4fkw4nDqA/8I8bcBY+z+9XxmajyL+zsKf3p5I4AFFAElFqQqyP8s4oGGUFCFL4M26C5UPqopjcS4KZGW/l4y1aasAOsSwrYajVOoeZKkQJBANyP/QY04qKk7f/F73nh3Qk9SeIgRJkrRIBL3ReVq5fRs+I9h2seMxYtZueo/pIaFRvM1ps2u36uU0rXBaXzoNUCQQDDIterC+3NJeWlAtDU44lWLxnQeywfGZj0wVCrsWA1Oei3vylAjS1C2SMpnw56uf4kSCQRL7cX578yX7meVJgFAkAWh0aK6b/0aPA+XdNWGbHgDbiMNkuLcZXvnNAaZpk+SpcbFrnMQStqqnyGUQafmbZqPoP1GqQQDnJHhKr5Bmd5AkAK6yEsJe+5Ydx2njC3KMAffWUxYCL43oLLdyGfab5bXBEkbuLNBNk6e1HXW1oBDfUlbI/wEj5pbQMqu2Z0cIFNAkEAhXktsNKuo1dxxyFZ3MgsHDqOF0YKdHH2RXrn160UlF8VFWAC/uVro0yShB7FR2+mjUiPqP+2aK9p6cAHX8wavA==', null, 'RSA 私钥', '1', 'RSA', null, null, '2021-11-27 13:34:00', null);
INSERT INTO `s_sys_param_item` VALUES ('RSA_PUBLIC_KEY', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoH7KroRDzeVx5zWj1VjP3Khjydxf7KTuV9jyIZD3vWwzfxF51wVcrP2cZW09eSiivJHpsMIR8w3AuuMj/cGvrP3iWCYnMpiao8KnVIQuGpd/00G6MkbbnKaO3g4sYC+OmV85FC/cw3gD7Xb5CTp0kaOLlAG/UbZHEwKPGISScKQIDAQAB', null, 'RSA 公钥', '1', 'RSA', null, null, '2021-11-27 13:33:13', '2021-11-27 13:33:22');
INSERT INTO `s_sys_param_item` VALUES ('SHIRO_ACCESSTOKEN_TTL', '18', null, 'accesstoken过期时间', '1', 'SHIRO', null, null, '2022-02-19 21:02:24', '2022-03-01 09:33:39');
INSERT INTO `s_sys_param_item` VALUES ('SHIRO_REFRESHTOKEN_TTL', '3600', null, 'refreshtoken过期时间', '1', 'SHIRO', null, null, '2022-02-19 21:02:08', '2022-02-28 11:19:09');
INSERT INTO `s_sys_param_item` VALUES ('SHIRO_REFRESH_TOKEN_TIME_INTERVAL', '500', null, 'refreshtoken刷新的时间间隔', '1', 'SHIRO', null, null, '2022-02-19 21:23:09', '2022-02-19 21:23:24');
INSERT INTO `s_sys_param_item` VALUES ('SIGNATURE_FILTER_ORDER', '-2147483348', null, '防止表单重复提交的过滤器', '1', 'FILTER_ORDER', null, null, '2021-11-27 21:46:00', '2021-11-27 21:46:57');
INSERT INTO `s_sys_param_item` VALUES ('STATIC_RESORUCES', '/static,/scripts,/css,.js,/js,.png,.jpg,/images,/fonts,.css,.html,.svg,.gif', null, '系统静态资源', '1', 'SECURITY', null, null, '2021-11-27 14:46:43', '2021-11-27 14:46:51');
INSERT INTO `s_sys_param_item` VALUES ('SUCCESS_URL', '/index.html', null, '登录成功跳转URL', '1', 'SHIRO', null, null, '2022-02-15 09:10:12', '2022-02-15 09:10:28');
INSERT INTO `s_sys_param_item` VALUES ('UNAUTHORIZED_URL', '/403', null, '认证失败跳转URL', '1', 'SHIRO', null, null, '2022-02-15 09:14:39', '2022-02-15 09:14:42');
INSERT INTO `s_sys_param_item` VALUES ('XSS_ENABLE', 'true', null, '是否开启XSS过滤', '1', 'XSS_CONFIG', null, null, '2021-11-29 09:40:57', '2021-11-29 09:42:08');
INSERT INTO `s_sys_param_item` VALUES ('XSS_FILTER_ORDER', '-2147483248', null, 'XSS跨站脚本攻击过滤器', '1', 'FILTER_ORDER', null, null, '2021-11-27 21:48:00', null);

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `account` varchar(50) NOT NULL COMMENT '用户账号',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `username` varchar(50) NOT NULL COMMENT '用户昵称',
  `avtar` varchar(150) DEFAULT NULL COMMENT '头像URL',
  `tel` varchar(50) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `sex` tinyint(3) unsigned DEFAULT NULL COMMENT '性别',
  `status` tinyint(3) unsigned DEFAULT '1' COMMENT '状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `source_type` tinyint(3) unsigned DEFAULT NULL COMMENT '用户来源',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('1', 'admin', '21232F297A57A5A743894A0E4A801FC3', '管理员', null, '13205957694', '809566095@qq.com', '350521199401294039', 'payn', '2022-02-10 17:55:35', 'aaa', '0', '1', null, null, null, null, null, '2022-02-12 17:55:46', '2022-02-12 18:25:05');

-- ----------------------------
-- Table structure for s_user_role
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
INSERT INTO `s_user_role` VALUES ('1', '1', '1', null, null, '2022-02-12 17:56:30', null);

-- ----------------------------
-- Table structure for ureport_file
-- ----------------------------
DROP TABLE IF EXISTS `ureport_file`;
CREATE TABLE `ureport_file` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '文件名',
  `content` mediumblob COMMENT '内容',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='ureport文件表';

-- ----------------------------
-- Records of ureport_file
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_group`;
CREATE TABLE `xxl_job_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) NOT NULL COMMENT '执行器名称',
  `address_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` text COMMENT '执行器地址列表，多地址逗号分隔',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of xxl_job_group
-- ----------------------------
INSERT INTO `xxl_job_group` VALUES ('1', 'xxl-job-executor-sample', '示例执行器', '0', null, '2022-01-25 15:10:01');

-- ----------------------------
-- Table structure for xxl_job_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_info`;
CREATE TABLE `xxl_job_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_desc` varchar(255) NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `author` varchar(64) DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) DEFAULT NULL COMMENT '报警邮件',
  `schedule_type` varchar(50) NOT NULL DEFAULT 'NONE' COMMENT '调度类型',
  `schedule_conf` varchar(128) DEFAULT NULL COMMENT '调度配置，值含义取决于调度类型',
  `misfire_strategy` varchar(50) NOT NULL DEFAULT 'DO_NOTHING' COMMENT '调度过期策略',
  `executor_route_strategy` varchar(50) DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int(11) NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `glue_type` varchar(50) NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '上次调度时间',
  `trigger_next_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '下次调度时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of xxl_job_info
-- ----------------------------
INSERT INTO `xxl_job_info` VALUES ('1', '1', '测试任务1', '2018-11-03 22:21:31', '2022-01-25 09:31:25', 'XXL', '', 'CRON', '0 0 0 * * ? *', 'DO_NOTHING', 'FIRST', 'demoJobHandler', '', 'SERIAL_EXECUTION', '0', '0', 'BEAN', '', 'GLUE代码初始化', '2018-11-03 22:21:31', '', '1', '0', '1643126400000');

-- ----------------------------
-- Table structure for xxl_job_lock
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_lock`;
CREATE TABLE `xxl_job_lock` (
  `lock_name` varchar(50) NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of xxl_job_lock
-- ----------------------------
INSERT INTO `xxl_job_lock` VALUES ('schedule_lock');

-- ----------------------------
-- Table structure for xxl_job_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log`;
CREATE TABLE `xxl_job_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `trigger_time` datetime DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int(11) NOT NULL COMMENT '调度-结果',
  `trigger_msg` text COMMENT '调度-日志',
  `handle_time` datetime DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int(11) NOT NULL COMMENT '执行-状态',
  `handle_msg` text COMMENT '执行-日志',
  `alarm_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`),
  KEY `I_trigger_time` (`trigger_time`),
  KEY `I_handle_code` (`handle_code`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of xxl_job_log
-- ----------------------------
INSERT INTO `xxl_job_log` VALUES ('1', '1', '1', null, 'demoJobHandler', '', null, '0', '2022-01-25 09:26:28', '500', '任务触发类型：手动触发<br>调度机器：192.168.0.30<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', null, '0', null, '1');
INSERT INTO `xxl_job_log` VALUES ('2', '1', '1', null, 'demoJobHandler', '', null, '0', '2022-01-25 09:26:55', '500', '任务触发类型：手动触发<br>调度机器：192.168.0.30<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', null, '0', null, '1');
INSERT INTO `xxl_job_log` VALUES ('3', '1', '1', '127.0.0.1', 'demoJobHandler', '', null, '0', '2022-01-25 09:28:07', '500', '任务触发类型：手动触发<br>调度机器：192.168.0.30<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1/run), for url : 127.0.0.1/run', null, '0', null, '1');
INSERT INTO `xxl_job_log` VALUES ('4', '1', '1', null, 'demoJobHandler', '', null, '0', '2022-01-25 09:31:29', '500', '任务触发类型：手动触发<br>调度机器：192.168.0.30<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', null, '0', null, '1');
INSERT INTO `xxl_job_log` VALUES ('5', '1', '1', '127.0.0.1', 'demoJobHandler', '', null, '0', '2022-01-25 09:31:52', '500', '任务触发类型：手动触发<br>调度机器：192.168.0.30<br>执行器-注册方式：手动录入<br>执行器-地址列表：[127.0.0.1]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：127.0.0.1<br>code：500<br>msg：xxl-rpc remoting error(no protocol: 127.0.0.1/run), for url : 127.0.0.1/run', null, '0', null, '1');
INSERT INTO `xxl_job_log` VALUES ('6', '1', '1', 'http://192.168.0.30:9999/', 'demoJobHandler', '', null, '0', '2022-01-25 09:32:39', '200', '任务触发类型：手动触发<br>调度机器：192.168.0.30<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.0.30:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.0.30:9999/<br>code：200<br>msg：null', '2022-01-25 09:32:50', '200', '', '0');
INSERT INTO `xxl_job_log` VALUES ('7', '1', '1', 'http://192.168.0.30:9999/', 'demoJobHandler', '', null, '0', '2022-01-25 09:36:00', '200', '任务触发类型：手动触发<br>调度机器：192.168.0.30<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.0.30:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.0.30:9999/<br>code：200<br>msg：null', '2022-01-25 09:36:10', '200', '', '0');
INSERT INTO `xxl_job_log` VALUES ('8', '1', '1', 'http://192.168.0.30:9999/', 'demoJobHandler', '', null, '0', '2022-01-25 09:36:24', '200', '任务触发类型：手动触发<br>调度机器：192.168.0.30<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.0.30:9999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.0.30:9999/<br>code：200<br>msg：null', '2022-01-25 09:36:43', '200', '', '0');

-- ----------------------------
-- Table structure for xxl_job_log_report
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log_report`;
CREATE TABLE `xxl_job_log_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trigger_day` datetime DEFAULT NULL COMMENT '调度-时间',
  `running_count` int(11) NOT NULL DEFAULT '0' COMMENT '运行中-日志数量',
  `suc_count` int(11) NOT NULL DEFAULT '0' COMMENT '执行成功-日志数量',
  `fail_count` int(11) NOT NULL DEFAULT '0' COMMENT '执行失败-日志数量',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_trigger_day` (`trigger_day`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of xxl_job_log_report
-- ----------------------------
INSERT INTO `xxl_job_log_report` VALUES ('1', '2022-01-24 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('2', '2022-01-23 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('3', '2022-01-22 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('4', '2022-01-25 00:00:00', '0', '3', '5', null);

-- ----------------------------
-- Table structure for xxl_job_logglue
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_logglue`;
CREATE TABLE `xxl_job_logglue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) NOT NULL COMMENT 'GLUE备注',
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of xxl_job_logglue
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_registry`;
CREATE TABLE `xxl_job_registry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(50) NOT NULL,
  `registry_key` varchar(255) NOT NULL,
  `registry_value` varchar(255) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `i_g_k_v` (`registry_group`,`registry_key`,`registry_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of xxl_job_registry
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_user
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_user`;
CREATE TABLE `xxl_job_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '账号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `role` tinyint(4) NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of xxl_job_user
-- ----------------------------
INSERT INTO `xxl_job_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1', null);
